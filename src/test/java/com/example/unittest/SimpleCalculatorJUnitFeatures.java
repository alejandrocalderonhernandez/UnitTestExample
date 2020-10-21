package com.example.unittest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SimpleCalculatorJUnitFeatures {
	
	private static SimpleCalculator calculator;
	
	@BeforeAll
	public static void init() {
		System.out.println("Start test");
		SimpleCalculatorJUnitFeatures.calculator = new SimpleCalculator(new NumberUtils());
	}
	
	@AfterAll
	public static void finish() {
		System.out.println("End test");
		SimpleCalculatorJUnitFeatures.calculator = null;	
	}
	

	@Test
	public void calculator_dont_be_null() {
		assertNotNull(SimpleCalculatorJUnitFeatures.calculator);
	}
	
	@Test
	public void test_add_method_valid_input() {
		Integer result = SimpleCalculatorJUnitFeatures.calculator.add(10, 20);
		assertEquals(30, result);
	}
	
	@Test
	public void test_divide_method_valid_input() {
		Double result = SimpleCalculatorJUnitFeatures.calculator.divide(10, 7);
		assertEquals(1.4, result, 0.5);
	}
	
	@Test
	public void test_divide_method_expected_exception() {
		assertThrows(ArithmeticException.class, () -> SimpleCalculatorJUnitFeatures.calculator.divide(10, 0));
	}
	
	@Disabled("This is a example the invalid test")
	@Test
	public void test_divide_method_valid_input_dissabled() {
		Double result = SimpleCalculatorJUnitFeatures.calculator.divide(10, 0);
		assertEquals(1.4, result, 0.5);
	}
	
	@DisplayName("Test Full Class")
	@Test
	public void test_all_methods_calculator_class_valid_input() {
		assertAll(
				() -> assertEquals(10, SimpleCalculatorJUnitFeatures.calculator.add(8, 2)),
				() -> assertEquals(10, SimpleCalculatorJUnitFeatures.calculator.substract(17, 7)),
				() -> assertEquals(10, SimpleCalculatorJUnitFeatures.calculator.multiply(2, 5)),
				() -> assertEquals(10, SimpleCalculatorJUnitFeatures.calculator.divide(10, 1))
				
		);
	} 
	
	@ParameterizedTest(name = "{index} => a={0}, b={1}, result= {2}")
	@MethodSource("addProviderData")
	public void test_add_method_valid_input_Parameterized(int a, int b, int expected) {
		assertEquals(expected, SimpleCalculatorJUnitFeatures.calculator.add(a, b));
	}
	
	@ParameterizedTest(name = "{index} => a={0}, b={1}, result= {2}")
	@MethodSource("multiplyProviderData")
	public void test_multiply_method_valid_input_Parameterized(int a, int b, int expected) {
		assertEquals(expected, SimpleCalculatorJUnitFeatures.calculator.multiply(a, b));
	}
	
	@Test
	public void test_add_with_time_out() {
		assertTimeout(Duration.ofSeconds(10), () -> 
				assertEquals(19, SimpleCalculatorJUnitFeatures.calculator.addWithTimeOut(10, 9)));
	}
	
	@Test
	public void test_to_fharenheit_with_valid_input() {
		assertEquals(-9.4F, SimpleCalculatorJUnitFeatures.calculator.toFharenheit(-23F), 0.01);
	}

	private static Stream<Arguments> addProviderData() {
		return Stream.of(
				Arguments.of(6,2,8),
				Arguments.of(7,7,14),
				Arguments.of(21,7,28),
				Arguments.of(30,15,45)
		);
	}
	
	private static Stream<Arguments> multiplyProviderData() {
		return Stream.of(
				Arguments.of(6,2,12),
				Arguments.of(7,7,49),
				Arguments.of(8,8,64),
				Arguments.of(21,7,147)
		);
	}
	
}
