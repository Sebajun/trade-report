package com.example.demo.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.demo.model.Trade;
import com.example.demo.repository.TradeRepository;

@Service
public class TradeService {

	private TradeRepository tradeRepository;

	public TradeService(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	// trouver les ordres par nom de produit, broker et date
	public Stream<Trade> findTrades(int productId, int brokerId, String tradeDate) {
		return tradeRepository.findByBroker_IdAndProduct_IdAndTradeDate(brokerId, productId, tradeDate);
	}

}
