package stockanalyzer.downloader;

import yahooApi.YahooFinance;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Downloader {

    public static final String DIRECTORY_DOWNLOAD = "download/";
    private static final String JSON_EXTENSION = ".json";

    public abstract int process(List<String> urls);

    public String saveJson2File(String ticker) {
        String fileName = "";
        BufferedWriter writer= null;
        try {
            // create download directory if it does not exist
            Path directory = Path.of(DIRECTORY_DOWNLOAD);
            if(!Files.exists(directory)){
                Files.createDirectories(directory);
            }
            YahooFinance yahooFinance = new YahooFinance();
            String json = yahooFinance.requestData(new ArrayList<>(Collections.singleton(ticker)));

            fileName = DIRECTORY_DOWNLOAD +ticker + JSON_EXTENSION;

            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(writer).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
