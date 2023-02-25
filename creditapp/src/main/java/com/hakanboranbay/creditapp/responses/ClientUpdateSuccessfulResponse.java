package com.hakanboranbay.creditapp.responses;

import lombok.Data;

@Data
public class ClientUpdateSuccessfulResponse {
	
	private String message;
	private String idNo;
	private int creditScore;
	private double monthlyIncome;

}
