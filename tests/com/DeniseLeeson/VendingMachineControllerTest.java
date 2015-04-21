package com.DeniseLeeson;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class VendingMachineControllerTest {
    private VendingMachineController vendingMachineController;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        vendingMachineController = new VendingMachineController();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        vendingMachineController = null;
        System.setOut(null);
    }

    @Test
    public void controlsVendingMachine() {
        assertNotNull(vendingMachineController.getVendingMachine());
    }



    @Test
    public void doesntAcceptInvalidMoney() {
        Double money = 10.00;
        vendingMachineController.acceptMoney(money);
        assertEquals(String.format(Constants.INVALID_MONEY,money), outContent.toString().trim());
    }

    @Test
    public void displaysResultWhenMoneyIsAccepted() {
        Double money = 1.00;
        vendingMachineController.acceptMoney(money);
        assertEquals(Constants.DOLLAR + Constants.SPACER.trim(), outContent.toString().trim());
    }

    @Test
    public void displaysResultWhenReturningMoney() throws IOException {
        vendingMachineController.acceptMoney(1.00);
        vendingMachineController.acceptMoney(.05);
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.returnMoney();
        StringBuilder sb = new StringBuilder();
        sb
                .append(Constants.DOLLAR)
                .append(Constants.SPACER)
                .append(Constants.NICKEL)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.COIN_RETURN)
                .append(Constants.NEWLINE)
                .append(Constants.DOLLAR)
                .append(Constants.SPACER)
                .append(Constants.NICKEL)
                .append(Constants.SPACER)
                .append(Constants.QUARTER);


        assertEquals(sb.toString(), outContent.toString().trim());
    }

    @Test
    public void displaysResultOfSelectAnItem() {
        vendingMachineController.getVendingMachine();
        vendingMachineController.acceptMoney(1.00);
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.selectItem(Constants.GET_C);
        StringBuilder sb = new StringBuilder();
        sb
                .append(Constants.DOLLAR)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.GET_C)
                .append(Constants.NEWLINE)
                .append("C");

        assertEquals(sb.toString(), outContent.toString().trim());
    }

    @Test
    public void displaysOnlySelectorWhenBuyingItemWithExactChange() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(Constants.DOLLAR)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.GET_C)
                .append(Constants.NEWLINE)
                .append("C");

        vendingMachineController.acceptMoney(1.00);
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.selectItem(Constants.GET_C);
        assertEquals(sb.toString(), outContent.toString().trim());
    }

    @Test
    public void itemIsSoldOut() {
        VendingMachine vendingMachine = vendingMachineController.getVendingMachine();
        Item item = vendingMachine.getItem(Constants.GET_A);
        item.setCount(0);

        vendingMachineController.acceptMoney(.25);
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.acceptMoney(.10);
        vendingMachineController.acceptMoney(.05);
        vendingMachineController.selectItem(Constants.GET_A);

        StringBuilder sb = new StringBuilder();
        sb.append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.DIME)
                .append(Constants.SPACER)
                .append(Constants.NICKEL)
                .append(Constants.SPACER)
                .append(Constants.GET_A)
                .append(Constants.NEWLINE)
                .append(Constants.ITEM_SOLD_OUT);


        assertEquals(sb.toString(), outContent.toString().trim());

    }

    @Test
    public void noMoneyWasInsertedButItemSelected() {
        vendingMachineController.selectItem(Constants.GET_A);
        VendingMachine vendingMachine = vendingMachineController.getVendingMachine();
        Item item = vendingMachine.getItem(Constants.GET_A);
        assertEquals(item.getCost().toString(), outContent.toString().trim());
    }

    @Test
    public void notEnoughMoneyForTheItem() {
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.selectItem(Constants.GET_A);
        Double expectedAmountStillNeeded = .40;
        StringBuilder sb = new StringBuilder();
            sb.append(Constants.QUARTER)
            .append(Constants.SPACER)
            .append(Constants.GET_A)
            .append(Constants.NEWLINE)
            .append(String.format(Constants.NOT_ENOUGH_MONEY, expectedAmountStillNeeded));
        assertEquals(sb.toString(), outContent.toString().trim());
    }

    @Test
    public void correctChangeIsDisplayed() {
        vendingMachineController.acceptMoney(1.00);
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.acceptMoney(.10);
        vendingMachineController.acceptMoney(.10);
        vendingMachineController.acceptMoney(.10);
        vendingMachineController.acceptMoney(.05);
        vendingMachineController.acceptMoney(.25);
        vendingMachineController.selectItem(Constants.GET_A);
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.DOLLAR)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.DIME)
                .append(Constants.SPACER)
                .append(Constants.DIME)
                .append(Constants.SPACER)
                .append(Constants.DIME)
                .append(Constants.SPACER)
                .append(Constants.NICKEL)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.GET_A)
                .append(Constants.NEWLINE)
                .append("A")
                .append(Constants.SPACER)
                .append(Constants.DOLLAR)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.DIME)
                .append(Constants.SPACER)
                .append(Constants.DIME);
        assertEquals(sb.toString(), outContent.toString().trim());
    }

    @Test
    public void canSetInventory() {
        List<Item> items = new ArrayList<Item>();
        Item expectedItem = new ItemImpl("D",2.25,5);
        items.add(expectedItem);
        vendingMachineController.setInventory(items);

        Item actualItem = vendingMachineController.getVendingMachine().getItem("D");
        assertEquals(expectedItem.getSelector(), actualItem.getSelector());
        assertEquals(expectedItem.getCost(), actualItem.getCost());
        assertEquals(expectedItem.getCount(),actualItem.getCount());
    }

    @Test
    public void canSetChange() {


    }

    @Test
    public void returnMoneyWithNoItemsSelectedOrMoneyInserted() {
        vendingMachineController.returnMoney();
        assertEquals(Constants.NO_COINS_TO_RETURN,outContent.toString().trim());
    }

    @Test
    public void selectItemThatDoesntExist() {
        vendingMachineController.selectItem("GET_F");
        StringBuilder sb = new StringBuilder();
        sb.append("GET_F")
                .append(Constants.NEWLINE)
                .append(Constants.ITEM_DOES_NOT_EXIST);
        assertEquals(sb.toString(), outContent.toString().trim());
    }

    @Test
    public void moneyActuallyGetsSaved() {
        vendingMachineController.acceptMoney(Constants.DOLLAR_AMOUNT);
        vendingMachineController.returnMoney();
        assertEquals("DOLLAR, COIN-RETURN\nDOLLAR",outContent.toString().trim());
    }
}
