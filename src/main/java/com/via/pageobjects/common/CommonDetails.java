package com.via.pageobjects.common;

import lombok.Getter;
import lombok.Setter;

import com.via.utils.Constant.Country;
import com.via.utils.Constant.Product;

@Getter
@Setter
public class CommonDetails {

    private boolean login      = false;

    private Country country;
    private Product product;

    private String  paymentMode;
    private String  paymentType;
    private String  promoCode;

    private double  totalFare  = 0.0;
    private double  ssrCharges = 0.0;
    private double  payfee     = 0.0;

}
