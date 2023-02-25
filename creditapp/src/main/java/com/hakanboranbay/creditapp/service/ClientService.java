package com.hakanboranbay.creditapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.repositories.ClientJdbcRepository;
import com.hakanboranbay.creditapp.request.ClientCreateRequest;

@Service
public class ClientService {
	
	@Autowired
    private ClientJdbcRepository clientRepository;

    public Client createClient(ClientCreateRequest request){
        Client client = mapClient(request);
        if (client != null) {
            clientRepository.create(client);
        }
        return client;
    }
    
    public Client getDetailsById(String idNo) {
    	return clientRepository.getDetailsById(idNo);
    }
    
    public int updateClientCreditScore(Client client, int creditScore, double monthlyIncome) {
    	client.setCreditScore(creditScore);
    	client.setMonthlyIncome(monthlyIncome);
    	return clientRepository.updateClient(client, creditScore, monthlyIncome);
    }
    
    public int deleteClient(Client client) {
    	return clientRepository.deleteClient(client);
    }

    private Client mapClient(ClientCreateRequest request) {
        Client client = null;

        client = new Client();
        client.setIdNo(request.getIdNo());
        client.setName(request.getName());
        client.setSurname(request.getSurname());
        client.setMonthlyIncome(request.getMonthlyIncome());
        client.setPhoneNo(request.getPhoneNo());
        client.setDateOfBirth(request.getDateOfBirth());
        client.setCreditScore(request.getCreditScore());
        client.setLastModified(System.currentTimeMillis());
        return client;
    }

}
