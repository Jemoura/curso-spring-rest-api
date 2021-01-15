package com.algaworks.osworks.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ServiceOrderInputModel {
	
	@Valid
	@NotNull
	private ClientIdInputModel clientId;
	
	@NotBlank
	private String description;
	
	@NotNull
	private BigDecimal price;
	
	public ClientIdInputModel getClientId() {
		return clientId;
	}
	
	public void setClientId(ClientIdInputModel clientId) {
		this.clientId = clientId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}	

}
