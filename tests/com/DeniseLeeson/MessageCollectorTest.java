package com.DeniseLeeson;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MessageCollectorTest {

    private MessageCollector messageCollector;

    @Before
    public void setup() {
        messageCollector = new MessageCollector();
    }

    @Test
    public void messageCollectCanAddAMessage() {
        String expectedMessage = "Succeed.";
        messageCollector.add(expectedMessage);
        assertEquals(expectedMessage, messageCollector.get(0).toString());
    }


    @Test
    public void hasMessagesReturnsTrueIfThereAreMessages() {
        messageCollector.add("Succeed.");
        assertTrue(messageCollector.hasMessages());
    }

    @Test
    public void hasMessagesReturnsFalseWhenNoMessagesExist() {
        assertFalse(messageCollector.hasMessages());
    }

    @Test
    public void canStoreManyMessages() {
        List<String> expectedMessages = Arrays.asList("Hello","My","Name","is","Barbie");
        messageCollector.addAll(expectedMessages);
        assertArrayEquals(expectedMessages.toArray(),messageCollector.getMessages().toArray());
    }
}
