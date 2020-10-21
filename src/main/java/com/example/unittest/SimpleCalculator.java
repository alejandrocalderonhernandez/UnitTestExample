package com.example.unittest;

import java.util.concurrent.TimeUnit;

public class SimpleCalculator {
	
	private NumberUtils numberValidator;
		
	public SimpleCalculator(NumberUtils numberValidator) {
		this.numberValidator = numberValidator;
	}
	
	public Integer addMock(Object arg1, Object arg2) {
		if(this.numberValidator.isNumber(arg1) && this.numberValidator.isNumber(arg2)) {
			return (Integer) arg1 + (Integer) arg2;
		}
		throw new IllegalArgumentException();
	}
	
	public Integer addRoundOut(Object arg1, Object arg2) {
		return numberValidator.doubleToInt(arg1) +  numberValidator.doubleToInt(arg2); 
	}
	
	public Integer add(Integer arg1, Integer arg2) {
		return arg1 + arg2;
	}
	
	public Integer substract(Integer arg1, Integer arg2) {
		return arg1 - arg2;
	}
	
	public Integer multiply(Integer arg1, Integer arg2) {
		return arg1 * arg2;
	}
	
	public Double divide(Integer arg1, Integer arg2) {
		if(arg2 == 0) {
			throw new ArithmeticException("Cant divide between zero");
		}
		return (double) arg1 / arg2;
	}
	
	public int addWithTimeOut(Integer arg1, Integer arg2) {
		try {
		    TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		return arg1 + arg2;
	}
	
	public Float toFharenheit(Float degree) {
		return (degree * 9 / 5) + 32;
	}
	
	public void printCuurencyFormat(Double number) {
		this.numberValidator.printAsCurrencyFormat(number);
	}

}
