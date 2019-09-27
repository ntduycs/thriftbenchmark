package com.thanhduybk.thrift_testing.client;

import com.thanhduybk.thrift_testing.common.ServerModel;
import com.thanhduybk.thrift_testing.generated.SampleField;
import com.thanhduybk.thrift_testing.generated.SampleRequest;
import com.thanhduybk.thrift_testing.generated.SampleResponse;
import com.thanhduybk.thrift_testing.generated.SampleService;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SampleClient {
    private static final ServerModel model = ServerModel.THREADED_SELECTOR;

    public static void main(String[] args) throws TException {
        int numClients = 200;
        int concurrentRequestsPerClient = 1; // 10 20 50 100 200

        int requestSizeInByte = 10000;
        int execTimeInMs = 0;

        SampleRequest request = generateRequestWith(requestSizeInByte, execTimeInMs);
        SampleService.Client[] clients = createClients(numClients, "localhost", 5000, request);

        final DescriptiveStatistics statistics = new SynchronizedDescriptiveStatistics();

        ExecutorService executor = Executors.newFixedThreadPool(numClients);

        final AtomicInteger trans = new AtomicInteger(0);
        final AtomicInteger transOK = new AtomicInteger(0);
        final CountDownLatch latch = new CountDownLatch(concurrentRequestsPerClient * numClients);

        long start = System.currentTimeMillis();
        for (int i = 0; i < numClients; i++) {
            final int k = i; // executor.submit() will run asynchronously

            executor.submit(() -> {
                for (int j = 0; j < concurrentRequestsPerClient; j++) {
                    try {
                        long t = System.currentTimeMillis();
                        SampleResponse res = clients[k].doBenchmark(request);
                        t = System.currentTimeMillis() - t;

                        statistics.addValue(t);
                        trans.incrementAndGet();

                        if (res != null)
                            transOK.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        start = System.currentTimeMillis() - start;

        int numRequests = numClients * concurrentRequestsPerClient;
        System.out.println("Sent " + numRequests + " requests (" + requestSizeInByte + " bytes for each)");
        System.out.println("Received " + trans.get() + " responses");
        System.out.println("Received " + transOK.get() + " responses with status OK");
        System.out.println("Throughput: " + numRequests * 1000 / start + " requests per second");

        System.out.println("Average time per call: " + statistics.getMean() + "ms");
        System.out.println("Maximum time per call: " + statistics.getMax() + "ms");
        System.out.println("Minimum time per call: " + statistics.getMin() + "ms");

        writeResultToFile((double) numRequests * 1000 / start,
                statistics.getMean(), statistics.getMax(), statistics.getMin(),
                numClients, concurrentRequestsPerClient, requestSizeInByte, execTimeInMs);
    }

    private static void writeResultToFile(double throughput, double avgTime, double maxTime, double minTime, int numClients, int concurrent, int reqSize, int execTime) {
        try {
            File des = new File("bench.csv");
            FileWriter writer = new FileWriter(des, true);

            writer.append(String.join(",",
                    "\n", "\n",
                    String.valueOf(numClients),
                    String.valueOf(concurrent),
                    String.valueOf(reqSize),
                    String.valueOf(execTime),
                    String.valueOf(throughput),
                    String.valueOf(avgTime),
                    String.valueOf(maxTime),
                    String.valueOf(minTime),
                    "\n")
            );
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Using TCompactProtocol by default
    private static SampleService.Client[] createClients(int numClients, String host, int port, SampleRequest warmUpReq) throws TException {
        TTransport[] transports = new TTransport[numClients];
        SampleService.Client[] clients = new SampleService.Client[numClients];

        // initialize and warm-up
        for (int i = 0; i < numClients; i++) {
            switch (model) {
                case THREADED_SELECTOR:
                    transports[i] = new TFramedTransport(new TSocket(host, port));
                    break;
                case THREAD_POOL:
                    transports[i] = new TSocket(host, port);
                    break;
                default:
                    throw new RuntimeException("Unknown server mode");
            }

            transports[i].open();

            clients[i] = new SampleService.Client(new TCompactProtocol(transports[i]));

            // do warm-up
            clients[i].doBenchmark(warmUpReq);
        }

        return clients;
    }

    private static SampleRequest generateRequestWith(int reqSize, int execTimeInMs) {
        final String BASE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345678900"; // 64 bytes
        final int loop = reqSize / BASE_STRING.length() + 1;

        return new SampleRequest(new SampleField(
                SampleField._Fields.STR_FIELD,
                BASE_STRING.repeat(loop)
        ), execTimeInMs);
    }

}
