package com.thanhduybk.thrift_learn.protocol;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TSimpleFileTransport;
import org.apache.thrift.transport.TTransport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Benchmark {
    private static class Trade {
        char[] symbol = new char[16];
        double price;
        int size;
    }

    public static void main(String[] args) throws TException {
        String usage = "Usage: " + " (m[emory]|f[file]) (b[inary]|c[ompact]|j[son]) [b[uffering]]";
        String typeBench = "";
        if (args.length < 2 || args.length > 3) {
            System.out.println(usage);
            return;
        }

        TTransport transport;
        TProtocol protocol;

        if (args[0].equals("m")) {
            int memSize = 64 * 1024 * 1024;
            transport = new TMemoryBuffer(memSize);
            System.out.print("TMemoryBuffer(" + memSize + ")/");
            typeBench += "TMemoryBuffer/";
        } else if (args[0].equals("f")) {
            String pathName = "/tmp/thrift_data";
            transport = new TSimpleFileTransport(pathName, false, true);
            System.out.print("TSimpleFileTransport(" + pathName + ")/");
            typeBench += "TSimpleFileTransport/";
        } else {
            System.out.println(usage);
            return;
        }

        if (args.length == 3) {
            System.out.print("TFramedTransport");
            typeBench += "TFramedTransport/";
            transport = new TFramedTransport(transport);
        }

        switch (args[1]) {
            case "b":
                protocol = new TBinaryProtocol(transport);
                System.out.print("TBinaryProtocol");
                typeBench += "TBinaryProtocol";
                break;
            case "c":
                protocol = new TCompactProtocol(transport);
                System.out.print("TCompactProtocol");
                typeBench += "TCompactProtocol";
                break;
            case "j":
                protocol = new TJSONProtocol(transport);
                System.out.print("TJSONProtocol");
                typeBench += "TJSONProtocol";
                break;
            default:
                System.out.println(usage);
                return;
        }

        Trade trade = new Trade();
        trade.symbol[0] = 'F';
        trade.symbol[1] = '\0';
        trade.price = 13.10;
        trade.size = 2500;

        int sum = 0;

        for (int i = 0; i < 10; i++) {
            LocalTime start = LocalTime.now();
            for (int j = 0; j < 100000; j++) {
                protocol.writeStructBegin(new TStruct());

                protocol.writeFieldBegin(new TField("symbol", TType.STRING, (short) 1));
                protocol.writeString(String.valueOf(trade.symbol));
                protocol.writeFieldEnd();

                protocol.writeFieldBegin(new TField("price", TType.DOUBLE, (short) 2));
                protocol.writeDouble(trade.price);
                protocol.writeFieldEnd();

                protocol.writeFieldBegin(new TField("size", TType.I32, (short) 3));
                protocol.writeI32(trade.size);
                protocol.writeFieldEnd();

                protocol.writeFieldStop();
                protocol.writeStructEnd();

                protocol.getTransport().flush();
            }
            LocalTime end = LocalTime.now();

            sum += ChronoUnit.MILLIS.between(start, end);
        }
        System.out.println("Duration: " + (double) sum / 10 + "ms");

        try {
            FileWriter writer = new FileWriter(new File("Benchmark.csv"), true);
            writer.append(String.join(",",
                    typeBench,
                    (double) sum / 10 + "ms",
                    "\n"));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
