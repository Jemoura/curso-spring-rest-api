package com.algaworks.osworks.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.BusinessRuleException;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> listAll() {
		return clientRepository.findAll();
	}
	
	public Optional<Client> fetch(Long clientId) {
		Optional<Client> client = clientRepository.findById(clientId);		
		return client;
	}
	
	public Client insert(Client client) {
		
		Client existingClient = clientRepository.findByEmail(client.getEmail());
		
		if (existingClient != null && !existingClient.equals(client)) {
			throw new BusinessRuleException("Já existe um cliente cadastrado com este e-mail.");
		}
		
		return clientRepository.save(client);		
	}
	
	public Client update(Long clientId, Client client) {
		client.setId(clientId);
		client = clientRepository.save(client);
		return client;
	}
	
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}

}
