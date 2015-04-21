package com.DeniseLeeson;

public class MessagePrinter {
    public static void printMessage(String message) {
       System.out.print(message);
    }

    public static void printMessage(MessageCollector messages) {
        for (int indx = 0; indx < messages.size(); indx++) {
            printMessage(messages.get(indx).toString());
            printReturn();
        }
    }

    public static void printReturn() {
        printMessage(Constants.NEWLINE);
    }
}


