package stockanalyzer.ctrl;

import stockanalyzer.downloader.Downloader;
import stockanalyzer.ui.UserInterface;
import yahooApi.YahooFinance;
import yahooApi.exceptions.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// GitHub Repo: https://github.com/SycSin/StockAnalyzer
public class Controller {

	private YahooFinance yahooFinance;
	private List<String> tickers;

	public void process(String ticker, Downloader... downloader) throws YahooException{
		System.out.println("Start process");

		if(ticker.length() > 0){
			getData(ticker);
			serveData();
			if(downloader.length == 1){
				downloadTickersToJSON(downloader[0]);
			}
		}
		else{
			throw new YahooException("ticker cannot be null or empty!");
		}
	}

	private void downloadTickersToJSON(Downloader downloader) throws YahooException {
		if(tickers != null){
			long start = System.nanoTime();
			downloader.process(tickers);
			long end = System.nanoTime();
			long totalTime = (end - start)/100000;
			System.out.printf("Time spent writing to JSON-File(s): %s ms", totalTime);
		}
		else{
			throw new YahooException("The tickers collection has not been initialized yet! Make sure to call the getData method first!");
		}
	}


	public void getData(String searchString){
		tickers = Stream.of(searchString.split(",")).map(String::trim).collect(Collectors.toList());
		yahooFinance = new YahooFinance();
	}

	public void serveData(){
		try {
			System.out.println("The highest quote is: "+yahooFinance.getHighestQuote(tickers));
			System.out.println("The average quote is: "+yahooFinance.getAverageQuote(tickers));
			System.out.println("The total sum of records is: "+yahooFinance.getTotalRecords(tickers));
		} catch (IOException | YahooException e){
			UserInterface.print(e.getMessage());
		}
	}


	public void closeConnection() {
	}
}
