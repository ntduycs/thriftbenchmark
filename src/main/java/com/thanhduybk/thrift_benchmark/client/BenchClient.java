package com.thanhduybk.thrift_benchmark.client;

import com.thanhduybk.thrift_benchmark.generated.BenchRequest;
import com.thanhduybk.thrift_benchmark.generated.BenchService;
import com.thanhduybk.thrift_benchmark.server.BenchServer;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchClient {
    private volatile boolean runFlag = false;
    private AtomicInteger runCounter = new AtomicInteger(0);

    public enum ClientProtocol {
        BINARY, COMPACT, JSON
    }

    public enum ClientMode {
        SIMPLE, NONBLOCKING, HSHA, THREADED_SELECTOR, THREAD_POOL
    }

    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("host", true, "Thrift server host, default is localhost.");
        options.addOption("port", true, "Thrift server port, default is 5000.");
        options.addOption("pauseTimeInMs", true,
                "Remote method will pause n ms to simulate execution, default is -1(no pause).");
        options.addOption("clientCount", true,
                "Thrift client number which connect to server at the same time, default is 10.");
        options.addOption("protocol", true,
                "Thrift protocol could be " + String.join(", ", Arrays.toString(BenchServer.ServerProtocol.values())));
        options.addOption("mode", true,
                "Server mode could be " + String.join(", ", Arrays.toString(BenchServer.ServerMode.values())));

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, args);

        String protocolAsString = commandLine.getOptionValue("protocol");
        ClientProtocol protocol;
        try {
            protocol = ClientProtocol.valueOf(protocolAsString);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unknown protocol argument: " + protocolAsString, e);
        }

        String clientModeAsString = commandLine.getOptionValue("mode");
        ClientMode clientMode;
        try {
            clientMode = ClientMode.valueOf(clientModeAsString);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unknown server mode argument: " + clientModeAsString, e);
        }

        String host = commandLine.getOptionValue("host");
        if (host == null) {
            host = "localhost";
        }

        String portAsString = commandLine.getOptionValue("port");
        int port = 5000;
        try {
            port = Integer.parseInt(portAsString);
        } catch (NumberFormatException e) {
            System.out.println("Expect: an Integer for port, Actual: " + portAsString);
        }

        String pauseTimeInMsAsString = commandLine.getOptionValue("pauseTimeInMs");
        long pauseTimeInMs = -1L;
        try {
            pauseTimeInMs = Long.parseLong(pauseTimeInMsAsString);
        } catch (NumberFormatException e) {
            System.out.println("Expect: an Long for pauseTimeInMs, Actual: " + pauseTimeInMsAsString);
        }

        String clientCountAsString = commandLine.getOptionValue("clientCount");
        int clientCount = 20;
        try {
            clientCount = Integer.parseInt(clientCountAsString);
        } catch (NumberFormatException e) {
            System.out.println("Expect: an Integer for clientCount, Actual: " + clientCountAsString);
        }

        if (clientMode.equals(ClientMode.SIMPLE)) {
            clientCount = 1;
        }

        BenchClient benchClient = new BenchClient();
        System.out.println("Client starting with protocol " + protocol +
                ", mode " + clientMode +
                ", host " + host +
                ", port " + port +
                ", clientCount (1 for simple mode) " + clientCount +
                ", pauseTimeInMs " + pauseTimeInMs);
        BenchService.Client[] clients = new BenchService.Client[clientCount];
        for (int i = 0; i < clientCount; i++) {
            clients[i] = benchClient.getClient(clientMode, protocol, host, port);
        }
        benchClient.doBenchMark(clients, pauseTimeInMs);
    }

    private BenchService.Client getClient(ClientMode clientMode, ClientProtocol clientProtocol, String host, int port) {
        TTransport transport;
        TSocket socket = new TSocket(host, port);

        switch (clientMode) {
            case SIMPLE:
            case THREAD_POOL:
                transport = socket;
                break;
            case NONBLOCKING:
            case HSHA:
            case THREADED_SELECTOR:
                transport = new TFramedTransport(socket);
                break;
            default:
                throw new RuntimeException("Unknown client mode: " + clientMode.toString());
        }

        try {
            transport.open();
        } catch (TTransportException e) {
            transport.close();
            throw new RuntimeException("Failed to open client transport.", e);
        }

        TProtocol protocol = null;
        switch (clientProtocol) {
            case BINARY:
                protocol = new TBinaryProtocol(transport);
                break;
            case COMPACT:
                protocol = new TCompactProtocol(transport);
                break;
            case JSON:
                protocol = new TJSONProtocol(transport);
                break;
            default:
                transport.close();
                throw new RuntimeException("Unknown client protocol: " + clientProtocol.toString());
        }

        return new BenchService.Client(protocol);
    }

    private void doBenchMark(BenchService.Client[] clients, long pauseTimeInMs) {
        // Todo: Try with different Executors
        ExecutorService executorService = Executors.newCachedThreadPool();
        execute(clients, 5000, 1, true, executorService, pauseTimeInMs);
        execute(clients, 5000, 1, false, executorService, pauseTimeInMs);
        execute(clients, 5000, 512, false, executorService, pauseTimeInMs);
        execute(clients, 5000, 1024, false, executorService, pauseTimeInMs);
        execute(clients, 5000, 4096, false, executorService, pauseTimeInMs);
        execute(clients, 5000, 10240, false, executorService, pauseTimeInMs);
        execute(clients, 5000, 40960, false, executorService, pauseTimeInMs);
        execute(clients, 5000, 102400, false, executorService, pauseTimeInMs);
        executorService.shutdown();
    }

    private void execute(BenchService.Client[] clients, long duration, int responseSize, boolean warmUp, ExecutorService executorService, long pauseTimeInMs) {
        final CountDownLatch starter = new CountDownLatch(1);
        final CountDownLatch finisher = new CountDownLatch(clients.length);

        runFlag = true;
        runCounter.set(0);

        final BenchRequest request = new BenchRequest();
        request.setPauseTimeInMs(pauseTimeInMs);
        request.setResponseSize(responseSize);

        for (final BenchService.Client client : clients) {
            Runnable task = () -> {
                try {
                    starter.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Failed to execute benchmark.", e);
                }

                while (true) {
                    boolean flag = runFlag;
                    if (!flag) {
                        break;
                    }
                    try {
                        client.doBenchmark(request);
                        runCounter.incrementAndGet();
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }
                finisher.countDown();
            };
            executorService.execute(task);
        }

        long startTime = System.currentTimeMillis();
        starter.countDown();
        while (true) {
            if ((System.currentTimeMillis() - startTime) > duration) {
                break;
            }
        }

        runFlag = false;

        try {
            finisher.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to execute benchmark.", e);
        }

        long count = runCounter.get();
        long elapsedTimes = System.currentTimeMillis() - startTime;
        long requestPerSecond = count * 1000L / elapsedTimes;
        long bytesPerSecond = requestPerSecond * (long) responseSize;
        String bps = readableFileSize(bytesPerSecond);

        if (!warmUp) { // Ignore the warm-up
            try {
                FileWriter writer = new FileWriter(new File("CachedThreadPool.txt"), true);
                try (PrintWriter printWriter = new PrintWriter(writer)) {
                    printWriter.println("========================");
                    printWriter.println();
                    printWriter.println("Payload size: " + StringUtils.leftPad(String.valueOf(responseSize), 8) + "KB" +
                            ",\nTotal exec count is: " + StringUtils.leftPad(String.valueOf(count), 8) +
                            ",\nNumber of requests per second is: " + StringUtils.leftPad(String.valueOf(requestPerSecond), 8) +
                            ",\nNumber of bytes per second is: " + StringUtils.leftPad(bps, 10));
                }

//                writer.append(String.join(",",
//                        String.valueOf(responseSize),
//                        String.valueOf(count),
//                        String.valueOf(requestPerSecond), bps, "\n"));
//                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    private String readableFileSize(long bytesPerSecond) {
        if (bytesPerSecond <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(bytesPerSecond) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(bytesPerSecond / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
