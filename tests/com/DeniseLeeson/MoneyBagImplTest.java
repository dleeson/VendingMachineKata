package com.DeniseLeeson;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoneyBagImplTest {
    MoneyBag moneyBag;

    @Before
    public void setUp() {
       moneyBag = new MoneyBagImpl();
       createMoneyBag();
    }

    private void createMoneyBag() {
        Money money = new MoneyImpl(Constants.NICKEL,.05);
        money.setCount(4);
        moneyBag.add(money);
        money = new MoneyImpl(Constants.QUARTER,.25);
        money.setCount(5);
        moneyBag.add(money);
    }

    @Test
    public void getDominationTotal() throws Exception {
        Double expectedTotal = 1.25;
        assertEquals(expectedTotal, moneyBag.getDominationTotal(Constants.QUARTER));
    }

    @Test
    public void getTotal() throws Exception {
        Double expectedTotal = 1.45;
        assertEquals(expectedTotal, moneyBag.getTotal());
    }

}