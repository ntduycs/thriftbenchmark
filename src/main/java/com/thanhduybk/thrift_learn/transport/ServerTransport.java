package com.thanhduybk.thrift_learn.transport;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.nio.charset.StandardCharsets;

public class ServerTransport {
    public static void main(String[] args) throws TTransportException {
        final String msg = "Hello Thrift\n";
        final String stopCmd = "STOP";
        final int bufferSize = 4096;
        byte[] buffer = new byte[bufferSize];

        // Create an acceptor with its timeout set to 10s to prevent indefinite blocking
        TServerTransport acceptor = new TServerSocket(8585, 10000);
        acceptor.listen();

        System.out.println("Server is listening or port 8585.");

        while (true) {
            // New connection must wait until the accept() method is called
            // A thread that calls accept() method will be blocked until a connect arrives
            TTransport transport = acceptor.accept();

            System.out.println("Server is handling request");

            /* enhanced version with TFramedTransport
            TTransport improvedTransport = new TFramedTransport(transport);
            improvedTransport.read(buffer, 0, bufferSize);
             */

            transport.read(buffer, 0, bufferSize);

            // Read the last four character to check the termination request
            if (stopCmd.regionMatches(0, new String(buffer, 0, buffer.length, StandardCharsets.UTF_8), 0, 4)) {
                break;
            }

            /* enhanced version with TFramedTransport
            improvedTransport.write(msg.getBytes());
            improvedTransport.flush();
            improvedTransport.close()
             */

            transport.write(msg.getBytes());
            transport.flush();
            transport.close();
        }

        System.out.println("Server is exiting");
        acceptor.close();
    }
}
