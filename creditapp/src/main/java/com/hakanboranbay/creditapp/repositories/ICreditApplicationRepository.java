package com.hakanboranbay.creditapp.repositories;

import com.hakanboranbay.creditapp.model.CreditApplication;

public interface ICreditApplicationRepository {

	CreditApplication checkCreditApplication(CreditApplication creditApplication);
	
}
