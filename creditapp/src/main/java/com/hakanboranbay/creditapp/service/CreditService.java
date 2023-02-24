package com.hakanboranbay.creditapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakanboranbay.creditapp.jdbc.JdbcRepository;
import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.model.CreditApplication;
import com.hakanboranbay.creditapp.request.CreditApplicationRequest;

@Service
public class CreditService {

	@Autowired 
	JdbcRepository jdbcRepository;

	public CreditApplication checkCreditApplication(CreditApplicationRequest request) {
		return mapApplication(request);
	}

	private CreditApplication mapApplication(CreditApplicationRequest request) {
		CreditApplication creditApplication = new CreditApplication();
		Client client = jdbcRepository.findById(request.getClientIdNo());

		creditApplication.setCreditLimit(calculateAllowedCreditAmount(client, request));
		if (calculateAllowedCreditAmount(client, request) > 0) {
			creditApplication.setSuccessful(true);
			creditApplication.setClientIdNo(request.getClientIdNo());
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
