#SIMPLE, NONBLOCKING, HSHA, THREADED_SELECTOR, THREAD_POOL
MODE=("SIMPLE" "NONBLOCKING" "HSHA" "THREADED_SELECTOR" "THREAD_POOL")

#BINARY, COMPACT, JSON
PROTOCOLS=("BINARY" "COMPACT" "JSON")

for mode in "${MODE[@]}" ; do
  for protocol in "${PROTOCOLS[@]}"; do
    mvn exec:java -Dexec.mainClass="com.thanhduybk.thrift_benchmark.server.BenchServer" -Dexec.args="-protocol ${protocol} -mode ${mode}"
    sleep 5
    mvn exec:java -Dexec.mainClass="com.thanhduybk.thrift_benchmark.client.BenchClient" -Dexec.args="-protocol ${protocol} -mode ${mode} -pauseTimeInMs 2"
  done
done
