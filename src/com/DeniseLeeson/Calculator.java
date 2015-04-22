package com.DeniseLeeson;

import java.util.List;

public class Calculator {
    public static Double addAmounts(List<Double> amounts) {
        Double total = Constants.ZERO_AMOUNT;
        for (Double amount : amounts) {
            total += amount;
        }
        return total;
    }
}
