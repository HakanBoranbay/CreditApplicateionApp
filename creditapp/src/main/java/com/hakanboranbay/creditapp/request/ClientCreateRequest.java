package com.hakanboranbay.creditapp.request;

import lombok.Data;

/**
 * {
 * 		   "idNo" : 121212121212,	
 *         "name" : "Onur",
 *         "surname" : "Boranbay",
 *         "monthlyIncome" : 5000.0,
 *         "phoneNo" : "05555555555",
 *         "dateOfBirth" : "1992-06-18",
 *         "creditScore" : 800
 *     }
 *
 */
@Data
public class ClientCreateRequest {

	private String idNo;
	private String name;
	private String surname;
	private float monthlyIncome;
	private String phoneNo;
	private String dateOfBirth;
	private int creditScore;
	
}
