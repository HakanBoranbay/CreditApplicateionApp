package com.hakanboranbay.creditapp.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hakanboranbay.creditapp.jdbc.JdbcRepository;
import com.hakanboranbay.creditapp.model.Client;

@Component
public class ClientRepository implements IClientRepository {
	
	@Autowired
	JdbcRepository jdbcRepository;

	@Override
	public Client create(Client client) {
		
		return client;
	}

	@Override
	public Client getDetailsById(String clientIdNo) {
		return jdbcRepository.findById(clientIdNo);
	}

	@Override
	public Client updateClient(int creditScore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client updateClient(double monthlyIncome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client updateClient(int creditScore, double monthlyIncome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client deleteClient(String clientIdNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
