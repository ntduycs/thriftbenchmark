package com.thanhduybk.thrift_testing.server;

import com.thanhduybk.thrift_testing.common.ServerModel;
import com.thanhduybk.thrift_testing.generated.SampleService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TCompactProtocol.Factory;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.*;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class SampleServer {
    // protocol, mode, port, workerThreads
    private static ServerModel serverModel = ServerModel.THREADED_SELECTOR;
    private static int port = 5000;
    private static int maxWorkerThreads = 20;
    private static int minWorkerThreads = 5;
    private static int ioThreads = 5;

    private static SampleServer INSTANCE;

    private SampleServer() {
    }

    public static SampleServer getInstance() {
        if (INSTANCE == null) return new SampleServer();
        return INSTANCE;
    }

    public static void main(String[] args) {
        SampleServer sampleServer = getInstance();
        try {
            sampleServer.startServer(serverModel, port, maxWorkerThreads, minWorkerThreads, ioThreads);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    private void startServer(ServerModel serverModel, int port, int maxWorkerThreads, int minWorkerThreads, int ioThreads) throws TTransportException {
        SampleService.Processor<SampleHandler> processor = new SampleService.Processor<>(new SampleHandler());

        TProtocolFactory protocolFactory = new TCompactProtocol.Factory();
        TServer server;

        switch (serverModel) {
            case SIMPLE:
                TServerSocket tServerSocket = new TServerSocket(port);
                TServer.Args simpleArgs = new TServer.Args(tServerSocket);
                simpleArgs.protocolFactory(protocolFactory);
                simpleArgs.processor(processor);
                server = new TSimpleServer(simpleArgs);
                break;

            case THREAD_POOL:
                TServerSocket threadPoolServerSocket = new TServerSocket(port);
                TThreadPoolServer.Args threadPoolArgs = new TThreadPoolServer.Args(threadPoolServerSocket);
                threadPoolArgs.protocolFactory(protocolFactory);
                threadPoolArgs.processor(processor);
                threadPoolArgs.minWorkerThreads(minWorkerThreads);
                threadPoolArgs.maxWorkerThreads(maxWorkerThreads);
                server = new TThreadPoolServer(threadPoolArgs);
                break;

            case NON_BLOCKING:
                TNonblockingServerTransport nonblockingServerTransport = new TNonblockingServerSocket(port);
                TNonblockingServer.Args nonBlockingArgs = new TNonblockingServer.Args(nonblockingServerTransport);
                nonBlockingArgs.protocolFactory(protocolFactory);
                nonBlockingArgs.processor(processor);
                server = new TNonblockingServer(nonBlockingArgs);
                break;

            case HSHA:
                TNonblockingServerTransport hshaServerTransport = new TNonblockingServerSocket(port);
                THsHaServer.Args hshaArgs = new THsHaServer.Args(hshaServerTransport);
                hshaArgs.protocolFactory(protocolFactory);
                hshaArgs.processor(processor);
                hshaArgs.minWorkerThreads(minWorkerThreads);
                hshaArgs.maxWorkerThreads(maxWorkerThreads);
                server = new THsHaServer(hshaArgs);
                break;

            case THREADED_SELECTOR:
                TNonblockingServerTransport threadedSelectorServerTransport = new TNonblockingServerSocket(port);
                TThreadedSelectorServer.Args threadedSelectorArgs = new TThreadedSelectorServer.Args(threadedSelectorServerTransport);
                threadedSelectorArgs.protocolFactory(protocolFactory);
                threadedSelectorArgs.processor(processor);
                threadedSelectorArgs.selectorThreads(ioThreads); // I/O threads
                threadedSelectorArgs.workerThreads(maxWorkerThreads); // processing threads
                server = new TThreadedSelectorServer(threadedSelectorArgs);
                break;

            default:
                throw new RuntimeException("Unknown server mode");
        }

        server.serve();
    }
}
