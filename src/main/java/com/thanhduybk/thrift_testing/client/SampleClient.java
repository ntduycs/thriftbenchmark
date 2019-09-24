package com.thanhduybk.thrift_testing.client;

import com.thanhduybk.thrift_testing.common.ServerModel;
import com.thanhduybk.thrift_testing.generated.SampleField;
import com.thanhduybk.thrift_testing.generated.SampleRequest;
import com.thanhduybk.thrift_testing.generated.SampleService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SampleClient {
    private volatile boolean runFlag = false;
    private AtomicInteger runCounter = new AtomicInteger(0);

    // This benchmark makes use TCompactProtocol
    private static ServerModel serverModel = ServerModel.THREADED_SELECTOR;
    private static int numConnections = 10;
    private static int step = 1; // actual size will be (64 * step + 8) bytes

    private String host = "localhost";
    private int port = 5000;

    public static void main(String[] args) {
        (new SampleClient()).doBenchmark(serverModel, numConnections, step);
    }

    private void doBenchmark(ServerModel serverModel, int numConnections, int step) {
        SampleService.Client[] clients = new SampleService.Client[numConnections];
        for (int i = 0; i < numConnections; i++) {
            clients[i] = connect(serverModel, host, port);
        }
        callServiceForEachConnection(clients, step);
    }

    // Using TCompactProtocol by default
    private SampleService.Client connect(ServerModel serverModel, String host, int port) {
        TTransport transport;

        switch (serverModel) {
            case SIMPLE:
            case THREAD_POOL:
                transport = new TSocket(host, port);
                break;
            case NON_BLOCKING:
            case HSHA:
            case THREADED_SELECTOR:
                transport = new TFramedTransport(new TSocket(host, port));
                break;
            default:
                throw new RuntimeException("Unknown server mode: " + serverModel.name());
        }

        try {
            transport.open();
        } catch (TTransportException e) {
            transport.close();
            throw new RuntimeException("Failed to open client transport", e);
        }

        return new SampleService.Client(new TCompactProtocol(transport));
    }

    private SampleRequest generateRequestBaseOn(int reqSize) {
        final String BASE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345678900"; // 64 bytes
        final String strField = BASE_STRING.repeat(reqSize);
        final long executionTime = 2000; // lasts 2 seconds, 8 bytes

        return new SampleRequest(new SampleField(SampleField._Fields.STR_FIELD, strField), executionTime);
    }

    private void callServiceForEachConnection(SampleService.Client[] clients, int step) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // step = 1, 10, 100, 1000, 10000
        // reqSize = 64, 640, 6400, 64000, 640000 bytes
        for (int i = 0; i < 5; i++) {
            SampleRequest request = generateRequestBaseOn((int) (step * Math.pow(10, i)));
            callService(clients, request, executor, 5000);
        }

        executor.shutdown();
    }

    private void callService(SampleService.Client[] clients, SampleRequest request, ExecutorService executor, long delay) {
        final CountDownLatch starter = new CountDownLatch(1);
        final CountDownLatch finisher = new CountDownLatch(clients.length);

        runFlag = true;
        runCounter.set(0);

        // Serve
        for (final SampleService.Client client : clients) {
            Runnable task = () -> {
                try {
                    starter.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Failed to execute benchmark.", e);
                }

                while (true) {
                    boolean flag = runFlag;
                    if (!flag) break;
                    try {
                        client.doBenchmark(request);
                        int byteSent = runCounter.incrementAndGet();
                        System.out.println("\n " + byteSent + "bytes \n");
                    } catch (TException e) {
                        throw new RuntimeException("Error when call service", e);
                    }
                }
                // One connection has been served
                finisher.countDown();
            };
            executor.execute(task);
        }

        long startCountDown = System.currentTimeMillis();
        starter.countDown();

        while (true) {
            if ((System.currentTimeMillis() - startCountDown) > delay) break;
        }

        runFlag = false;

        try {
            finisher.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to execute benchmark.", e);
        }

        long totalBytesSent = runCounter.get();
        System.out.println(totalBytesSent);
        long elapsedTime = System.currentTimeMillis() - startCountDown;

    }


}
