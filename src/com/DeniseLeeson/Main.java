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
        double money;
        try {
            for (String arg : args) {
                money = 0.00;
                if (arg.equals("NICKEL")) {
                    money = .05;
                } else if (arg.equals("DIME")) {
                    money = .10;
                } else if (arg.equals("QUARTER")) {
                    money = .25;
                } else if (arg.equals("DOLLAR")) {
                    money = 1.00;
                }
                if (money > 0) {
                    vendingMachineController.acceptMoney(money);
                } else if (arg.equals("COIN_RETURN")) {
                    vendingMachineController.returnMoney();
                } else if (arg.equals("GET_A") || arg.equals("GET_B") || arg.equals("GET_C") || arg.substring(0,3).toUpperCase().equals("GET")) {
                    vendingMachineController.selectItem(arg);
                } else if (arg.toLowerCase().contains(",")) {
                    vendingMachineController.setInventory(getItems(arg));
                } else {
                    System.out.println("Invalid action.");
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Item> getItems(String arg) {
        List<Item> items = new ArrayList<Item>();
        List<String> stringArgs = Arrays.asList(arg.split("\\s*,\\s*"));
        List<String> stringItemElements;
        Item item;
        for (String strArg : stringArgs) {
            stringItemElements = Arrays.asList(strArg.split("\\s*|\\s*"));
            item = new ItemImpl(stringItemElements.get(0),Double.parseDouble(stringItemElements.get(1)), Integer.parseInt(stringItemElements.get(2)));
            items.add(item);
        }
        return items;
    }
}
