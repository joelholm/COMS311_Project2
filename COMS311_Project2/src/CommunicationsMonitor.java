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
		mergeSort(triples);
	}
	
	public List<CommunicationTriple> merge(final List<CommunicationTriple> left, final List<CommunicationTriple> right) {
        final List<CommunicationTriple> merged = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty()) {
        	if (left.get(0).getTimestamp() <= (right.get(0)).getTimestamp()) {
                merged.add(left.remove(0));
            } else {
                merged.add(right.remove(0));
            }
        }
        merged.addAll(left);
        merged.addAll(right);
        return merged;
    }

    public void mergeSort(final List<CommunicationTriple> input) {
        if (input.size() != 1) {
            final List<CommunicationTriple> left = new ArrayList<CommunicationTriple>();
            final List<CommunicationTriple> right = new ArrayList<CommunicationTriple>();
            boolean logicalSwitch = true;
            while (!input.isEmpty()) {
                if (logicalSwitch) {
                    left.add(input.remove(0));
                } else {
                    right.add(input.remove(0));
                }
                logicalSwitch = !logicalSwitch;
            }
            mergeSort(left);
            mergeSort(right);
            input.addAll(merge(left, right));
        }
    }
	
}
