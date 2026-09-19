package com.lab.jenkins;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void appliesDiscount() {
        assertEquals(900.0, calculator.calculateDiscount(1000, 10));
    }

    @Test
    void rejectsDiscountOfHundredPercentOrMore() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateDiscount(1000, 120));
    }
}
