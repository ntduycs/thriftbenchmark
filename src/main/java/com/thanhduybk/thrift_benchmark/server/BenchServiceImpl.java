package com.thanhduybk.thrift_benchmark.server;

import com.thanhduybk.thrift_benchmark.generated.BenchRequest;
import com.thanhduybk.thrift_benchmark.generated.BenchResponse;
import com.thanhduybk.thrift_benchmark.generated.BenchService;
import org.apache.thrift.TException;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class BenchServiceImpl implements BenchService.Iface {
    private static final String LEGAL_LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom secureRandom = new SecureRandom();

    private Map<Integer, String> cacheMap = new HashMap<>();

    @Override
    public BenchResponse doBenchmark(BenchRequest req) throws TException {
        BenchResponse response = new BenchResponse();
        int responseSize = req.getResponseSize();
        long pauseTimeInMs = req.getPauseTimeInMs();
        String data = cacheMap.get(responseSize);

        if (data == null) {
            data = generateString(responseSize);
            cacheMap.put(responseSize, data);
        }

        if (pauseTimeInMs > 0) {
            try {
                Thread.sleep(pauseTimeInMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        response.setResponseString(data);
        return response;
    }

    private String generateString(int len) {
        StringBuilder stringBuilder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            stringBuilder.append(LEGAL_LETTERS.charAt(secureRandom.nextInt(LEGAL_LETTERS.length())));
        }
        return stringBuilder.toString();
    }
}
