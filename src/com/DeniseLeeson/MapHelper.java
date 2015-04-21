package com.DeniseLeeson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapHelper {

    public static String getKeyFromValue(Double value, Map<String,Double> map) {
        String key = "";
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                key = entry.getKey();
            }
        }
        return key;
    }

    public static List<String> getKeys(Map map) {
        return new ArrayList<String>(map.keySet());
    }

    public static List<String> getKeys(List<Money> monies){
        List<String> keys = new ArrayList<String>();
        for (Money money : monies) {
            keys.add(money.getDescription());
        }
        return keys;
    }

    public static List<Double> getValues(List<Money> monies) {
        List<Double> values = new ArrayList<Double>();
        for (Money money : monies) {
            values.add(money.getValue());
        }
        return values;
    }


}
