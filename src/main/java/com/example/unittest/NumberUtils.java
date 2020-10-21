package com.example.unittest;

import java.text.NumberFormat;

public class NumberUtils {
	
	public NumberUtils() {}
	
	public boolean isNumber(Object o) {
		return o instanceof Integer || o instanceof Double;
	}
	
	public Integer doubleToInt(Object o) {
		if(o instanceof Double) {
			return ((Double) o).intValue();
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public void printAsCurrencyFormat(Double number) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(number);
		System.out.println(moneyString);
	}

}
