package com.thanhduybk.thrift_learn.protocol;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

public class BinaryProtocol {
    public static void main(String[] args) throws TException {
        TMemoryBuffer transport = new TMemoryBuffer(4096);
        TProtocol protocol = new TBinaryProtocol(transport);

        protocol.writeString("Hello Thrift");

        System.out.println("Wrote " + transport.length() + " bytes to the TMemoryBuffer");

        String msg = protocol.readString();

        System.out.println("Recovered string: " + msg);
    }
}
