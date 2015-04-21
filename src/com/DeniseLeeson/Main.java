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
