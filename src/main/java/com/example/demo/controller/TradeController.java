package com.example.demo.controller;

import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Broker;
import com.example.demo.model.PrintingPattern;
import com.example.demo.model.Product;
import com.example.demo.model.Trade;
import com.example.demo.service.BrokerService;
import com.example.demo.service.PrintingPatternService;
import com.example.demo.service.ProductService;
import com.example.demo.service.TradeService;
import com.example.demo.util.Printer;

@RestController
public class TradeController {

	private TradeService tradeService;
	private PrintingPatternService printingPatternService;
	private ProductService productService;
	private BrokerService brokerService;

	public TradeController(TradeService tradeService, PrintingPatternService printingPatternService,
			ProductService productService, BrokerService brokerService) {
		super();
		this.tradeService = tradeService;
		this.printingPatternService = printingPatternService;
		this.productService = productService;
		this.brokerService = brokerService;
	}

	@Transactional
	@GetMapping("/trade")
	public String findTrades(@RequestParam(name = "product") String productName,
			@RequestParam(name = "broker") String brokerName, @RequestParam String date) {

		Product product = productService.findByName(productName);
		Broker broker = brokerService.findByName(brokerName);

		if (product == null || broker == null) {
			return "";
		}
		Stream<Trade> tradeList = tradeService.findTrades(product.getId(), broker.getId(), date);
		PrintingPattern pattern = printingPatternService.findPrintingPattern(product.getId(), broker.getId());

		return Printer.printTrades(pattern, tradeList);
	}

}
