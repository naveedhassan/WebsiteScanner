package app.webScanner.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class WordTrie {
	
	
	TrieNode head;
	
	public WordTrie(TrieNode head) {
		
		this.head=head;
	}
    
 // A max-heap node
    @SuppressWarnings("rawtypes")
	class Node implements Comparable
    {
    	String key;
    	int count;

    	// constructor
    	Node(String key, int count) {
    		this.key = key;
    		this.count = count;
    	}

    	@Override
    	public int compareTo(Object o) {
    		Node node = (Node)o;
    		return count - node.count;
    	}
    	
    	@Override
    	public String toString() {
    	
    		return "Key==>" + key + " Count==" + count;
    	}
    }
    
    
    //Code to insert words into the trie
    public void insert(String str)
	{
		// start from root node
    	TrieNode curr = head;
    	
    	if(str.equals("EHR")) {
    		System.out.println();
    	}

		for (char c: str.toCharArray())
		{
			// create a new node if path doesn't exists
			curr.characterMap.putIfAbsent(c, new TrieNode());

			// go to next node
			curr = curr.characterMap.get(c);
		}

		// store key and its count in leaf nodes
		curr.key = str;
		curr.count += 1;
	}
    
    
    // Traversing through the trie and inserting the value into the Priority queue
 	// each distinct key along with its count in max-heap
 	private void preorderTraversal(TrieNode curr, PriorityQueue<Node> pq)
 	{
 		// break condition
 		if (curr == null) {
 			return;
 		}

 		for (Entry<Character, TrieNode> entry: curr.characterMap.entrySet())
 		{
 			// if leaf node is reached (leaf node have non-zero count),
 			// push key with its frequency in max-heap
 			if (entry.getValue().count != 0) {
 				pq.add(new Node(entry.getValue().key, entry.getValue().count));
 			}

 			// recur for current node children
 			preorderTraversal(entry.getValue(), pq);
 		}
 	}
 	
 	public List<WordCount> fetchWordsOccurence(int k){
 		
 		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
 		preorderTraversal(head, priorityQueue);
 		List<WordCount> nodeList=new ArrayList<WordCount>();
 		
 		while (k-- > 0 && !priorityQueue.isEmpty())
		{
			// extract the maximum node from the max-heap
 			Node node=priorityQueue.poll();
 			
 			WordCount wordCount=new WordCount();
 			wordCount.setWord(node.key);
 			wordCount.setCount(node.count);
 			nodeList.add(wordCount);
		}
 		
 		return nodeList;
 		
 		
 	}


	@Override
	public String toString() {
		return "WordTrie [head=" + head + "]";
	}
 	
 	
 	
}
