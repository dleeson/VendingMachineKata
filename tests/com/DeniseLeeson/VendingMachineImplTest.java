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
        Double money = Constants.NICKEL_AMOUNT;
        assertEquals(Constants.NICKEL + Constants.SPACER,vendingMachine.acceptMoney(money));
    }

    @Test
    public void acceptsDimes() {
        Double money = Constants.DIME_AMOUNT;
        assertEquals(Constants.DIME + Constants.SPACER, vendingMachine.acceptMoney(money));
    }


    @Test
    public void acceptsQuarters() {
        Double money = Constants.QUARTER_AMOUNT;
        assertEquals(Constants.QUARTER + Constants.SPACER,vendingMachine.acceptMoney(money));
    }

    @Test
    public void acceptsDollars() {
        Double money = Constants.DOLLAR_AMOUNT;
        assertEquals(Constants.DOLLAR + Constants.SPACER,vendingMachine.acceptMoney(money));
    }

    @Test
    public void storesInsertedMoney() {
        vendingMachine.acceptMoney(Constants.DOLLAR_AMOUNT);
        vendingMachine.acceptMoney(Constants.NICKEL_AMOUNT);
        vendingMachine.acceptMoney(Constants.QUARTER_AMOUNT);
        vendingMachine.returnMoney();
        String actualResult = MessageFormatHelper.formatResult(messageCollector) ;
        assertEquals(Constants.COIN_RETURN + Constants.NEWLINE + MessageFormatHelper.formatResult(allowableMoney), actualResult);

    }

    @Test
    public void canRetrieveAnItem() {
        Double dollar = Constants.DOLLAR_AMOUNT;
        Item item = vendingMachine.getItem(Constants.GET_B);
        assertEquals("B", item.getSelector());
        assertEquals(dollar, item.getCost());
    }
    @Test
    public void itemCountIsDiminishedByOneWhenItemIsPurchase() {
        int expectedItemCount =
                vendingMachine.getItem(Constants.GET_C).getCount() - 1;
        vendingMachine.acceptMoney(Constants.DOLLAR_AMOUNT);
        vendingMachine.acceptMoney(Constants.QUARTER_AMOUNT);
        vendingMachine.acceptMoney(Constants.QUARTER_AMOUNT);
        vendingMachine.selectItem(Constants.GET_C);
        assertEquals(expectedItemCount, vendingMachine.getItem(Constants.GET_C).getCount());
    }

    @Test
    public void setMoneyBagAddsMoneyToCurrentMoneyBag() {
        MoneyBag<Money> currentMoneyBag = new MoneyBagImpl<Money>();
        Money currentMoney = new MoneyImpl(Constants.QUARTER, Constants.QUARTER_AMOUNT);
        currentMoney.setCount(10);
        currentMoneyBag.add(currentMoney);
        vendingMachine.setMoneyBag(currentMoneyBag);
        int expectedCount = 15;

        MoneyBag<Money> moneyBag = new MoneyBagImpl<Money>();
        Money money = new MoneyImpl(Constants.QUARTER,Constants.QUARTER_AMOUNT);
        money.setCount(5);
        moneyBag.add(money);
        vendingMachine.setMoneyBag(moneyBag);

        MoneyBag<Money> actualMoneyBag = vendingMachine.getMoneyBag();
        assertEquals(expectedCount, actualMoneyBag.getMoney(Constants.QUARTER).getCount());
    }


}