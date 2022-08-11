package com.HEB.ShoppingCart.services;

import com.HEB.ShoppingCart.entities.*;
import com.HEB.ShoppingCart.exceptions.InternalServerErrorException;
import com.HEB.ShoppingCart.exceptions.NoRecordsException;
import com.HEB.ShoppingCart.exceptions.NotFoundException;
import com.HEB.ShoppingCart.repositories.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

@Service
public class CartService {

    private static Logger logger = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CouponService couponService;
    private final ConstantService constantService;


    public CartService(CartRepository cartRepository, CouponService couponService, ConstantService constantService) {
        this.cartRepository = cartRepository;
        this.couponService = couponService;
        this.constantService = constantService;
    }

    public List<Item> getCart() throws NoRecordsException, InternalServerErrorException {
        try {
            List<Item> cart = cartRepository.findAll();

            if(cart.isEmpty()) {
                logger.error("No records found");
                throw new NoRecordsException();
            }

            return cart;
        }
        catch (NoRecordsException e) {
            logger.error(e.getLocalizedMessage());
            throw new NoRecordsException();
        }
        catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public FeatureOneDto getFeatureOne() throws NoRecordsException, NotFoundException, InternalServerErrorException {
        try {
            //get items in the cart
            List<Item> cart = getCart();

            if(cart.isEmpty()) {
                logger.error("No records found in cart");
                throw new NoRecordsException();
            }

            FeatureOneDto featureOneDto = new FeatureOneDto();
            //get description
            featureOneDto.setDescription(constantService.getFeatureDescription("ONE"));

            //send items to method to calculate subtotal
            featureOneDto.setGrandTotal(getTotal(cart).setScale(2, RoundingMode.FLOOR));

            return featureOneDto;
        }
        catch (NoRecordsException e) {
            logger.error(e.getLocalizedMessage());
            throw new NoRecordsException();
        }
        catch (NotFoundException e) {
            logger.error(e.getLocalizedMessage());
            throw new NotFoundException();
        }
        catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public FeatureTwoDto getFeatureTwo() throws NoRecordsException, NotFoundException, InternalServerErrorException {
        try {
            //get items in the cart
            List<Item> cart = getCart();

            if(cart.isEmpty()) {
                logger.error("No records found in cart");
                throw new NoRecordsException();
            }

            FeatureTwoDto featureTwoDto = new FeatureTwoDto();
            //get description
            featureTwoDto.setDescription(constantService.getFeatureDescription("TWO"));

            //send items to method to calculate subtotal
            featureTwoDto.setSubTotal(getTotal(cart).setScale(2, RoundingMode.FLOOR));

            //call method to calculate tax from subtotal
            featureTwoDto.setTaxTotal(applyTax(featureTwoDto.getSubTotal()));

            //add tax and subtotal
            BigDecimal grandTotal = featureTwoDto.getSubTotal().add(featureTwoDto.getTaxTotal().setScale(2, RoundingMode.FLOOR));
            featureTwoDto.setGrandTotal(grandTotal);

            return featureTwoDto;
        }
        catch (NoRecordsException e) {
            logger.error(e.getLocalizedMessage());
            throw new NoRecordsException();
        }
        catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public FeatureThreeDto getFeatureThree() throws NoRecordsException, NotFoundException, InternalServerErrorException {
        try {
            //get items in the cart
            List<Item> cart = getCart();

            if(cart.isEmpty()) {
                logger.error("No records found in cart");
                throw new NoRecordsException();
            }

            FeatureThreeDto featureThreeDto = new FeatureThreeDto();
            //get description
            featureThreeDto.setDescription(constantService.getFeatureDescription("THREE"));

            //send items to method to calculate subtotal
            featureThreeDto.setSubTotal(getTotal(cart).setScale(2, RoundingMode.FLOOR));

            //send just nontaxable items to method to calculate nontaxable subtotal
            List<Item> nontaxableItems = cart.stream().filter(item -> !item.isIsTaxable()).toList();
            featureThreeDto.setNontaxableSubTotal(getTotal(nontaxableItems).setScale(2, RoundingMode.FLOOR));

            //send just taxable items to method to calculate taxable subtotal
            List<Item> taxableItems = cart.stream().filter(Item::isIsTaxable).toList();
            featureThreeDto.setTaxableSubTotal(getTotal(taxableItems).setScale(2, RoundingMode.FLOOR));

            //calculate tax on only taxable items
            BigDecimal totalTax = applyTax(featureThreeDto.getTaxableSubTotal()).setScale(2, RoundingMode.FLOOR);
            featureThreeDto.setTaxTotal(totalTax);

            //add subtotal and total tax
            featureThreeDto.setGrandTotal(featureThreeDto.getSubTotal().add(totalTax));

            return featureThreeDto;
        }
        catch (NoRecordsException e) {
            logger.error(e.getLocalizedMessage());
            throw new NoRecordsException();
        }
        catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public FeatureFourDto getFeatureFour() throws NoRecordsException, NotFoundException, InternalServerErrorException {
        try {
            //get items in the cart and coupons
            List<Item> cart = cartRepository.findAll();
            List<Coupon> coupons = couponService.getCoupons();

            if(cart.isEmpty() || coupons.isEmpty()) {
                String error = cart.isEmpty() ? "No records found in cart" : "No coupons found";
                logger.error(error);
                throw new NoRecordsException();
            }

            FeatureFourDto featureFourDto = new FeatureFourDto();
            //get description
            featureFourDto.setDescription(constantService.getFeatureDescription("FOUR"));

            //send items to method to calculate subtotal
            featureFourDto.setSubTotal(getTotal(cart).setScale(2, RoundingMode.FLOOR));

            //send just nontaxable items to method to calculate nontaxable subtotal
            List<Item> nontaxableItems = cart.stream().filter(item -> !item.isIsTaxable()).toList();
            featureFourDto.setNontaxableSubTotal(getTotal(nontaxableItems).setScale(2, RoundingMode.FLOOR));

            //send just taxable items to method to calculate taxable subtotal
            List<Item> taxableItems = cart.stream().filter(Item::isIsTaxable).toList();
            featureFourDto.setTaxableSubTotal(getTotal(taxableItems).setScale(2, RoundingMode.FLOOR));

            //send nontaxable and taxable items to method to get the discount from coupons to get total discount
            BigDecimal nontaxableDiscount = getDiscountFromCoupons(nontaxableItems, coupons);
            BigDecimal taxableDiscount = getDiscountFromCoupons(taxableItems, coupons);
            BigDecimal discount = nontaxableDiscount.add(taxableDiscount).setScale(2, RoundingMode.FLOOR);
            featureFourDto.setDiscountTotal(discount);

            //calculate subtotal after discounts
            BigDecimal subtotalAfterDiscounts = featureFourDto.getSubTotal()
                    .subtract(discount).setScale(2, RoundingMode.FLOOR);
            featureFourDto.setSubTotalAfterDiscounts(subtotalAfterDiscounts);

            //calculate nontaxable subtotal after discounts on only those items
            BigDecimal nonTaxableSubtotalAfterDiscounts = featureFourDto.getNontaxableSubTotal()
                    .subtract(nontaxableDiscount).setScale(2, RoundingMode.FLOOR);
            featureFourDto.setNontaxableSubTotalAfterDiscounts(nonTaxableSubtotalAfterDiscounts);

            //calculate taxable subtotal after discounts on only those items
            BigDecimal taxableSubtotalAfterDiscounts = featureFourDto.getTaxableSubTotal()
                    .subtract(taxableDiscount).setScale(2, RoundingMode.FLOOR);
            featureFourDto.setTaxableSubTotalAfterDiscounts(taxableSubtotalAfterDiscounts);

            //calculate tax on only taxable items
            BigDecimal totalTax = applyTax(featureFourDto.getTaxableSubTotalAfterDiscounts()).setScale(2, RoundingMode.FLOOR);
            featureFourDto.setTaxTotal(totalTax);

            //add subtotal and total tax
            featureFourDto.setGrandTotal(subtotalAfterDiscounts.add(totalTax));

            return featureFourDto;
        }
        catch (NoRecordsException e) {
            logger.error(e.getLocalizedMessage());
            throw new NoRecordsException();
        }
        catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public List<Item> save(List<Item> cart) {
        return cartRepository.saveAll(cart);
    }

    private BigDecimal getTotal(List<Item> items) {
        BigDecimal total = new BigDecimal("0.00");

        for(Item item : items) {
            total = total.add(item.getPrice());

        }

        return total;
    }

    private BigDecimal applyTax(BigDecimal taxableSubtotal) throws NotFoundException, InternalServerErrorException {
        try {

            BigDecimal taxRate = BigDecimal.valueOf(constantService.getTaxRate());
            BigDecimal taxTotal = taxableSubtotal.multiply(taxRate);
            return taxTotal.setScale(2, RoundingMode.FLOOR);
        }
        catch (NumberFormatException | NotFoundException e) {
            logger.error(e.getLocalizedMessage());
            throw new NotFoundException();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    private BigDecimal getDiscountFromCoupons(List<Item> cart, List<Coupon> coupons) {
        BigDecimal discount = new BigDecimal("0.00");
        HashMap<Long, Coupon> couponHashMap = getCouponHashMap(coupons);

        for(Item item : cart) {
            if(couponHashMap.containsKey(item.getSku())) {
                BigDecimal difference = item.getPrice().compareTo(couponHashMap.get(item.getSku()).getDiscountPrice()) > 0 ?
                        couponHashMap.get(item.getSku()).getDiscountPrice() : item.getPrice();
                discount = discount.add(difference);
            }
        }

        return discount;
    }

    private HashMap<Long, Coupon> getCouponHashMap(List<Coupon> coupons) {
        HashMap<Long, Coupon> couponHashMap = new HashMap<>();

        coupons.stream().forEach(coupon -> {
            if(!couponHashMap.containsKey(coupon.getAppliedSku())) {
                couponHashMap.put(coupon.getAppliedSku(), coupon);
            }
        });

        return couponHashMap;
    }


}
