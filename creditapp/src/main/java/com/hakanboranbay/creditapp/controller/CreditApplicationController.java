package com.hakanboranbay.creditapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hakanboranbay.creditapp.jdbc.JdbcRepository;
import com.hakanboranbay.creditapp.model.CreditApplication;
import com.hakanboranbay.creditapp.request.CreditApplicationRequest;
import com.hakanboranbay.creditapp.responses.CreditSuccessfulResponse;
import com.hakanboranbay.creditapp.service.CreditService;

@RestController
public class CreditApplicationController {

    @Autowired
    private CreditService service;
    @Autowired
    private JdbcRepository jdbcRepository;

    @PostMapping("/credits")
    public ResponseEntity<?> create(@RequestBody CreditApplicationRequest request) {
        CreditApplication creditApplication = service.checkCreditApplication(request);
        jdbcRepository.saveCreditApplication(creditApplication);
        CreditSuccessfulResponse response = new CreditSuccessfulResponse();

        if (creditApplication.isSuccessful()) {
            response = new CreditSuccessfulResponse();
            response.setMessage("Credit approved");
            response.setCreditAmount(creditApplication.getCreditLimit());
            return ResponseEntity.ok(response);
        } else {
            response = new CreditSuccessfulResponse();
            response.setMessage("Credit not approved");
            return ResponseEntity.badRequest().body(response);
        }

    }

}
