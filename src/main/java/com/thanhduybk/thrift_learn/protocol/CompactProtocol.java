package com.thanhduybk.thrift_learn.protocol;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TSimpleFileTransport;
import org.apache.thrift.transport.TTransport;

public class CompactProtocol {
    private static class Trade {
        public String symbol;
        public double price;
        public int size;
    }

    public static void main(String[] args) throws TException {
        TTransport transport = new TSimpleFileTransport("data", false, true);
        TProtocol protocol = new TCompactProtocol(transport);

        Trade trade = new Trade();
        trade.symbol = "F";
        trade.price = 13.10;
        trade.size = 2500;

        protocol.writeStructBegin(new TStruct());
        protocol.writeFieldBegin(new TField("symbol", TType.STRING, (short) 1));
        protocol.writeString(trade.symbol);
        protocol.writeFieldEnd();

        protocol.writeFieldBegin(new TField("price", TType.DOUBLE, (short) 2));
        protocol.writeDouble(trade.price);
        protocol.writeFieldEnd();

        protocol.writeFieldBegin(new TField("size", TType.I32, (short) 3));
        protocol.writeI32(trade.size);
        protocol.writeFieldEnd();

        protocol.writeFieldStop();
        protocol.writeStructEnd();

        System.out.println("Wrote trade to file");
    }
}
