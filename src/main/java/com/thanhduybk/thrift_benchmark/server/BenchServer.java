package com.thanhduybk.thrift_benchmark.server;

import com.thanhduybk.thrift_benchmark.generated.BenchService;
import org.apache.commons.cli.*;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.*;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.util.Arrays;

public class BenchServer {
    public enum ServerProtocol {
        BINARY, COMPACT, JSON
    }

    public enum ServerMode {
        SIMPLE, NONBLOCKING, HSHA, THREADED_SELECTOR, THREAD_POOL
    }

    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("protocol", true,
                "Thrift protocol could be " + String.join(", ", Arrays.toString(ServerProtocol.values())));
        options.addOption("mode", true,
                "Server mode could be " + String.join(", ", Arrays.toString(ServerMode.values())));
        options.addOption("port", true,
                "Server port is 5000 by default.");
        options.addOption("maxNumThreads", true,
                "Maximum worker threads for ThreadPool server mode is 20 default.");

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, args);

        String protocolAsString = commandLine.getOptionValue("protocol");
        ServerProtocol protocol;
        try {
            protocol = ServerProtocol.valueOf(protocolAsString);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unknown protocol argument: " + protocolAsString, e);
        }

        String serverModeAsString = commandLine.getOptionValue("mode");
        ServerMode serverMode;
        try {
            serverMode = ServerMode.valueOf(serverModeAsString);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unknown server mode argument: " + serverModeAsString, e);
        }

        String portAsString = commandLine.getOptionValue("port");
        int port = 5000;
        try {
            port = Integer.parseInt(portAsString);
        } catch (NumberFormatException e) {
            System.out.println("Expect: an Integer for port, Actual: " + portAsString);
        }

        String maxWorkerThreads = commandLine.getOptionValue("maxNumThreads");
        int threadpoolMaxWorkerThreads = 20;
        try {
            threadpoolMaxWorkerThreads = Integer.parseInt(maxWorkerThreads);
        } catch (NumberFormatException e) {
            System.out.println("Expect: an Integer for workerThread, Actual: " + threadpoolMaxWorkerThreads);
        }

        BenchServer benchServer = new BenchServer();
        try {
            System.out.println("Server starting with protocol " + protocol +
                    ", mode " + serverMode +
                    ", port " + port +
                    ", maxWorkerThreads (effect only in thread_pool mode) " + threadpoolMaxWorkerThreads);
            benchServer.startServer(protocol, serverMode, port, threadpoolMaxWorkerThreads);
        } catch (TTransportException e) {
            throw new RuntimeException("Failed to start server due to unknown reason,", e);
        }
    }

    private void startServer(ServerProtocol protocol, ServerMode serverMode, int port, int threadpoolMaxWorkerThreads) throws TTransportException {
        BenchServiceImpl service = new BenchServiceImpl();
        BenchService.Processor<BenchServiceImpl> processor = new BenchService.Processor<>(service);

        TProtocolFactory protocolFactory = getTProtocolFactory(protocol);
        TServer server;

        switch (serverMode) {
            case SIMPLE:
                TServerSocket simpleServerSocket = new TServerSocket(port);
                TServer.Args simpleArgs = new TServer.Args(simpleServerSocket);
                simpleArgs.protocolFactory(protocolFactory);
                simpleArgs.processor(processor);
                server = new TSimpleServer(simpleArgs);
                break;
            case NONBLOCKING:
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
                hshaArgs.minWorkerThreads(5);
                hshaArgs.maxWorkerThreads(20);
                server = new THsHaServer(hshaArgs);
                break;
            case THREADED_SELECTOR:
                TNonblockingServerTransport threadedSelectorServerTransport = new TNonblockingServerSocket(port);
                TThreadedSelectorServer.Args threadedSelectorArgs = new TThreadedSelectorServer.Args(threadedSelectorServerTransport);
                threadedSelectorArgs.protocolFactory(protocolFactory);
                threadedSelectorArgs.processor(processor);
                threadedSelectorArgs.selectorThreads(10);
                threadedSelectorArgs.workerThreads(10);
                server = new TThreadedSelectorServer(threadedSelectorArgs);
                break;
            case THREAD_POOL:
                TServerSocket threadPoolServerSocket = new TServerSocket(port);
                TThreadPoolServer.Args threadPoolArgs = new TThreadPoolServer.Args(threadPoolServerSocket);
                threadPoolArgs.protocolFactory(protocolFactory);
                threadPoolArgs.processor(processor);
                threadPoolArgs.minWorkerThreads(5);
                threadPoolArgs.maxWorkerThreads(threadpoolMaxWorkerThreads);
                server = new TThreadPoolServer(threadPoolArgs);
                break;
            default:
                throw new RuntimeException("Thrift Server mode not supported !" + serverMode.toString());
        }

        server.serve();
    }

    private TProtocolFactory getTProtocolFactory(ServerProtocol protocol) {
        switch (protocol) {
            case COMPACT:
                return new TCompactProtocol.Factory();
            case BINARY:
                return new TBinaryProtocol.Factory();
            case JSON:
                return new TJSONProtocol.Factory();
            default:
                throw new RuntimeException("Thrift Server protocol not supported! " + protocol.toString());
        }
    }
}
