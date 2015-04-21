package com.DeniseLeeson;

import java.util.*;

public abstract class VendingMachine {

    private List<Item> items;
    private MessageCollector messageCollector;
    private MoneyBag moneyBag;

    public List<Money> moneyAccepted;
    public static final Map<String, Double> moneyMap = new HashMap<String, Double>(){{
        put(Constants.NICKEL, .05);
        put(Constants.DIME, .10);
        put(Constants.QUARTER, .25);
        put(Constants.DOLLAR, 1.00);
    }};

    public static final Hashtable<String, Double> validMoney= new Hashtable<String, Double>(moneyMap);

    public VendingMachine(MessageCollector messageCollector) {
        setMessageCollector(messageCollector);
        createItems();
        moneyAccepted = new ArrayList<Money>();
        moneyBag = new MoneyBagImpl();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public abstract Item getItem(String selector);

    public MessageCollector getMessageCollector() {
        return messageCollector;
    }

    public void setMessageCollector(MessageCollector messageCollector) {
        this.messageCollector = messageCollector;
    }

    public abstract String acceptMoney(Double money);

    public abstract String returnMoney();

    private void createItems() {
        items = new ArrayList<Item>();
        items.add(new ItemImpl("A",.65, 10));
        items.add(new ItemImpl("B",1.00, 9));
        items.add(new ItemImpl("C",1.50, 8));
    }

    public abstract String selectItem(String selection);

    public abstract void setInventory(List<Item> items);
}
