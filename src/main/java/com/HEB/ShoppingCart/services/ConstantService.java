package com.HEB.ShoppingCart.services;

import com.HEB.ShoppingCart.entities.Constant;
import com.HEB.ShoppingCart.exceptions.InternalServerErrorException;
import com.HEB.ShoppingCart.exceptions.NotFoundException;
import com.HEB.ShoppingCart.repositories.ConstantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ConstantService {

    private static Logger logger = LoggerFactory.getLogger(ConstantService.class);


    private final ConstantRepository constantRepository;
    private HashMap<String, Constant> cachedConstants;

    private final String TAX_RATE = "TAX_RATE";
    private final String FEATURE = "FEATURE";
    private final String FLOAT_TYPE = "FLOAT";
    private final String STRING_TYPE = "STRING";

    public ConstantService(ConstantRepository constantRepository) {
        this.constantRepository = constantRepository;
    }

    public List<Constant> getConstants() {
        return constantRepository.findAll();
    }

    public Float getTaxRate() throws NotFoundException, InternalServerErrorException {
        try {
            if(cachedConstants == null) {
                loadCache();
            }

            if(cachedConstants == null || cachedConstants.isEmpty() ||
                    !cachedConstants.containsKey(TAX_RATE) || !cachedConstants.get(TAX_RATE).getConstantType().equals(FLOAT_TYPE)) {
                throw new NotFoundException();
            }

            return Float.parseFloat(cachedConstants.get(TAX_RATE).getConstantValue());
        }
        catch (NumberFormatException | NotFoundException e) {
            logger.error(e.getLocalizedMessage());
            throw new NotFoundException();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public String getFeatureDescription(String feature) throws NotFoundException, InternalServerErrorException {
        try {
            if(cachedConstants == null) {
                loadCache();
            }

            if(cachedConstants == null || cachedConstants.isEmpty()) {
                throw new NotFoundException();
            }

            ArrayList<String> constants = new ArrayList<>();
            cachedConstants.forEach((k, v) -> {
                if(k.contains(FEATURE) && k.contains(feature.toUpperCase()) && v.getConstantType().equals(STRING_TYPE)) {
                    constants.add(v.getConstantValue());
                }
            });

            if (constants.size() > 1) {
                throw new InternalServerErrorException();
            }

            return constants.get(0);
        }
        catch (NotFoundException e) {
            logger.error(e.getLocalizedMessage());
            throw new NotFoundException();
        }
        catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public List<Constant> save(List<Constant> coupons) {
        return constantRepository.saveAll(coupons);
    }

    private void loadCache() {
        if(constantRepository != null) {
            cachedConstants = new HashMap<>();
            List<Constant> constants = getConstants();
            constants.stream().forEach(constant -> {
                if(!cachedConstants.containsKey(constant.getConstantKey())) {
                    cachedConstants.put(constant.getConstantKey(), constant);
                }
            });
        }
    }
}
