package stockanalyzer.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelDownloader extends Downloader {
    @Override
    public int process(List<String> tickers) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<>();
        for (String s : tickers) {
            Future<String> future = executor.submit(
                    () -> saveJson2File(s)
            );
            futures.add(future);
        }
        executor.shutdown();
        return futures.size();
    }
}
