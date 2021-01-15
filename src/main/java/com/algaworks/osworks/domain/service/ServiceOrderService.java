package com.algaworks.osworks.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.api.model.Comment;
import com.algaworks.osworks.domain.exception.BusinessRuleException;
import com.algaworks.osworks.domain.exception.EntityNotFoundException;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.model.ServiceOrderStatus;
import com.algaworks.osworks.domain.repository.ClientRepository;
import com.algaworks.osworks.domain.repository.CommentRepository;
import com.algaworks.osworks.domain.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService {
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public ServiceOrder create(ServiceOrder serviceOrder) {
		
		Client client = clientRepository.findById(serviceOrder.getClient().getId())
				.orElseThrow(() -> new BusinessRuleException("Cliente não encontrado."));
		
		serviceOrder.setClient(client);
		serviceOrder.setStatus(ServiceOrderStatus.OPEN);
		serviceOrder.setOpeningDate(OffsetDateTime.now());
		
		return serviceOrderRepository.save(serviceOrder);
	}
	
	public List<ServiceOrder> listAll() {
		return serviceOrderRepository.findAll();
	}
	
	public Optional<ServiceOrder> fetch(Long serviceOrderId) {
		Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(serviceOrderId);
		return serviceOrder;		
	}
	
	public Comment addComment(Long serviceOrderId, String description) {
		ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada."));
		
		Comment comment = new Comment();
		comment.setDescription(description);
		comment.setSendingDate(OffsetDateTime.now());
		comment.setServiceOrder(serviceOrder);
		
		return commentRepository.save(comment);		
	}

}
