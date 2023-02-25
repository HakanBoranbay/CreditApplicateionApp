package com.hakanboranbay.creditapp.request;

import lombok.Data;

@Data
public class ClientUpdateRequest {

	private String idNo;
	private int creditScore;
	private double monthlyIncome;
	
}
