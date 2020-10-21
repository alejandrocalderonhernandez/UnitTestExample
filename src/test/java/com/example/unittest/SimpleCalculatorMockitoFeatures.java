package com.example.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

public class SimpleCalculatorMockitoFeatures {
	
	@Mock
	private NumberUtils validator;
	
	@InjectMocks
	private SimpleCalculator calculator;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_add_with_happy_path_simple_test() {
		Integer input = Integer.valueOf(5);
		when(this.validator.isNumber(input)).thenReturn(true);
		Integer result = this.calculator.addMock(input, input);
		assertEquals(10, result);
	}
	
	@Test
	public void test_add_with_unhappy_path() {
		Integer input = Integer.valueOf(5);
		when(this.validator.isNumber(input)).thenReturn(false);
		assertThrows(IllegalArgumentException.class, () -> this.calculator.addMock(input, input));
	}
	
	@Test
	public void test_is_number_with_happy_path_call_real_method_feature() {
		when(this.validator.isNumber(5)).thenCallRealMethod();
		assertEquals(true,  this.validator.isNumber(5));
	}
	
	
	@Test
	public void test_double_to_int_path_then_anser_feature() {
		Answer<Integer> answer = (mock) -> {
			return 10;
		};
		when(this.validator.doubleToInt(10.97)).thenAnswer(answer);
		assertEquals(10, (this.validator.doubleToInt(10.97)));
	}
	
	@Test
	public void test_add_happy_path_BDD() {
		//Given
		Integer input = Integer.valueOf(10);
		given(this.validator.isNumber(input)).willReturn(true);
		//When
		Integer result = this.calculator.add(input, input);
		//Then
		assertEquals(20, result);
	}
	
	@Test
	public void test_add_happy_path_argument_matcher_feature() {
		given(this.validator.isNumber(anyInt())).willReturn(true);
		Integer result = this.calculator.add(4, 4);
		assertEquals(8, result);
	}
	
	@Test
	public void test_print_as_currency_format_verify_feature() {
	 Double input = 12.5;
	  this.calculator.printCuurencyFormat(input);
	  verify(this.validator, times(1)).printAsCurrencyFormat(input);
	}
}
