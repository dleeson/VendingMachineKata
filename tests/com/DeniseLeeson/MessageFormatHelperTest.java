package com.DeniseLeeson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MessageFormatHelperTest {
    public static final Map<String, Double> map = new HashMap<String, Double>(){{
        put(Constants.NICKEL, Constants.NICKEL_AMOUNT);
        put(Constants.DIME, Constants.DIME_AMOUNT);
        put(Constants.QUARTER, Constants.QUARTER_AMOUNT);
        put(Constants.DOLLAR, Constants.DOLLAR_AMOUNT);
    }};

    @Test
    public void resultsAreFormattedForList() {
        List<String> messages = new ArrayList<String>();
        messages.add(Constants.DOLLAR);
        messages.add(Constants.DIME);
        messages.add(Constants.GET_A);
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.DOLLAR)
                .append(Constants.SPACER)
                .append(Constants.DIME)
                .append(Constants.SPACER)
                .append(Constants.GET_A);

        assertEquals(sb.toString(), MessageFormatHelper.formatResult(messages));

    }

    @Test
    public void resultsAreFormattedForDouble() {
        assertEquals(Constants.QUARTER + Constants.SPACER,MessageFormatHelper.formatResult(Constants.QUARTER_AMOUNT, map));
    }

    @Test
    public void resultsIncludeItemSelected() {
        String ItemSelected = Constants.GET_B;
        assertEquals(ItemSelected + Constants.NEWLINE, MessageFormatHelper.formatItemSelected(ItemSelected));

    }

    @Test
    public void resultsAreFormattedForReturnChange() {
        Change change = new Change(.65, 0, 0, 0, 0);
        change.calculateChange();

        StringBuilder sb = new StringBuilder();
            sb.append("A")
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.QUARTER)
                .append(Constants.SPACER)
                .append(Constants.DIME)
                .append(Constants.SPACER)
                .append(Constants.NICKEL);


        MessageCollector messageCollector = new MessageCollector();
        MessageFormatHelper.formatReturnChange(change, messageCollector, "A");
        assertEquals(sb.toString(), MessageFormatHelper.formatResult(messageCollector.getMessages()));

    }
}
