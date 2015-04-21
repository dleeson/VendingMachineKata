package com.DeniseLeeson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class VendingMachineImplTest {
    private VendingMachine vendingMachine;
    private MessageCollector messageCollector;
    private final List<String> allowableMoney = Arrays.asList(Constants.DOLLAR, Constants.NICKEL, Constants.QUARTER);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        messageCollector = new MessageCollector();
        vendingMachine = new VendingMachineImpl(messageCollector);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void hasListOfItemsForSale() {
        assertNotNull(new VendingMachineImpl(new MessageCollector()).getItems());
    }

    @Test
    public void acceptsNickels() {
        Double money = .05;
        assertEquals(Constants.NICKEL + Constants.SPACER,vendingMachine.acceptMoney(money));
    }

    @Test
    public void acceptsDimes() {
        Double money = .10;
        assertEquals(Constants.DIME + Constants.SPACER, vendingMachine.acceptMoney(money));
    }


    @Test
    public void acceptsQuarters() {
        Double money = .25;
        assertEquals(Constants.QUARTER + Constants.SPACER,vendingMachine.acceptMoney(money));
    }

    @Test
    public void acceptsDollars() {
        Double money = 1.00;
        assertEquals(Constants.DOLLAR + Constants.SPACER,vendingMachine.acceptMoney(money));
    }

    @Test
    public void storesInsertedMoney() {
        vendingMachine.acceptMoney(1.00);
        vendingMachine.acceptMoney(.05);
        vendingMachine.acceptMoney(.25);
        vendingMachine.returnMoney();
        String actualResult = MessageFormatHelper.formatResult(messageCollector) ;
        assertEquals(MessageFormatHelper.formatResult(allowableMoney) + Constants.SPACER + Constants.COIN_RETURN, actualResult);

    }

    @Test
    public void canRetrieveAnItem() {
        Double dollar = 1.00;
        Item item = vendingMachine.getItem(Constants.GET_B);
        assertEquals("B", item.getSelector());
        assertEquals(dollar, item.getCost());
    }
    @Test
    public void itemCountIsDiminishedByOneWhenItemIsPurchase() {
        int expectedItemCount =
                vendingMachine.getItem(Constants.GET_C).getCount() - 1;
        vendingMachine.acceptMoney(1.00);
        vendingMachine.acceptMoney(.25);
        vendingMachine.acceptMoney(.25);
        vendingMachine.selectItem(Constants.GET_C);
        assertEquals(expectedItemCount, vendingMachine.getItem(Constants.GET_C).getCount());
    }


}