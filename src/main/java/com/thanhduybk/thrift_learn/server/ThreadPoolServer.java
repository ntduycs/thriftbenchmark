package com.thanhduybk.thrift_learn.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class ThreadPoolServer {
    public static void main(String[] args) throws TTransportException {
        TServerSocket serverTransport = new TServerSocket(8585);
        TProcessor processor = new Message.Processor<>(new MessageHandler());

        TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
        server.serve();
    }
}
