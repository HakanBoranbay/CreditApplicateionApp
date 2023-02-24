package com.hakanboranbay.creditapp.repositories;

import org.springframework.stereotype.Component;

import com.hakanboranbay.creditapp.model.CreditApplication;

@Component
public class CreditApplicationRepository implements ICreditApplicationRepository {

	@Override
	public CreditApplication checkCreditApplication(CreditApplication creditApplication) {
		return creditApplication;
	}
	
}
