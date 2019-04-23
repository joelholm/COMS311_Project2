import java.util.*;

public class ComputerNode {
	
	private int timestamp;
	private int id;
	ComputerNode pred;
	private List<ComputerNode> outNeighbors;
	
	public ComputerNode(int id, int timestamp) {
		outNeighbors = new ArrayList<ComputerNode>();
		this.timestamp = timestamp;
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public int getTimestamp() {
		return timestamp;
	}
	
	public List<ComputerNode> getOutNeighbors(){
		return outNeighbors;
	}
	
	public void addNeighborNode(ComputerNode n) {
		if( n == null ) {
			throw new NullPointerException();
		}
		outNeighbors.add(n);
	}
	
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
