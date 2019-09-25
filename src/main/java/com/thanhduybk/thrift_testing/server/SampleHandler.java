package com.thanhduybk.thrift_testing.server;

import com.thanhduybk.thrift_testing.generated.SampleRequest;
import com.thanhduybk.thrift_testing.generated.SampleResponse;
import com.thanhduybk.thrift_testing.generated.SampleService;
import org.apache.thrift.TException;

public class SampleHandler implements SampleService.Iface {
    private static final String BASE_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // 62 bytes
    private static final int respSizeInByte = 10000; // bytes

    private SampleResponse genResponseWith(int respSize) {
        return new SampleResponse(BASE_STRING.repeat(respSize / BASE_STRING.length() + 1));
    }

    @Override
    public SampleResponse doBenchmark(SampleRequest req) throws TException {
        if (req.pauseTimeInMs > 0) {
            try {
                Thread.sleep(req.pauseTimeInMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Response size = 62 * resSize = 310 bytes
        return genResponseWith(respSizeInByte);
    }
}
