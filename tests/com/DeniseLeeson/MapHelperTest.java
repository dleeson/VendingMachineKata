package com.DeniseLeeson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapHelperTest {

    private Map map = new HashMap<String, Double>() {{
        put(Constants.NICKEL, .05);
        put(Constants.DIME, .10);
        put(Constants.QUARTER, .25);
        put(Constants.DOLLAR, 1.00);
    }};


    @Test
    public void getKeyFromValueReturnsKey() {
        assertEquals(Constants.DIME, MapHelper.getKeyFromValue(.10, map));
    }

    @Test
    public void getKeyFromValueReturnsEmptyWhenKeyNotFound() {
        assertEquals("", MapHelper.getKeyFromValue(.50, map));
    }

    @Test
    public void getKeysFromMappedList() {
        List<String> expectedKeys = new ArrayList<String>();
        expectedKeys.add(Constants.NICKEL);
        expectedKeys.add(Constants.DIME);
        expectedKeys.add(Constants.QUARTER);
        expectedKeys.add(Constants.DOLLAR);

        assertEquals(4, MapHelper.getKeys(map).size());
        assertTrue(expectedKeys.containsAll(MapHelper.getKeys(map)));
    }
}