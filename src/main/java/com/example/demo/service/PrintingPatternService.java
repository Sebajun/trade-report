package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.PrintingPattern;
import com.example.demo.repository.PrintingPatternRepository;

@Service
public class PrintingPatternService {
	
	private PrintingPatternRepository printingPatternRepository;
	
	public PrintingPatternService(PrintingPatternRepository printingPatternRepository) {
		super();
		this.printingPatternRepository = printingPatternRepository;
	}

	public PrintingPattern findPrintingPattern(final int productId, final int brokerId) {
		return this.printingPatternRepository.findByBroker_IdAndProduct_Id(brokerId, productId);
		
	}
	
}
