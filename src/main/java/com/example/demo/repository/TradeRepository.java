package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Trade;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Integer> {
	
	List<Trade> findByBroker_IdAndProduct_IdAndTradeDate(int brokerId, int productId, String TradeDate);

}
