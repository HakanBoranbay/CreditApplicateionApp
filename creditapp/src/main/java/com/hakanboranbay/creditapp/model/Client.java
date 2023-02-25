package com.hakanboranbay.creditapp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idNo;
	private String name;
	private String surname;
	private double monthlyIncome;
	private String phoneNo;
	private String dateOfBirth; // YYYY-MM-DD format needed.
	private int creditScore;
	private final int creditLimitConstant = 4;
	private long lastModified = System.currentTimeMillis();
	
}
