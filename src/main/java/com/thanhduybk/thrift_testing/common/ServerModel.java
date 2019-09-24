package com.thanhduybk.thrift_testing.common;

public enum ServerModel {
    // Blocking
    SIMPLE, THREAD_POOL,

    // Non-blocking
    NON_BLOCKING, HSHA, THREADED_SELECTOR
}
