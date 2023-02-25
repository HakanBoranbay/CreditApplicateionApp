package com.hakanboranbay.creditapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.request.ClientCreateRequest;
import com.hakanboranbay.creditapp.request.ClientUpdateRequest;
import com.hakanboranbay.creditapp.request.DeleteClientRequest;
import com.hakanboranbay.creditapp.responses.ClientCreateFailResponse;
import com.hakanboranbay.creditapp.responses.ClientCreateSuccessResponse;
import com.hakanboranbay.creditapp.responses.ClientDeleteResponse;
import com.hakanboranbay.creditapp.responses.ClientUpdateFailResponse;
import com.hakanboranbay.creditapp.responses.ClientUpdateSuccessfulResponse;
import com.hakanboranbay.creditapp.service.ClientService;

@RestController
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/clients")
	public ResponseEntity<?> create(@RequestBody ClientCreateRequest request) {
		Client client = clientService.createClient(request);

		if (client != null) {
			ClientCreateSuccessResponse response = new ClientCreateSuccessResponse();
			response.setMessage("Account created.");
			response.setAccountIdNo(client.getIdNo());
			return ResponseEntity.ok(response);
		} else {
			ClientCreateFailResponse response = new ClientCreateFailResponse();
			response.setMessage("Invalid parameter(s)");
			return ResponseEntity.badRequest().body(response);
		}

	}
	
	@PostMapping("/client")
	public ResponseEntity<?> updateCreditScore(@RequestBody ClientUpdateRequest request) {
		Client client = clientService.getDetailsById(request.getIdNo());
		clientService.updateClientCreditScore(client, request.getCreditScore(), request.getMonthlyIncome());
		if (client != null) {
			ClientUpdateSuccessfulResponse response = new ClientUpdateSuccessfulResponse();
			response.setMessage("Update successful.");
			response.setIdNo(client.getIdNo());
			response.setCreditScore(request.getCreditScore());
			response.setMonthlyIncome(request.getMonthlyIncome());
			return ResponseEntity.ok(response);
		} else {
			ClientUpdateFailResponse response = new ClientUpdateFailResponse();
			response.setMessage("Update failed: invalid parameter(s)");
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping("/client")
	public ResponseEntity<?> deleteClient(@RequestBody DeleteClientRequest request) {
		Client client = clientService.getDetailsById(request.getIdNo());
		clientService.deleteClient(client);
		ClientDeleteResponse response = new ClientDeleteResponse();
		if (client != null) {
			response.setMessage("Client with id " + request.getIdNo() + "is deleted successfully.");
			return ResponseEntity.ok(response);
		} else {
			response.setMessage("Client with id " + request.getIdNo() + "does not exist.");
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}
