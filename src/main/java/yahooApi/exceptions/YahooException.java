package yahooApi.exceptions;

import stockanalyzer.ui.UserInterface;

public class YahooException extends Exception{
    public YahooException(String errorMessage) {
        UserInterface.print(errorMessage);
    }
}