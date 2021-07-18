package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Broker;

@Repository
public interface BrokerRepository extends CrudRepository<Broker, Integer> {
	
	Broker findByName(String name);

}
