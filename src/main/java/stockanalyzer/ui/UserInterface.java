package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import stockanalyzer.downloader.Downloader;
import stockanalyzer.downloader.ParallelDownloader;
import stockanalyzer.downloader.SequentialDownloader;
import yahooApi.exceptions.*;

import stockanalyzer.ctrl.Controller;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1() {
		try{
			Downloader sd = new SequentialDownloader();
			ctrl.process("INTC,AAPL,AMZN",sd);
		}
		catch(YahooException e){
			UserInterface.print(e.getMessage());
		}
	}

	public void getDataFromCtrl2(){
		try{
			Downloader pd = new ParallelDownloader();
			ctrl.process("INTC,AAPL,AMZN",pd);
		}
		catch(YahooException e){
			UserInterface.print(e.getMessage());
		}
	}

	public void getDataFromCtrl3(){
		try{
			ctrl.process("INTC");
		}
		catch(YahooException e){
			UserInterface.print(e.getMessage());
		}
	}
	public void getDataFromCtrl4(){
		try{
			ctrl.process("AAPL");
		}
		catch(YahooException e){
			UserInterface.print(e.getMessage());
		}
	}

	public void getDataFromCtrl5(){
		try{
			ctrl.process("AMZN");
		}
		catch(YahooException e){
			UserInterface.print(e.getMessage());
		}
	}

	public void getDataForCustomInput() {

	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "INTC,AAPL,AMZN - SequentialDownloader", this::getDataFromCtrl1);
		menu.insert("b", "INTC,AAPL,AMZN - ParallelDownloader", this::getDataFromCtrl2);
		menu.insert("c", "INTC", this::getDataFromCtrl3);
		menu.insert("d", "AAPL",this::getDataFromCtrl4);
		menu.insert("z", "AMZN",this::getDataFromCtrl5);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		ctrl.closeConnection();
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}

	public static void print(String message){
		System.out.println(message);
	}
}
