package com.algaworks.osworks.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.service.ServiceOrderService;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrder create(@Valid @RequestBody ServiceOrder serviceOrder) {
		return serviceOrderService.create(serviceOrder);
	}

}
