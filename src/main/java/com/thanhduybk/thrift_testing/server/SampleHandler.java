package com.thanhduybk.thrift_testing.server;

import com.thanhduybk.thrift_testing.common.ObjectSizeFetcher;
import com.thanhduybk.thrift_testing.generated.SampleRequest;
import com.thanhduybk.thrift_testing.generated.SampleResponse;
import com.thanhduybk.thrift_testing.generated.SampleService;
import org.apache.thrift.TException;

public class SampleHandler implements SampleService.Iface {
    private static final String BASE_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // 62 bytes
    private static final int resSize = 5;


    @Override
    public SampleResponse doBenchmark(SampleRequest req) throws TException {
        if (req.pauseTimeInMs > 0) {
            try {
                Thread.sleep(req.pauseTimeInMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Service is called.");
        // Response size = 62 * 5 = 310 bytes
        return new SampleResponse(BASE_STRING.repeat(resSize));
    }
}
