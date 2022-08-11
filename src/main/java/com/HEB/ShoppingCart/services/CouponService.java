package com.HEB.ShoppingCart.services;

import com.HEB.ShoppingCart.entities.Coupon;
import com.HEB.ShoppingCart.exceptions.InternalServerErrorException;
import com.HEB.ShoppingCart.exceptions.NoRecordsException;
import com.HEB.ShoppingCart.exceptions.NotFoundException;
import com.HEB.ShoppingCart.repositories.CouponsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    private static Logger logger = LoggerFactory.getLogger(CouponService.class);

    private final CouponsRepository couponsRepository;

    public CouponService(CouponsRepository couponsRepository) {
        this.couponsRepository = couponsRepository;
    }

    public List<Coupon> getCoupons() throws NoRecordsException, InternalServerErrorException {
        try {
            List<Coupon> coupons = couponsRepository.findAll();

            if(coupons.isEmpty()) {
                logger.error("No records found");
                throw new NoRecordsException();
            }

            return coupons;
        }
        catch (NoRecordsException e) {
            logger.error(e.getLocalizedMessage());
            throw new NoRecordsException();
        }
        catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }


    public List<Coupon> save(List<Coupon> coupons) {
        return couponsRepository.saveAll(coupons);
    }
}
