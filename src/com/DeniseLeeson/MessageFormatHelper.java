package com.DeniseLeeson;


import java.util.List;
import java.util.Map;

public class MessageFormatHelper {

    public static String formatResult(List<String> messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            if (message.trim().length() > 0) {
                sb.append(message);
                if (!message.substring(message.length() -1, message.length()).equals(Constants.NEWLINE)) {
                    sb.append(Constants.SPACER);
                }
            }
        }
        if (sb.toString().trim().endsWith(",")) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    public static String formatResult(Double money, Map<String, Double> map) {
        return MapHelper.getKeyFromValue(money,map) + Constants.SPACER;
    }

    public static String formatItemSelected(String itemSelected) {
        return itemSelected + Constants.NEWLINE;
    }

    public static void formatReturnChange(Change change, MessageCollector messageCollector, String selector) {
        StringBuilder sb = new StringBuilder();
        sb.append(selector)
                .append(Constants.SPACER);

        for (int iDollar = 0; iDollar < change.getDollarCount(); iDollar++) {
            sb.append(Constants.DOLLAR)
                    .append(Constants.SPACER);
        }

        for (int iQuarter = 0; iQuarter < change.getQuarterCount(); iQuarter++) {
            sb.append(Constants.QUARTER)
                    .append(Constants.SPACER);
        }

        for (int iDime = 0; iDime < change.getDimeCount(); iDime++) {
            sb.append(Constants.DIME)
                    .append(Constants.SPACER);
        }

        for (int iNickel = 0; iNickel < change.getNickelCount(); iNickel++) {
            sb.append(Constants.NICKEL)
                    .append(Constants.SPACER);
        }
        if (sb.toString().trim().endsWith(",")) {
            sb.delete(sb.length() - 2, sb.length());
        }

        messageCollector.add(sb.toString());
    }

    public static String formatMoneyBag(MoneyBag<Money> moneyBag, MessageCollector messageCollector) {
        for (Money money : moneyBag) {
            messageCollector.add(money.toString() + Constants.NEWLINE);
        }
        return MessageFormatHelper.formatResult(messageCollector.getMessages());
    }
}
