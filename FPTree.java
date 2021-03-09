package fp_growth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class FPTree {
	private double minsup;
	private double minconf;
	private int num;
	
	public FPTree(double minsup, double minconf, int countLine) {
		super();
		num = (int) minsup * countLine;
	}
	
	//build header table
	public LinkedList<Node> headerTable(ArrayList<ArrayList<String>> table){
		LinkedList<Node> headerTable = new LinkedList<Node>();
		HashMap<String, Node> map = new HashMap<String, Node>();
		
		for(ArrayList<String> items:table) {
			for(String item: items) {
				if(map.containsKey(item))
					map.get(item).addCount();
				else {
					Node node = new Node(item,1);
					map.put(item, node);
				}
			}
		}
		
		// add items that have enough frequency to header table
		for(String name: map.keySet()) {
			Node node = map.get(name);
			if(node.getCount() >= num)
				headerTable.add(node);
		}
		
		// sort header table
		Collections.sort(headerTable, new nodeSort());
		return headerTable;
	}
	
	// build Tree
	public Node buildTree(ArrayList<ArrayList<String>> table, LinkedList<Node> headerTable) {
		Node root = new Node("root", 0, null);				// set a root
		// incomplete
		
		return root;
	}
	
	// build conditional pattern base
	public ArrayList<String> buildCondiPatternBase(Node node){
		ArrayList<String> table = new ArrayList<String>();
		Node parent = node;
		while(parent.hasParent()) {
			if(parent.getParent().getName().equals("root"))
				break;
			parent = parent.getParent();
			table.add(parent.getName());
		}
		return table;
	}
	
	// implement FP growth
	public ArrayList<String> growth(ArrayList<ArrayList<String>> table, String item){
		ArrayList<String> pattern = new ArrayList<String>();
		LinkedList<Node> headerTable = headerTable(table);
		Node root = buildTree(table, headerTable);
		
		if(headerTable.size() == 0 || root == null)
			return pattern;
		
		if(item == null) {
			for(Node node:headerTable)
				pattern.add(node.getName() + ":" + node.getCount());
		}else {
			for(int i = headerTable.size() - 1; i >= 0; i--) {
				Node node = headerTable.get(i);
				pattern.add(node.getName() + ":" + node.getCount());
			}
		}
		
		for(int i = headerTable.size() - 1; i >= 0; i--) {
			ArrayList<ArrayList<String>> newTransactions = new ArrayList<ArrayList<String>>();
		    Node node = headerTable.get(i);					
			String newItem = item == null ? node.getName() : node.getName() + "," + item;
																	
			while(node.hasNextSameNode()){
				node = node.getNextSameNode();
				for(int j = 0; j < node.getCount(); j++)			
					newTransactions.add(buildCondiPatternBase(node));
			}
			
			pattern.addAll(growth(newTransactions, newItem));
		}
		return pattern;
	}
}
