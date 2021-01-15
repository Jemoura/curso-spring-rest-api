package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.ClientInputModel;
import com.algaworks.osworks.api.model.ClientModel;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.repository.ClientRepository;
import com.algaworks.osworks.domain.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<ClientModel> listAll() {
		return toCollectionModel(clientService.listAll());		
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<ClientModel> fetch(@PathVariable Long clientId) {
		Optional<Client> client = clientService.fetch(clientId);
		
		if(client.isPresent()) {
			ClientModel clientModel = toModel(client.get());
			return ResponseEntity.ok(clientModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientModel insert(@Valid @RequestBody ClientInputModel clientInputModel) {
		Client client = toEntity(clientInputModel);
		
		return toModel(clientService.insert(client));		
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<ClientModel> update(@Valid @PathVariable Long clientId, @RequestBody ClientInputModel clientInputModel) {
		Client client = toEntity(clientInputModel);
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
				
		ClientModel clientModel = toModel(clientService.update(clientId, client));
		
		return ResponseEntity.ok(clientModel);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable Long clientId) {
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		clientService.delete(clientId);
		
		return ResponseEntity.noContent().build();
	}	
	
	private ClientModel toModel(Client client) {
		return modelMapper.map(client, ClientModel.class);		
	}
	
	private List<ClientModel> toCollectionModel(List<Client> clients) {
		return clients.stream().map(client -> toModel(client)).collect(Collectors.toList());
	}
	
	private Client toEntity(ClientInputModel clientInputModel) {
		return modelMapper.map(clientInputModel, Client.class);
	}
	
}
