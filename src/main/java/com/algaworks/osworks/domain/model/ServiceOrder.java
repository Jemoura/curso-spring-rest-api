package com.algaworks.osworks.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.osworks.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
public class ServiceOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
	@NotNull
	@ManyToOne	
	private Client client;
	
	@NotBlank
	private String description;
	
	@NotNull
	private BigDecimal price;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private ServiceOrderStatus status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dateOpened;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dateClosed;
	
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
	
	public LocalDateTime getOpeningDate() {
		return dateOpened;
	}
	
	public void setOpeningDate(LocalDateTime dateOpened) {
		this.dateOpened = dateOpened;
	}
	
	public LocalDateTime getClosureDate() {
		return dateClosed;
	}
	
	public void setClosureDate(LocalDateTime dateClosed) {
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
