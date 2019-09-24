namespace * com.thanhduybk.thrift_testing

union SampleField {
    1: i32 intField,
    2: string strField,
}

struct SampleRequest {
    1: SampleField sampleField,
    2: i64 pauseTimeInMs,
}

struct SampleResponse {
    1: string resStr,
}

service SampleService {
    SampleResponse doBenchmark(1: SampleRequest req)
}