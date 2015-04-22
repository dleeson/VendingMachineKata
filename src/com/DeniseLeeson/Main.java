package com.DeniseLeeson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        run(args);
    }

    private static void run(String[] args) {
        VendingMachineController vendingMachineController = new VendingMachineController();
        try {
            for (String arg : args) {
                if (arg.equals("NICKEL")) {
                    vendingMachineController.acceptMoney(Constants.NICKEL_AMOUNT);
                } else if (arg.equals("DIME")) {
                    vendingMachineController.acceptMoney(Constants.DIME_AMOUNT);
                } else if (arg.equals("QUARTER")) {
                    vendingMachineController.acceptMoney(Constants.QUARTER_AMOUNT);
                } else if (arg.equals("DOLLAR")) {
                    vendingMachineController.acceptMoney(Constants.DOLLAR_AMOUNT);
                } else if (arg.equals("COIN_RETURN")) {
                    vendingMachineController.returnMoney();
                } else if (arg.toLowerCase().contains(";")) {
                    if (arg.toUpperCase().contains(Constants.QUARTER) ||
                        arg.toUpperCase().contains(Constants.DIME) ||
                        arg.toUpperCase().contains(Constants.NICKEL)) {
                        vendingMachineController.setMoneyBag(getMoneyBag(arg));
                        System.out.println(MessageFormatHelper.formatMoneyBag(vendingMachineController.getVendingMachine().getMoneyBag(),vendingMachineController.getVendingMachine().getMessageCollector()));
                    } else {
                        vendingMachineController.setInventory(getItems(arg));
                    }
                } else if (arg.equals("GET_A") || arg.equals("GET_B") || arg.equals("GET_C") || arg.substring(0,3).toUpperCase().equals("GET")) {
                    vendingMachineController.selectItem(arg);
                } else {
                    System.out.println(Constants.INVALID_ACTION);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Item> getItems(String arg) {

        List<Item> items = new ArrayList<Item>();
        List<String> stringArgs = Arrays.asList(arg.split(","));
        List<String> stringItemElements;
        Item item;
        try {
            for (String strArg : stringArgs) {
                stringItemElements = Arrays.asList(strArg.split(";"));
                item = new ItemImpl(stringItemElements.get(0), Double.parseDouble(stringItemElements.get(1)), Integer.parseInt(stringItemElements.get(2)));
                items.add(item);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    private static MoneyBag getMoneyBag(String arg) {
        MoneyBag moneyBag = new MoneyBagImpl();
        List<String> stringArgs = Arrays.asList(arg.split(","));
        List<String> stringItemElements;
        int nickelCount = 0;
        int dimeCount = 0;
        int quarterCount = 0;

        try {
            for (String strArg : stringArgs) {

                stringItemElements = Arrays.asList(strArg.split(";"));
                if (stringItemElements.get(0).equals(Constants.QUARTER)) {
                    quarterCount += Integer.parseInt(stringItemElements.get(1).toString());
                } else if (stringItemElements.get(0).equals(Constants.DIME)) {
                    dimeCount += Integer.parseInt(stringItemElements.get(1).toString());
                } else if (stringItemElements.get(0).equals(Constants.NICKEL)) {
                    nickelCount += Integer.parseInt(stringItemElements.get(1).toString());
                } else {
                    throw new Exception("Invalid entry.");
                }
            }
            if (quarterCount > 0) {
                moneyBag.add(
                        getMoney(Constants.QUARTER, Constants.QUARTER_AMOUNT, quarterCount)
                );
            }
            if (dimeCount > 0) {
                moneyBag.add(
                        getMoney(Constants.DIME, Constants.DIME_AMOUNT, dimeCount)
                );
            }
            if (nickelCount > 0) {
                moneyBag.add(
                        getMoney(Constants.NICKEL, Constants.NICKEL_AMOUNT, nickelCount)
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return moneyBag;
    }

    private static Money getMoney(String denomination, Double cost, int count) {
        Money money = new MoneyImpl(denomination,cost);
        money.setCount(count);
        return money;
    }
}
