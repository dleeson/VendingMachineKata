package com.DeniseLeeson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemImplTest {

    @Test
    public void hasDescriptonAndCostAndCount() {
        Double cost = .65;
        Item item = new ItemImpl("A", cost,10);

        assertEquals(cost, item.getCost());
        assertEquals("A", item.getSelector());
        assertEquals(10, item.getCount());
    }
}
