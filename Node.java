package fp_growth;

import java.util.ArrayList;
import java.util.Comparator;

public class Node {
	private int count = 0;
	private String name = null;
	private Node parent = null;
	private ArrayList<Node> children = new ArrayList<Node>();
	private Node nextNode = null;

	public Node() {
		super();
	}
	
	public Node(String name, int count) {
		super();
		this.count = count;
		this.name = name;
		this.parent = null;
		this.children = new ArrayList<Node>();
		//this.nextNode = null;
	}
	
	public Node(String name, int count, Node parent) {
		super();
		this.count = count;
		this.name = name;
		this.parent = parent;
		this.children = new ArrayList<Node>();
		//this.nextNode = null;
	}
	
	public int getCount() {
		return count;
	}
	
	public void addCount() {
		this.count += 1;
	}
	
	public String getName() {
		return name;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public boolean hasParent() {
		if(this.parent != null)
			return true;
		else
			return false;
	}
	
	public void addChild(Node child) {
		this.children.add(child);
	}
	
	public void addChildren(ArrayList<Node> children) {
		this.children.addAll(children);
	}
	
	public ArrayList<Node> getChildren(){
		return children;
	}
	
	public Node getNextSameNode() {
		return this.nextNode;
	}
	
	public boolean hasNextSameNode(){
		if(this.nextNode != null)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return this.getName() + ":" + this.getCount();
	}
}
	
class nodeSort implements Comparator<Node>{
	@Override
	public int compare(Node node1, Node node2) {
		int result = Integer.compare(node2.getCount(), node1.getCount());	
		if(result != 0)
			return result;
		return node1.getName().compareTo(node2.getName());
	}
}

