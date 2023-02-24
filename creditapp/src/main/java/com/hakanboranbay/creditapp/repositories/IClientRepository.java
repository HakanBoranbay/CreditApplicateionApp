package com.hakanboranbay.creditapp.repositories;

import com.hakanboranbay.creditapp.model.Client;

public interface IClientRepository {

	Client create(Client account);
	Client getDetailsById(String clientIdNo);
	Client updateClient(int creditScore);
	Client updateClient(double monthlyIncome);
	Client updateClient(int creditScore, double monthlyIncome);
	Client deleteClient(String clientIdNo);
	
}
