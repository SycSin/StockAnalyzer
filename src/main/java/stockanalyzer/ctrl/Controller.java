package stockanalyzer.ctrl;

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
	
	public void process(String ticker) throws YahooException{
		System.out.println("Start process");

		if(ticker != null && ticker.length() > 0){
			getData(ticker);
			serveData();
		}
		else{
			throw new YahooException("ticker cannot be null or empty!");
		}

	}
	

	public Object getData(String searchString){
		tickers = Stream.of(searchString.split(",")).map(String::trim).collect(Collectors.toList());
		yahooFinance = new YahooFinance();
		return null;
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
