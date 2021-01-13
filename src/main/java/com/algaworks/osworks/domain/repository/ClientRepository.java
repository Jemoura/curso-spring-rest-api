package com.algaworks.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
