package com.algaworks.osworks.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.BusinessRuleException;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.model.ServiceOrderStatus;
import com.algaworks.osworks.domain.repository.ClientRepository;
import com.algaworks.osworks.domain.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService {
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public ServiceOrder create(ServiceOrder serviceOrder) {
		
		Client client = clientRepository.findById(serviceOrder.getClient().getId())
				.orElseThrow(() -> new BusinessRuleException("Cliente não encontrado."));
		
		serviceOrder.setClient(client);
		serviceOrder.setStatus(ServiceOrderStatus.OPEN);
		serviceOrder.setOpeningDate(LocalDateTime.now());
		
		return serviceOrderRepository.save(serviceOrder);
	}

}
