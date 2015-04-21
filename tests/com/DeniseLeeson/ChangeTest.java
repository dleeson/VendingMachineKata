package com.DeniseLeeson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeTest {


    @Test
    public void canCalculateChange() {
        Double moneyLeft = 1.65;
        int actualDollars = 0;
        int actualQuarters = 0;
        int actualDimes = 0;
        int actualNickels = 0;
        int expectedDollars = 1;
        int expectedQuarters = 2;
        int expectedDimes = 1;
        int expectedNickels = 1;

        Change change = new Change(moneyLeft, actualDollars, actualQuarters, actualDimes, actualNickels);

        change.calculateChange();
        assertEquals(expectedDollars, change.getDollarCount());
        assertEquals(expectedQuarters,change.getQuarterCount());
        assertEquals(expectedDimes, change.getDimeCount());
        assertEquals(expectedNickels, change.getNickelCount());
    }
}
