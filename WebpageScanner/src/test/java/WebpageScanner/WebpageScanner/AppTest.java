package WebpageScanner.WebpageScanner;



import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import app.webScanner.App;
import app.webScanner.dto.WordCount;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	 App app = new App();
	 
	    @Test
	    public void testSingleWordpair() throws IOException{
	    	List<List<WordCount>> wordCount=app.webScanner("https://www.314e.com/");
	        
	        assertEquals(Double.valueOf(wordCount.get(0).get(0).getCount()),Double.valueOf(948));
	        assertEquals(Double.valueOf(wordCount.get(0).get(1).getCount()),Double.valueOf(840));
	        assertEquals(Double.valueOf(wordCount.get(0).get(2).getCount()),Double.valueOf(730));
	        assertEquals(Double.valueOf(wordCount.get(0).get(3).getCount()),Double.valueOf(609));
	        assertEquals(Double.valueOf(wordCount.get(0).get(4).getCount()),Double.valueOf(607));
	        assertEquals(Double.valueOf(wordCount.get(0).get(5).getCount()),Double.valueOf(533));
	        assertEquals(Double.valueOf(wordCount.get(0).get(6).getCount()),Double.valueOf(517));
	        assertEquals(Double.valueOf(wordCount.get(0).get(7).getCount()),Double.valueOf(517));
	        assertEquals(Double.valueOf(wordCount.get(0).get(8).getCount()),Double.valueOf(484));
	        assertEquals(Double.valueOf(wordCount.get(0).get(9).getCount()),Double.valueOf(456));
	    }
	    
}
