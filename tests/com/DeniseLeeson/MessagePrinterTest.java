package com.DeniseLeeson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MessagePrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void printMessagePutsMessageInConsole() {
        new MessagePrinter().printMessage("Hello");
        assertEquals("Hello", outContent.toString().trim());
    }

    @Test
    public void printMessagePutsMessagesInConsole() {
        MessageCollector messageCollector = new MessageCollector();
        messageCollector.add("Hello, Alan!");
        messageCollector.add("How are you?");
        messageCollector.add("my name is Hal.");
        MessagePrinter.printMessage(messageCollector);
        assertEquals("Hello, Alan!\nHow are you?\nmy name is Hal.\n", outContent.toString());
    }
}
