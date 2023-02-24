package com.hakanboranbay.creditapp.responses;

import lombok.Data;

@Data
public class CreditSuccessfulResponse {
    private String message;
    private double creditAmount;
}
