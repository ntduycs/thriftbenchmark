package com.thanhduybk.thrift_learn.server;

import org.apache.thrift.TException;

import java.util.Arrays;
import java.util.List;

public class MessageHandler implements Message.Iface {
    private int msgIndex;
    private static List<String> msgs = Arrays.asList("Duy", "Van", "Both of us");

    public MessageHandler() {
        msgIndex = 0;
    }

    @Override
    public String motd() throws TException {
        System.out.println("Call count: " + ++msgIndex);
        return msgs.get(Math.abs(msgIndex % 3));
    }
}
