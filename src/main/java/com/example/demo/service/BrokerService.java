package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Broker;
import com.example.demo.repository.BrokerRepository;

@Service
public class BrokerService {
	
	private BrokerRepository brokerRepository;

	public BrokerService(BrokerRepository brokerRepository) {
		super();
		this.brokerRepository = brokerRepository;
	}
	
	public Broker findByName(String name) {
		return brokerRepository.findByName(name);
	}

}
