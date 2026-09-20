package com.lab.jenkins.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void addsTwoNumbers() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void subtractsTwoNumbers() {
        assertEquals(6, calculator.subtract(10, 4));
    }

    @Test
    void multipliesTwoNumbers() {
        assertEquals(20, calculator.multiply(10, 2));
    }
}
