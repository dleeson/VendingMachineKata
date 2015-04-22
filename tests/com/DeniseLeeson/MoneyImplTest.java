package com.DeniseLeeson;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MoneyImplTest {
    private Money money;
    @Before
    public void setup() {
        money = new MoneyImpl(Constants.DOLLAR,Constants.DOLLAR_AMOUNT);
    }

    @Test
    public void addsToTotal() throws Exception {
        Double expectedAmount = 6.00;
        money.addTotal(5.00);
        assertEquals(expectedAmount, money.getTotal());

    }

    @Test
    public void subtractsFromTotal() throws Exception {
        Double expectedAmount = 6.00;
        money.setTotal(10.00);
        money.subtractTotal(4.00);
        assertEquals(expectedAmount, money.getTotal());
    }
}