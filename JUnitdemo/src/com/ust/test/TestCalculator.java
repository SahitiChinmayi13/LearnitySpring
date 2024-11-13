package com.ust.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TestCalculator {

@Test
void testAdd() {
	Calculator calculator=new Calculator();
	int expected=10+20;
	int actual=calculator.add(10,20);
//	assertEquals(expected,actual);
	boolean result=(actual==expected);
	assertTrue(result);
}

}
