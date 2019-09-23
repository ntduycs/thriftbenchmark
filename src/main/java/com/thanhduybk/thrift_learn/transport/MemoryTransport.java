package com.thanhduybk.thrift_learn.transport;

import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TTransportException;

import java.io.*;

public class MemoryTransport {
    static private class Trade implements Serializable {
        String symbol;
        double price;
        int size;
    }

    public static void main(String[] args) throws IOException {
        TMemoryBuffer transport = new TMemoryBuffer(4096);

        Trade trade = new Trade();
        trade.symbol = "F";
        trade.price = 13.10;
        trade.size = 2500;

        // Write data to the transport in form of a bytes array
        // Making use of the following output stream to turn our object into a byte array
        // which can then be written to TMemoryBuffer
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(trade);
            transport.write(byteArrayOutputStream.toByteArray());
        } catch (IOException | TTransportException e) {
            e.printStackTrace();
        }

        // Read data from the transport
        byte[] buffer = new byte[4096];
        int bytesRead = transport.read(buffer, 0, buffer.length);
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            Trade tradeRead = (Trade) objectInputStream.readObject();
            System.out.println("Read " + bytesRead + " bytes:  Data: " +
                    tradeRead.symbol + " " + tradeRead.size + " @ " + tradeRead.price);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
