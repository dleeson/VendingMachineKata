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
        money.addTotal(.20);
        moneyBag.add(money);
        money = new MoneyImpl(Constants.QUARTER,.25);
        money.addTotal(1.25);
        moneyBag.add(money);
    }

    @Test
    public void getDominationTotal() throws Exception {
        Double expectedTotal = 1.50;
        assertEquals(expectedTotal, moneyBag.getDominationTotal(Constants.QUARTER));
    }

    @Test
    public void getTotal() throws Exception {
        Double expectedTotal = 1.75;
        assertEquals(expectedTotal, moneyBag.getTotal());
    }

}