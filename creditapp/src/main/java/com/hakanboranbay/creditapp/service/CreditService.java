package com.hakanboranbay.creditapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.model.CreditApplication;
import com.hakanboranbay.creditapp.repositories.ClientJdbcRepository;
import com.hakanboranbay.creditapp.repositories.CreditApplicationJdbcRepository;
import com.hakanboranbay.creditapp.request.CreditApplicationRequest;

@Service
public class CreditService {
	
	@Autowired
	private CreditApplicationJdbcRepository creditApplicationJdbcRepository;
	@Autowired
	private ClientJdbcRepository clientJdbcRepository;

	public CreditApplication createCreditApplication(CreditApplicationRequest request) {
		CreditApplication creditApplication = mapApplication(request);
		if (creditApplication != null) {
			creditApplicationJdbcRepository.createCreditApplication(creditApplication);
		}
		return creditApplication;
	}
	
	public List<CreditApplication> listClientCreditApplications(Client client) {
		return creditApplicationJdbcRepository.listClientCreditApplications(client);
	}

	private CreditApplication mapApplication(CreditApplicationRequest request) {
		CreditApplication creditApplication = new CreditApplication();
		Client client = clientJdbcRepository.getDetailsById(request.getClientIdNo());
		creditApplication.setClientIdNo(request.getClientIdNo());
		creditApplication.setClientMonthlyIncome(client.getMonthlyIncome());
		creditApplication.setClientCreditScore(client.getCreditScore());
		creditApplication.setCreditLimit(calculateAllowedCreditAmount(client, request));
		if (calculateAllowedCreditAmount(client, request) > 0) {
			creditApplication.setSuccessful(true);
		}

		return creditApplication;
	}

	private double calculateAllowedCreditAmount(Client client, CreditApplicationRequest request) {
		if (client.getCreditScore() < 500) {
			return 0;
		} else if (client.getCreditScore() >= 500 && client.getCreditScore() < 1000
				&& client.getMonthlyIncome() < 5000) {
			if (request.getAssurance() > 0) {
				return 10000 + (request.getAssurance() * 0.1);
			}
			return 10000;
		} else if (client.getCreditScore() >= 500 && client.getCreditScore() < 1000
				&& client.getMonthlyIncome() >= 5000 && client.getMonthlyIncome() < 10000) {
			if (request.getAssurance() > 0) {
				return 20000 + (request.getAssurance() * 0.2);
			}
			return 20000;
		} else if (client.getCreditScore() >= 500 && client.getCreditScore() < 1000
				&& client.getMonthlyIncome() >= 10000) {
			if (request.getAssurance() > 0) {
				return (client.getMonthlyIncome() * client.getCreditLimitConstant() / 2)
						+ (request.getAssurance() * 0.25);
			}
			return (client.getMonthlyIncome() * client.getCreditLimitConstant() / 2);
		} else if (client.getCreditScore() >= 1000) {
			if (request.getAssurance() > 0) {
				return (client.getMonthlyIncome() * client.getCreditLimitConstant() / 2)
						+ (request.getAssurance() * 0.5);
			}
			return (client.getMonthlyIncome() * client.getCreditLimitConstant());
		} else {
			return 0;
		}

	}

}
