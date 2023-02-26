package com.hakanboranbay.creditapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hakanboranbay.creditapp.logger.Logger;
import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.model.CreditApplication;
import com.hakanboranbay.creditapp.request.CreditApplicationRequest;
import com.hakanboranbay.creditapp.responses.CreditSuccessfulResponse;
import com.hakanboranbay.creditapp.responses.ListCreditApplicationsFailResponse;
import com.hakanboranbay.creditapp.service.ClientService;
import com.hakanboranbay.creditapp.service.CreditService;

@RestController
public class CreditApplicationController {

    @Autowired
    private CreditService creditService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private Logger logger;

    @PostMapping("/credits")
    public ResponseEntity<?> create(@RequestBody CreditApplicationRequest request) {
        CreditApplication creditApplication = creditService.createCreditApplication(request);
        CreditSuccessfulResponse response = new CreditSuccessfulResponse();

        if (creditApplication.isSuccessful()) {
            response = new CreditSuccessfulResponse();
            response.setMessage("Credit approved");
            response.setCreditAmount(creditApplication.getCreditLimit());
            String message = "Account " + request.getClientIdNo() + " applied for credit. Result = seccessful, CreditLimit = " + creditApplication.getCreditLimit() + ".";
            logger.logToFile(message);
            return ResponseEntity.ok(response);
        } else {
            response = new CreditSuccessfulResponse();
            response.setMessage("Credit not approved");
            return ResponseEntity.badRequest().body(response);
        }

    }
    
    @GetMapping("/credits/{clientIdNo}/{dateOfBirth}")
    public ResponseEntity<?> listApplications(@PathVariable String clientIdNo, @PathVariable String dateOfBirth) {
    	Client client = clientService.getDetailsById(clientIdNo);
    	List<CreditApplication> creditList = creditService.listClientCreditApplications(client);
    	
    	if (client != null) {
			return new ResponseEntity<>(creditList, HttpStatus.OK);
		} else {
			ListCreditApplicationsFailResponse response = new ListCreditApplicationsFailResponse();
			response.setMessage("Invalid parameter(s)");
			return ResponseEntity.badRequest().body(response);
		}
    }

}
