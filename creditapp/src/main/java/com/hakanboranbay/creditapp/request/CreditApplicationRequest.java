package com.hakanboranbay.creditapp.request;

import lombok.Data;

/**
 *{
 *         "clientIdNo" : 81866280431,
 *         "assurance" : 5000.0
 *     }
 *
 */
@Data
public class CreditApplicationRequest {

    private String clientIdNo;
    private double assurance;
}
