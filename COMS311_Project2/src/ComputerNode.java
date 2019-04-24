/**
 * ComputerNode 
 * This class represents the nodes created after createGraph is called in CommunicationsMonitor.
 * It contains instance varibales used to runs the BFS such as pred 
 * 
 * @author Joel Holm, Colin Ishman
 */

import java.util.*;

public class ComputerNode {
	
	private int timestamp;
	private int id;
	public ComputerNode pred;
	public int color; //0 = white, 1 = gray, 2 = black
	private List<ComputerNode> outNeighbors;
	
	/**
	 * Constructor for the class
	 * @param id a integer that represents the ID of the node
	 * @param timestamp Time that the computer was interacted with
	 */
	public ComputerNode(int id, int timestamp) {
		outNeighbors = new ArrayList<ComputerNode>();
		this.timestamp = timestamp;
		this.id = id;
		pred = null;
		color = 0;
	}
	
	/**
	 * Getter method for ID
	 * @return Id of the node
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Getter method for Pred
	 * @return Pred of the node
	 */
	public ComputerNode getPred() {
		return pred;
	}
	
	/**
	 * Getter method for the timestap
	 * @return the timestamp of the node
	 */
	public int getTimestamp() {
		return timestamp;
	}
	
	/**
	 * List the nodes that are the neighbors to the current node
	 * @return A list of all the nodes that are neighbors to the current node
	 */
	public List<ComputerNode> getOutNeighbors(){
		return outNeighbors;
	}
	
	/**
	 * Adds a neighbor to the current node
	 * @param n Node to be added to the list of neighbors for the current node
	 */
	public void addNeighborNode(ComputerNode n) {
		if( n == null ) {
			throw new NullPointerException();
		}
		outNeighbors.add(n);
	}
	
	/**
	 * Override for toString
	 */
	@Override
	public String toString() {
		Iterator<ComputerNode> it = outNeighbors.iterator();
		String neighbors = "";
		while( it.hasNext() ) {
			ComputerNode next = it.next();
			neighbors += "ID: " + next.getID() + " Timestamp: " + next.getTimestamp() + "\t";
		}
		return new String("id: " + id + " timestamp: " + timestamp + " \n\tNeighbors: " + neighbors);
	}
}
