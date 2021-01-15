package com.algaworks.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.osworks.domain.model.ServiceOrderStatus;

public class ServiceOrderModel {
	
	private Long id;
	private ClientReducedModel client;
	private String description;
	private BigDecimal price;
	private ServiceOrderStatus status;
	private OffsetDateTime dateOpened;
	private OffsetDateTime dateClosed;
	
	public Long getIdLong() {
		return id;
	}
	
	public void setIdLong(Long id) {
		this.id = id;
	}		
	
	public ClientReducedModel getClient() {
		return client;
	}

	public void setClient(ClientReducedModel client) {
		this.client = client;
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
	
	public ServiceOrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(ServiceOrderStatus status) {
		this.status = status;
	}
	
	public OffsetDateTime getDateOpened() {
		return dateOpened;
	}
	
	public void setDateOpened(OffsetDateTime dateOpened) {
		this.dateOpened = dateOpened;
	}
	
	public OffsetDateTime getDateClosed() {
		return dateClosed;
	}
	
	public void setDateClosed(OffsetDateTime dateClosed) {
		this.dateClosed = dateClosed;
	}
	
}
