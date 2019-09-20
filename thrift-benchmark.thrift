namespace java com.thanhduybk.thrift_benchmark.generated

struct BenchRequest {
    1: i32 responseSize,
    2: i64 pauseTimeInMs,
}

struct BenchResponse {
    1: string responseString,
}

service BenchService {
    BenchResponse doBenchmark(1: BenchRequest req)
}