package com.DeniseLeeson;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineImpl extends VendingMachine {

    public VendingMachineImpl(MessageCollector messageCollector) {
        super(messageCollector);

    }

    @Override
    public Item getItem(String selector) {
        String searchCriteria = selector.substring(selector.length() -1, selector.length());
        Item itemFound = null;
        for (Item item : getItems()) {
            if (item.getSelector().equals(searchCriteria)) {
                itemFound = item;
                break;
            }
        }
        return itemFound;
    }

    @Override
    public String acceptMoney(Double money) {
        if (isMoneyValid(money)) {
            storeAcceptedMoney(money);
            return MessageFormatHelper.formatResult(money, moneyMap);
        }

        return String.format(Constants.INVALID_MONEY, money);
    }

    @Override
    public String returnMoney() {
        if (moneyAccepted.size() == 0) {
            return Constants.NO_COINS_TO_RETURN;
        }
        String message = MessageFormatHelper.formatResult(MapHelper.getKeys(moneyAccepted));
        message += Constants.SPACER + Constants.COIN_RETURN;
        getMessageCollector().add(message);
        return message;
    }

    @Override
    public String selectItem(String selection) {
       String message = MessageFormatHelper.formatItemSelected(selection);
       getMessageCollector().add(message);

        List<Double> costs = new ArrayList<Double>(MapHelper.getValues(moneyAccepted));

        Double totalAcceptedMoney = Calculator.addAmounts(costs);

        Item item = getItem(selection);
        if (item == null) {
            getMessageCollector().add(Constants.ITEM_DOES_NOT_EXIST);
            return MessageFormatHelper.formatResult(getMessageCollector().getMessages());
        }

        if (totalAcceptedMoney == 0.00) {
            return item.getCost().toString();
        }

        if (item.getCount() < 1) {
          getMessageCollector().add(Constants.ITEM_SOLD_OUT);
          return MessageFormatHelper.formatResult(getMessageCollector().getMessages());
        }

        Double moneyLeft = totalAcceptedMoney - item.getCost();
        if (moneyLeft < 0.00) {
            moneyLeft *= -1.0;
            String notEnough = String.format(Constants.NOT_ENOUGH_MONEY, moneyLeft);
            getMessageCollector().add(notEnough);
            return MessageFormatHelper.formatResult(getMessageCollector().getMessages());
        }

        item.setCount(item.getCount() - 1);
        if (moneyLeft == 0.00) {
            getMessageCollector().add(item.getSelector());
            return MessageFormatHelper.formatResult(getMessageCollector().getMessages());
        }


        Change change = new Change(moneyLeft, 0, 0, 0, 0);
        change.calculateChange();
        MessageFormatHelper.formatReturnChange(change, getMessageCollector(), item.getSelector());

        return MessageFormatHelper.formatResult(getMessageCollector().getMessages());

    }

    @Override
    public void setInventory(List<Item> items) {
        Item itemFound = null;
        for (Item item : items) {
            itemFound = getItem(item.getSelector());
            if (itemFound != null) {
                updateItem(item, itemFound);
            } else {
                getItems().add(item);
            }
        }
    }

    private void updateItem(Item sourceItem, Item targetItem){
        targetItem.setCount(sourceItem.getCount());
        targetItem.setSelector(sourceItem.getSelector());
        targetItem.setCost(sourceItem.getCost());
    }

    private boolean isMoneyValid(Double money) throws RuntimeException {
        if (validMoney.contains(money)) return true;
        return false;
    }

    private void storeAcceptedMoney(Double acceptedMoney) {
        Money money = new MoneyImpl(MapHelper.getKeyFromValue(acceptedMoney,moneyMap),acceptedMoney);
        moneyAccepted.add(money);
    }

}
