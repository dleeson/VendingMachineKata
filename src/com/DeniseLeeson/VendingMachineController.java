package com.DeniseLeeson;

import java.util.List;

public class VendingMachineController {


    private VendingMachine vendingMachine;

    public VendingMachineController() {
        vendingMachine = new VendingMachineImpl( new MessageCollector());
    }

    public VendingMachine getVendingMachine() {return vendingMachine;}

    public void acceptMoney(Double money) {
        MessagePrinter.printMessage(vendingMachine.acceptMoney(money));
    }

    public void returnMoney() {
        MessagePrinter.printMessage(vendingMachine.returnMoney());
        MessagePrinter.printReturn();
    }

    public void selectItem(String selection) {
        MessagePrinter.printMessage(vendingMachine.selectItem(selection));
        MessagePrinter.printReturn();
    }

    public void setInventory(List<Item> items) {vendingMachine.setInventory(items);}
}
