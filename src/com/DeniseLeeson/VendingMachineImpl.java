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
        String message = Constants.COIN_RETURN + Constants.NEWLINE;
        getMessageCollector().add(message);

        getMessageCollector().add(MessageFormatHelper.formatResult(MapHelper.getKeys(moneyAccepted)));
        return MessageFormatHelper.formatResult(getMessageCollector().getMessages());
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

        if (totalAcceptedMoney == Constants.ZERO_AMOUNT) {
            return item.getCost().toString();
        }

        if (item.getCount() < 1) {
          getMessageCollector().add(Constants.ITEM_SOLD_OUT);
          return MessageFormatHelper.formatResult(getMessageCollector().getMessages());
        }

        Double moneyLeft = totalAcceptedMoney - item.getCost();
        if (moneyLeft < Constants.ZERO_AMOUNT) {
            moneyLeft *= -1.0;
            String notEnough = String.format(Constants.NOT_ENOUGH_MONEY, moneyLeft);
            getMessageCollector().add(notEnough);
            return MessageFormatHelper.formatResult(getMessageCollector().getMessages());
        }

        item.setCount(item.getCount() - 1);
        if (moneyLeft == Double.parseDouble("0.0")) {
            getMessageCollector().add(item.getSelector());
            return MessageFormatHelper.formatResult(getMessageCollector().getMessages());
        }


        Change change = new Change(moneyLeft, 0, 0, 0, 0);
        change.calculateChange();

        String adjustmentMessage = adjustMoneyBag(change);
        if (adjustmentMessage.length() > 0) {
            getMessageCollector().add(adjustmentMessage);
            return MessageFormatHelper.formatResult(getMessageCollector().getMessages());
        }

        MessageFormatHelper.formatReturnChange(change, getMessageCollector(), item.getSelector());
        return MessageFormatHelper.formatResult(getMessageCollector().getMessages());

    }

    private String adjustMoneyBag(Change change) {
        String message = "";
        MoneyBag<Money> moneyBag = getMoneyBag();

        int nickelCount = moneyBag.getMoney(Constants.NICKEL).getCount();
        int dimeCount = moneyBag.getMoney(Constants.DIME).getCount();

        int quarterCount = moneyBag.getMoney(Constants.QUARTER).getCount();

        if (change.getNickelCount() > nickelCount ||
            change.getDimeCount() > dimeCount ||
            change.getQuarterCount() > quarterCount) {
            return Constants.NOT_ENOUGH_CHANGE;
        }


        if (change.getNickelCount() > 0) {
            moneyBag.getMoney(Constants.NICKEL).setCount(nickelCount - change.getNickelCount());
        }

        if (change.getDimeCount() > 0) {
            moneyBag.getMoney(Constants.DIME).setCount(dimeCount - change.getDimeCount());
        }

        if (change.getQuarterCount() > 0) {
            moneyBag.getMoney(Constants.QUARTER).setCount(quarterCount - change.getQuarterCount());
        }
        return message;
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
        money.setCount(1);
        moneyAccepted.add(money);
    }

}
