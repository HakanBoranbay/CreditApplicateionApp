package com.hakanboranbay.creditapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.repositories.ClientRepository;
import com.hakanboranbay.creditapp.request.ClientCreateRequest;

@Service
public class ClientService {
	
	@Autowired
    private ClientRepository clientRepository;

    public Client createClient(ClientCreateRequest request){
        Client client = mapClient(request);
        if (client != null) {
            clientRepository.create(client);
        }
        return client;
    }

    private Client mapClient(ClientCreateRequest request) {
        Client client = null;

//        if (request.getMonthlyIncome() < 0
//                || request.getDateOfBirth().getYear() < 1923
//                || request.getDateOfBirth().getYear() > 2005
//                || request.getCreditScore() < 0 || request.getCreditScore() > 1900) {
//            return client;
//        }

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
