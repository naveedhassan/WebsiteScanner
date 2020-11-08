package app.webScanner.dto;

import java.util.HashMap;
import java.util.Map;


public class TrieNode {

	
	int count = 0;
	String key = null;
	// each node stores a map to its child nodes
	Map<Character, TrieNode> characterMap = new HashMap<>();
	@Override
	public String toString() {
		return "TrieNode [count=" + count + ", key=" + key + ", characterMap=" + characterMap + "]";
	}
	
	
}
