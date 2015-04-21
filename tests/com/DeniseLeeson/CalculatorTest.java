package com.DeniseLeeson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CalculatorTest {
    @Test
    public void canAddAListOfValues() {
        List<Double> amounts = new ArrayList<Double>();
        amounts.add(1.50);
        amounts.add(.25);
        amounts.add(.50);
        Double expectedSum = 2.25;
        assertEquals(expectedSum, Calculator.addAmounts(amounts));
    }
}


