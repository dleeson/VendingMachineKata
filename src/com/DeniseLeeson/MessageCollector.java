package com.DeniseLeeson;


import java.util.ArrayList;
import java.util.List;

public class MessageCollector extends ArrayList {


    public MessageCollector() {
        super();
    }

    public boolean hasMessages() {
        return size() > 0;
    }

    public List<String> getMessages() {
        List<String> messages = new ArrayList<String>();
        for(int indx = 0; indx < size(); indx++) {
            messages.add(String.valueOf(get(indx)));
        }
        return messages;
    }


}
