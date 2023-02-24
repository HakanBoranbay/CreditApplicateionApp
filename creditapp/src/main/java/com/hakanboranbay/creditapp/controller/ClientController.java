package com.hakanboranbay.creditapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hakanboranbay.creditapp.jdbc.JdbcRepository;
import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.request.ClientCreateRequest;
import com.hakanboranbay.creditapp.responses.ClientCreateFailResponse;
import com.hakanboranbay.creditapp.responses.ClientCreateSuccessResponse;
import com.hakanboranbay.creditapp.service.ClientService;

@RestController
public class ClientController {
	
	@Autowired
	private JdbcRepository jdbcRepository;
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/clients")
	public ResponseEntity<?> create(@RequestBody ClientCreateRequest request) {
		Client client = clientService.createClient(request);
		jdbcRepository.save(client);

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

	@GetMapping("/clients/{clientIdNo}")
	public ResponseEntity<?> getDetail(@PathVariable String clientIdNo) {
		Client client = this.jdbcRepository.findById(clientIdNo);

		if (client != null) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
