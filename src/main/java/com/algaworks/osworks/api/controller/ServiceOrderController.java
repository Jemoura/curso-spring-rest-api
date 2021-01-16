package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.algaworks.osworks.api.model.ServiceOrderInputModel;
import com.algaworks.osworks.api.model.ServiceOrderModel;
import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.service.ServiceOrderService;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrderModel create(@Valid @RequestBody ServiceOrderInputModel serviceOrderInputModel) {
		ServiceOrder serviceOrder = toEntity(serviceOrderInputModel);
		
		return toModel(serviceOrderService.create(serviceOrder));
	}
	
	@GetMapping
	public List<ServiceOrderModel> listAll() {
		return toCollectionModel(serviceOrderService.listAll());
	}
	
	@GetMapping("/{serviceOrderId}")
	public ResponseEntity<ServiceOrderModel> fetch(@PathVariable Long serviceOrderId) {
		Optional<ServiceOrder> serviceOrder = serviceOrderService.fetch(serviceOrderId);
		if(serviceOrder.isPresent()) {
			ServiceOrderModel serviceOrderModel = toModel(serviceOrder.get());				
			return ResponseEntity.ok(serviceOrderModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{serviceOrderId}/finalize")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalize(@PathVariable Long serviceOrderId) {
		serviceOrderService.finalize(serviceOrderId);
	}
	
	private ServiceOrderModel toModel(ServiceOrder serviceOrder ) {
		return modelMapper.map(serviceOrder, ServiceOrderModel.class);		 		
	}
	
	private List<ServiceOrderModel> toCollectionModel(List<ServiceOrder> serviceOrders) {
		return serviceOrders.stream().map(serviceOrder -> toModel(serviceOrder)).collect(Collectors.toList());
	}
	
	private ServiceOrder toEntity(ServiceOrderInputModel serviceOrderInputModel) {
		return modelMapper.map(serviceOrderInputModel, ServiceOrder.class);
	}

}
