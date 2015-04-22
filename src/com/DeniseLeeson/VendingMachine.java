package com.DeniseLeeson;

import java.util.*;

public abstract class VendingMachine {

    private List<Item> items;
    private MessageCollector messageCollector;
    private MoneyBag moneyBag;

    public List<Money> moneyAccepted;
    public static final Map<String, Double> moneyMap = new HashMap<String, Double>(){{
        put(Constants.NICKEL, Constants.NICKEL_AMOUNT);
        put(Constants.DIME, Constants.DIME_AMOUNT);
        put(Constants.QUARTER, Constants.QUARTER_AMOUNT);
        put(Constants.DOLLAR, Constants.DOLLAR_AMOUNT);
    }};

    public static final Hashtable<String, Double> validMoney= new Hashtable<String, Double>(moneyMap);

    public VendingMachine(MessageCollector messageCollector) {
        setMessageCollector(messageCollector);
        createItems();
        moneyAccepted = new ArrayList<Money>();
        moneyBag = new MoneyBagImpl<Money>();
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

    public MoneyBag getMoneyBag() {
        return moneyBag;
    }

    public void setMoneyBag(MoneyBag<Money> moneyBag) {
        MoneyBag<Money> currentMoneyBag = getMoneyBag();
        Money currentMoney;
        for (Money money : moneyBag) {
          currentMoney =  currentMoneyBag.getMoney(money.getDescription());
          if (currentMoney == null) {
              currentMoneyBag.add(money);
          } else {
              currentMoney.setCount(currentMoney.getCount() + money.getCount());
          }
        }
        this.moneyBag = currentMoneyBag;
    }
}
