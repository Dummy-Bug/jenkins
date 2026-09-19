package com.lab.jenkins;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public double calculateDiscount(double price, double discountPercent) {
        if (discountPercent >= 100) {
            throw new IllegalArgumentException("Discount must be less than 100%");
        }
        return price - (price * discountPercent / 100);
    }
}
