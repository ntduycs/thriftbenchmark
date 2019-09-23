package com.thanhduybk.thrift_learn.transport;

import org.apache.thrift.transport.TSimpleFileTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.nio.charset.StandardCharsets;

public class NetworkTransport {
    private static void readTransport(TTransport transport) {
        final int bufferSize = 4096;
        byte[] buffer = new byte[bufferSize];

        while (true) {
            try {
                int bytesRead = transport.read(buffer, 0, bufferSize);
                if (bytesRead <= 0 || bufferSize < bytesRead) {
                    break;
                }
                System.out.println(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws TTransportException {
        // Display web page
        TTransport transport = new TSocket("localhost", 80);
        final String msg = " GET / \n";
        transport.open();
        transport.write(msg.getBytes());
        transport.flush();
        readTransport(transport);
        transport.close();

        // Display file
        transport = new TSimpleFileTransport("trans.java");
        transport.open();
        readTransport(transport);
        transport.close();
    }
}
