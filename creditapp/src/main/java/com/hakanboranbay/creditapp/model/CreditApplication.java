package com.hakanboranbay.creditapp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreditApplication implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String clientIdNo;
	private double clientMonthlyIncome;
	private int clientCreditScore;
    private boolean isSuccessful;
    private double creditLimit;
    private long applicationDate = System.currentTimeMillis();

}
