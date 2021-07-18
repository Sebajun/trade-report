package com.example.demo.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PrintingPattern;

@Repository
public interface PrintingPatternRepository extends CrudRepository<PrintingPattern, Integer> {
	
	PrintingPattern findByBroker_IdAndProduct_Id(int brokerId, int productId);

}
