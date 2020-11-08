package app.webScanner.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import app.webScanner.dto.TrieNode;
import app.webScanner.dto.WordCount;
import app.webScanner.dto.WordTrie;

public class WebScannerService {

	private static Integer THRESHOLD_RECURSION = 4; // this determines the recursion level

	private static Integer TOP_COUNT_ELEMENTS = 10; //
	/*
	 * This method returns List of two tries, one contain top 
	 * 
	 * Initializing hyperLinkData which is a set which is used to contain already
	 * scanned URLS
	 * 
	 * Initializing recursion count to 0, Maximum right now is 4.
	 * 
	 * Adding the parent URL to hyperlinkData
	 * 
	 * Initializing document get instance from jsoup.
	 * 
	 * 
	 */
	public List<List<WordCount>> intializeAndConstructWordCount(String url) throws IOException {

		Set<String> hyperLinkData = new HashSet<String>();// used set as the retrieval time is O(1)

		Integer recusrsionCount = 0;// Initializing recursion count to zero in the start

		hyperLinkData.add(url);// Adding cussrint URL to hyperlingData , so that we dont scan the URL again.
		
		List<List<WordCount>> wordTrieList=new ArrayList<>();

		// Initializing head Trie Node to store single words
		TrieNode head = new TrieNode();
		WordTrie wordTrie = new WordTrie(head);
		

		// Trie to store word pairs
		TrieNode headPair = new TrieNode();
		WordTrie wordTriePair = new WordTrie(headPair);
		
		

		Document document = Jsoup.connect(url).get();
		processHyperlinks(document, hyperLinkData, recusrsionCount, url, wordTrie, wordTriePair);
		
		
		wordTrieList.add(wordTrie.fetchWordsOccurence(TOP_COUNT_ELEMENTS));
		wordTrieList.add(wordTriePair.fetchWordsOccurence(TOP_COUNT_ELEMENTS));
		return wordTrieList;


	}

	/*
	 * This methods fetches all the hyper links from the Webpage.
	 * 
	 * Then it recursively calls the method to again fetch all the words from the
	 * child page.
	 * 
	 * After fetching the words it inserts the word to two tries , one for single
	 * words and one for Pair words.
	 * 
	 * It also maintains a recursion count which should not go beyond 4. It can be
	 * externally configured
	 * 
	 */

	private String[] processHyperlinks(Document document, Set<String> hyperLinkData, Integer recursionCount,
			String masterUrl, WordTrie wordTrie, WordTrie wordTriePair) {

		if (recursionCount > THRESHOLD_RECURSION) {
			return null;
		}

		// fetching all elements of hyperlinks
		Elements elementList = document.select("a");

		String webPageText = document.body().text();
		String[] splitString = webPageText.split("\\W+");

		if (recursionCount == 0) {
			insertKeyToTrie(wordTrie, splitString);
			insertKeyToTriePair(wordTriePair, splitString);
		}

		for (Element element : elementList) {
			String url = element.attr("href");
			if (isValidURL(url, hyperLinkData, masterUrl)) {
				try {
					hyperLinkData.add(url);
					Document linkDocument = Jsoup.connect(url).get();
					recursionCount++;
					String[] childSplitString = processHyperlinks(linkDocument, hyperLinkData, recursionCount,
							masterUrl, wordTrie, wordTriePair);
					recursionCount--;

					if (childSplitString != null) {
						insertKeyToTrie(wordTrie, childSplitString);
						insertKeyToTriePair(wordTriePair, childSplitString);
					}

				} catch (IOException e) {

					// Exception while creatig jspurp
					System.err.print(e);
				}

			}
		}

		return splitString;
	}

	/*
	 * Method to check if it is a valid URL or not It also checks if the URL has
	 * 
	 * been previously scanned. Also check if the URL is an extenernal link or not
	 * 
	 * If it is external link it return false which is accordinly used by the caller
	 * method
	 */

	private boolean isValidURL(String urlString, Set<String> hyperLinkData, String masterUrlString) {
		try {
			URL url = new URL(urlString);
			url.toURI();
			URL masterUrl = new URL(masterUrlString);
			masterUrl.toURI();
			if (hyperLinkData.contains(urlString) || !url.getHost().equals(masterUrl.getHost())) {
				return false;
			}

			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	private void insertKeyToTrie(WordTrie wordTrie, String[] key) {
		for (int i = 0; i < key.length; i++) {
			wordTrie.insert(key[i].toLowerCase());
		}

	}

	private void insertKeyToTriePair(WordTrie wordTriePair, String[] key) {
		for (int i = 0; i < key.length; i++) {
			if (i != key.length - 1) {
				String pairWords = key[i] + "-" + key[i + 1];
				wordTriePair.insert(pairWords.toLowerCase());
			}
		}
	}

}
