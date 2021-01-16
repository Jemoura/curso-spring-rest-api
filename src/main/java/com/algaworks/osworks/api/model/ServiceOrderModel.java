package com.algaworks.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.osworks.domain.model.ServiceOrderStatus;

public class ServiceOrderModel {
	
	private Long id;
	private ClientReducedModel client;
	private String description;
	private BigDecimal price;
	private ServiceOrderStatus status;
	private OffsetDateTime dateOpened;
	private OffsetDateTime dateClosed;
	
	private List<CommentModel> comments = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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

	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}	
	
}
