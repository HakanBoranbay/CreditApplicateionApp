package com.hakanboranbay.creditapp.repositories;

import com.hakanboranbay.creditapp.model.Client;

public interface IClientRepository {

	int create(Client account);
	Client getDetailsById(String clientIdNo);
	int updateClient(Client client, int creditScore, double monthlyIncome);
	int deleteClient(Client client);
	
}
