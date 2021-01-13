package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@GetMapping
	public List<Client> listAll() {
		return clientRepository.findAll();		
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> fetch(@PathVariable Long clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
		
		if(client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client insert(@RequestBody Client client) {
		return clientRepository.save(client);		
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@PathVariable Long clientId, @RequestBody Client client) {
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(clientId);
		client = clientRepository.save(client);
		
		return ResponseEntity.ok(client);
	}
	
	public ResponseEntity<Void> delete(@PathVariable Long clientId) {
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		clientRepository.deleteById(clientId);
		
		return ResponseEntity.noContent().build();
	}
}
