package app.webScanner;

import java.io.IOException;
import java.util.List;

import app.webScanner.Service.WebScannerService;
import app.webScanner.dto.WordCount;

/**
 * Hello world!
 *
 */
public class App {
	
	/*
	 * This is the entrypoint to the App.
	 * 
	 * It Initializes a WebScanner service and displays 10 most frequent word and
	 * word pair.
	 * 
	 * It return a two list, first list conatins 10 most frequent word, second list
	 * contains 10 most frequent word pairs.
	 */
	
	public static  List<List<WordCount>> webScanner(String url) throws IOException{
		
		WebScannerService service= new WebScannerService();
		
		List<List<WordCount>> wordCount=service.intializeAndConstructWordCount(url);
		System.out.println(wordCount);// Print the count to console
		
		return wordCount;
	}


	/* if you want to call from command line argument can be passed. */
	public static void main(String[] args) throws IOException {
		
	//	webScanner("https://www.314e.com/");
		
		webScanner(args[0]);
		
	}
}
