package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import yahooApi.exceptions.*;

import stockanalyzer.ctrl.Controller;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1() {
		try{
			ctrl.process("INTC,AAPL,AMZN");
		}
		catch(YahooException e){
			System.out.println(e.getMessage());
		}
	}

	public void getDataFromCtrl2(){
		try{
			ctrl.process("INTC");
		}
		catch(YahooException e){
			System.out.println(e.getMessage());
		}
	}

	public void getDataFromCtrl3(){
		try{
			ctrl.process("AAPL");
		}
		catch(YahooException e){
			System.out.println(e.getMessage());
		}
	}
	public void getDataFromCtrl4(){
		try{
			ctrl.process("AMZN,AAPL");
		}
		catch(YahooException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void getDataForCustomInput() {
		
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "INTC,APPL,AMZN", this::getDataFromCtrl1);
		menu.insert("b", "INTC", this::getDataFromCtrl2);
		menu.insert("c", "AAPL", this::getDataFromCtrl3);
		menu.insert("d", "AMZN",this::getDataForCustomInput);
		menu.insert("z", "AMZN, APPL",this::getDataFromCtrl4);
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
