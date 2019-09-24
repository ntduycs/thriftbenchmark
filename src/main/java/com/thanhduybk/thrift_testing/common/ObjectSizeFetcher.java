package com.thanhduybk.thrift_testing.common;

import java.lang.instrument.Instrumentation;

public class ObjectSizeFetcher {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getSizeInBytesOf(Object o) {
        return instrumentation.getObjectSize(o);
    }
}
