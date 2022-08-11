package com.HEB.ShoppingCart.controllers;

import com.HEB.ShoppingCart.entities.*;
import com.HEB.ShoppingCart.exceptions.InternalServerErrorException;
import com.HEB.ShoppingCart.exceptions.NoRecordsException;
import com.HEB.ShoppingCart.exceptions.NotFoundException;
import com.HEB.ShoppingCart.services.CartService;
import com.HEB.ShoppingCart.services.ConstantService;
import com.HEB.ShoppingCart.services.CouponService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-cart")
public class CartController {

    private static Logger logger = LoggerFactory.getLogger(CartController.class);

    private final CartService cartService;
    private final CouponService couponService;
    private final ConstantService constantService;

    public CartController(CartService cartService, CouponService couponService, ConstantService constantService) {
        this.cartService = cartService;
        this.couponService = couponService;
        this.constantService = constantService;
    }

    @ApiOperation(value = "Get list of items in shopping cart", response = List.class, tags = "getCart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/cart")
    public List<Item> getCart() throws NoRecordsException, InternalServerErrorException {
        try {
            return cartService.getCart();
        }
        catch (NoRecordsException e) {
            logger.error(e.getLocalizedMessage());
            throw new NoRecordsException();
        }
        catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @ApiOperation(value = "Get list of coupons", response = List.class, tags = "getCoupons")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/coupons")
    public List<Coupon> getCoupons() throws NoRecordsException, InternalServerErrorException {
        try {
            return couponService.getCoupons();
        }
        catch (NoRecordsException e) {
            logger.error(e.getLocalizedMessage());
            throw new NoRecordsException();
        }
        catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @ApiOperation(value = "Feature One", response = FeatureOneDto.class, tags = "getFeatureOne")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/feature-one")
    public FeatureOneDto getFeatureOne() throws NoRecordsException, NotFoundException, InternalServerErrorException {
        try {
            return cartService.getFeatureOne();
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

    @ApiOperation(value = "Feature Two", response = FeatureTwoDto.class, tags = "getFeatureTwo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/feature-two")
    public FeatureTwoDto getFeatureTwo() throws NoRecordsException, NotFoundException, InternalServerErrorException {
        try {
            return cartService.getFeatureTwo();
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

    @ApiOperation(value = "Feature Three", response = FeatureThreeDto.class, tags = "getFeatureThree")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/feature-three")
    public FeatureThreeDto getFeatureThree() throws NoRecordsException, NotFoundException, InternalServerErrorException {
        try {
            return cartService.getFeatureThree();
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

    @ApiOperation(value = "Feature Four", response = FeatureFourDto.class, tags = "getFeatureFour")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/feature-four")
    public FeatureFourDto getFeatureFour() throws NoRecordsException, NotFoundException, InternalServerErrorException {
        try {
            return cartService.getFeatureFour();
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

    @ApiOperation(value = "Get Constants", response = FeatureThreeDto.class, tags = "getConstants")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    @GetMapping("/constants")
    public List<Constant> getConstants() {
        return constantService.getConstants();
    }
}
