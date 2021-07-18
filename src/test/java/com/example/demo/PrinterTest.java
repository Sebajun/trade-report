package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Broker;
import com.example.demo.model.PrintingPattern;
import com.example.demo.model.Product;
import com.example.demo.model.Trade;
import com.example.demo.util.Printer;

class PrinterTest {

	public static final String expected = "tradeRef,productId,productName,tradeDate,qty,buySell,price\nT-FWD-1,1,AUDNZD FRD Exp14Jul2021,20200408,1000000,B,1.067591\n";
	public Product product;
	public Broker broker;
	public PrintingPattern pattern;
	public Trade trade;

	@BeforeEach
	public void setup() {
		product = new Product();
		product.setName("AUDNZD FRD Exp14Jul2021");
		product.setId(1);
		broker = new Broker();
		broker.setName("test_broker");
		broker.setId(1);
		pattern = new PrintingPattern();
		pattern.setBroker(broker);
		pattern.setProduct(product);
		pattern.setId(1);
		pattern.setHeaders("tradeRef,productId,productName,tradeDate,qty,buySell,price");
		pattern.setFieldsToPrint("tradeRef, product.id, product.name, tradeDate, qty,buySell, price");
		pattern.setSeparator(",");
		trade = new Trade();
		trade.setBroker(broker);
		trade.setBuySell("B");
		trade.setId(1);
		trade.setPrice(1.067591f);
		trade.setProduct(product);
		trade.setQty(1000000);
		trade.setTradeDate("20200408");
		trade.setTradeRef("T-FWD-1");
	}

	@Test
	void test_both_null() {
		Printer.printTrades(null, null);
	}

	@Test
	void test_tradeList_null() {
		PrintingPattern p = new PrintingPattern();
		Printer.printTrades(p, null);
	}

	@Test
	void test_pattern_null() {
		ArrayList<Trade> trades = new ArrayList<>();
		Printer.printTrades(null, trades);
	}

	@Test
	void test_pattern_all_empty_fields() {
		List<Trade> trades = new ArrayList<>();
		trades.add(trade);
		pattern.setFieldsToPrint("");
		String expect = "tradeRef,productId,productName,tradeDate,qty,buySell,price\n";
		Assertions.assertEquals(expect, Printer.printTrades(pattern, trades));
	}

	@Test
	void test_trade_has_null_value() {
		trade.setBuySell(null);
		trade.setTradeDate(null);
		trade.setTradeRef(null);
		List<Trade> trades = new ArrayList<>();
		trades.add(trade);
		String expect = "tradeRef,productId,productName,tradeDate,qty,buySell,price\n<null>,1,AUDNZD FRD Exp14Jul2021,<null>,1000000,<null>,1.067591\n";
		Assertions.assertEquals(expect, Printer.printTrades(pattern, trades));
	}

	@Test
	void test_pattern_contains_empty_field() {
		List<Trade> trades = new ArrayList<>();
		trades.add(trade);
		pattern.setFieldsToPrint("tradeRef, product.id,,, qty,buySell, price");
		String expect = "tradeRef,productId,productName,tradeDate,qty,buySell,price\nT-FWD-1,1,,,1000000,B,1.067591\n";
		Assertions.assertEquals(expect, Printer.printTrades(pattern, trades));
	}

	@Test
	void test_success() {
		List<Trade> trades = new ArrayList<>();
		trades.add(trade);
		Assertions.assertEquals(expected, Printer.printTrades(pattern, trades));
	}

}
