package com.algaworks.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ServiceOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@ManyToOne	
	private Client client;
	
	private String description;
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private ServiceOrderStatus status;
	
	private OffsetDateTime dateOpened;	
	private OffsetDateTime dateClosed;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
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
	
	public OffsetDateTime getOpeningDate() {
		return dateOpened;
	}
	
	public void setOpeningDate(OffsetDateTime dateOpened) {
		this.dateOpened = dateOpened;
	}
	
	public OffsetDateTime getClosureDate() {
		return dateClosed;
	}
	
	public void setClosureDate(OffsetDateTime dateClosed) {
		this.dateClosed = dateClosed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceOrder other = (ServiceOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
