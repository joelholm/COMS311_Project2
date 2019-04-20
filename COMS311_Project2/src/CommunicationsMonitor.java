import java.util.*;

public class CommunicationsMonitor {
	
	boolean createGraphCalled;
	ArrayList<CommunicationTriple> triples;
	
	public CommunicationsMonitor() {
		//TODO:
		createGraphCalled = false;
		triples = new ArrayList<CommunicationTriple>();
	}
	
	public void addCommunication(int c1, int c2, int timestamp) {
		//TODO: error checking...
		
		//check that createGraph wasn't called
		if( createGraphCalled ) {
			return;
		}
		
		//add to an array list
		triples.add(new CommunicationTriple(c1,c2,timestamp));
	}
	
	public void createGraph() {
		//TODO:
		createGraphCalled = true;
		
		sortTriples();
		
		//for each triple ( now ordered )
			//do necessary changes to the data structure ( those 6 or so steps in the document )
		
		
		
	}
	
	public List<ComputerNode> queryInfection(int c1, int c2, int x, int y){
		//TODO:
		return null;
	}
	
	//use figure 1 for reference ( those box things )
	public HashMap<Integer, List<ComputerNode>> getComputerMapping(){
		//TODO:
		
		return null;
	}
	
	//just like ^^^ this function, but only get one List based on int c
	public List<ComputerNode> getComputerMapping(int c){
		//TODO:
		return null;
	}
	
	public void sortTriples() {
		//TODO: 
		//sort the array list 
		//probably need to write a quicksort/mergesort or something
		//just need it to be done in O(nlogn)
	}
}
