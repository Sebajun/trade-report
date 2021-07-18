package com.example.demo.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.demo.model.PrintingPattern;
import com.example.demo.model.Trade;

/**
 * Printer class used to print a list of trade given print pattern
 * 
 * @author Camille
 *
 */
public class Printer {

	private static final String SPLIT_TOKEN = ",";
	private static final String NULL_TOKEN = "<null>";

	/**
	 * Print a list of trade given a print pattern and a list of trades
	 * 
	 * @param pattern
	 * @param trades
	 * @return
	 */
	public static String printTrades(PrintingPattern pattern, List<Trade> trades) {

		if (pattern == null || trades == null || trades.isEmpty()) {
			return "";
		}

		List<String> fieldsToPrint = filterTradeList(pattern.getFieldsToPrint());

		StringBuilder sb = new StringBuilder();
		for (Trade trade : trades) {
			JSONObject tradeAsJson = new JSONObject(trade);
			for (String field : fieldsToPrint) {
				sb.append(getFieldFromJson(tradeAsJson, field));
				sb.append(pattern.getSeparator());
			}
			sb.deleteCharAt(sb.length() - 1);
			if (!sb.isEmpty()) {
				sb.append("\n");
			}
		}
		sb.insert(0, pattern.getHeaders() + "\n");
		return sb.toString();
	}

	/**
	 * Transform the given list of fields into a List
	 * 
	 * @param fieldList
	 * @return
	 */
	private static List<String> filterTradeList(String fieldList) {

		String[] fieldArray = fieldList.split(SPLIT_TOKEN);

		Stream<String> fieldStream = Arrays.stream(fieldArray);

		List<String> filteredFields = fieldStream.map(field -> field.trim()).collect(Collectors.toList());

		return filteredFields;
	}

	/**
	 * Return the desired field from a JSON object, method handle inner field
	 * retrieval for nested json object.
	 * 
	 * @param trade
	 * @param field
	 * @return
	 */
	private static String getFieldFromJson(JSONObject trade, String field) {
		try {
			if (field.contains(".")) {
				String innerField = getInnerValue(trade, field);
				return innerField;
			} else if (!StringUtils.isBlank(field)) {
				return Optional.of(trade.get(field).toString()).orElse(NULL_TOKEN);
			} else {
				return "";
			}
		} catch (JSONException e) {
			// log error
			return NULL_TOKEN;
		}
	}

	/**
	 * Recursive method to find the desired nested value in a json object. ie
	 * trade.product.id will return the id
	 * 
	 * @param trade
	 * @param tradeField
	 * @return
	 */
	private static String getInnerValue(JSONObject trade, String tradeField) {

		if (!tradeField.contains(".")) {
			return trade.get(tradeField).toString();
		}

		String innerKey = tradeField.substring(0, tradeField.indexOf("."));
		String remaining = tradeField.substring(tradeField.indexOf(".") + 1);

		return getInnerValue(trade.getJSONObject(innerKey), remaining);
	}

}
