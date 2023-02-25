package com.hakanboranbay.creditapp.repositories;

import java.util.List;

import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.model.CreditApplication;

public interface ICreditApplicationRepository {

	int createCreditApplication(CreditApplication creditApplication);
	List<CreditApplication> listClientCreditApplications(Client client);
	
}
