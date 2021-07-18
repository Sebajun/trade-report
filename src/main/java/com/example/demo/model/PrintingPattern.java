package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

@Entity
public class PrintingPattern {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
	private Product product;
	@OneToOne(targetEntity = Broker.class, cascade = CascadeType.ALL)
	private Broker broker;
	@Column
	private String fieldsToPrint;
	@Column 
	private String headers;
	@Column @NotNull
	private String separator;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Broker getBroker() {
		return broker;
	}
	public void setBroker(Broker broker) {
		this.broker = broker;
	}
	public String getFieldsToPrint() {
		return fieldsToPrint;
	}
	public void setFieldsToPrint(String fieldsToPrint) {
		this.fieldsToPrint = fieldsToPrint;
	}
	public String getHeaders() {
		return headers;
	}
	public void setHeaders(String headers) {
		this.headers = headers;
	}
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}

}
