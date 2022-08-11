package com.HEB.ShoppingCart;

import com.HEB.ShoppingCart.entities.Constant;
import com.HEB.ShoppingCart.entities.Coupon;
import com.HEB.ShoppingCart.entities.Item;
import com.HEB.ShoppingCart.services.CartService;
import com.HEB.ShoppingCart.services.ConstantService;
import com.HEB.ShoppingCart.services.CouponService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.InputStream;
import java.util.List;

@EnableSwagger2
@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CartService cartService, CouponService couponService, ConstantService constantService){
		return args -> {
			ObjectMapper cartMapper = new ObjectMapper();
			ObjectMapper couponsMapper = new ObjectMapper();
			ObjectMapper constantsMapper = new ObjectMapper();

			TypeReference<List<Item>> cartTypeReference = new TypeReference<List<Item>>(){};
			TypeReference<List<Coupon>> couponsTypeReference = new TypeReference<List<Coupon>>(){};
			TypeReference<List<Constant>> constantTypeReference = new TypeReference<List<Constant>>(){};

			InputStream cartInputStream = TypeReference.class.getResourceAsStream("/json/cart.json");
			InputStream couponsInputStream = TypeReference.class.getResourceAsStream("/json/coupons.json");
			InputStream constantsInputStream = TypeReference.class.getResourceAsStream("/json/constants.json");

			try {
				List<Item> items = cartMapper.readValue(cartInputStream, cartTypeReference);
				cartService.save(items);

				List<Coupon> coupons = couponsMapper.readValue(couponsInputStream, couponsTypeReference);
				couponService.save(coupons);

				List<Constant> constants = constantsMapper.readValue(constantsInputStream, constantTypeReference);
				constantService.save(constants);

				System.out.println("Data Saved!");
			} catch (Exception e){
				System.out.println("Unable to save data: " + e.getMessage());
			}
		};
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
}
