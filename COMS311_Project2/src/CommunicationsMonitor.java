import java.util.*;

public class CommunicationsMonitor {
	
	boolean createGraphCalled;
	ArrayList<CommunicationTriple> triples;
	HashMap<Integer,ArrayList<ComputerNode>> graph;
	
	public CommunicationsMonitor() {
		//TODO:
		createGraphCalled = false;
		triples = new ArrayList<CommunicationTriple>();
		graph = new HashMap<Integer,ArrayList<ComputerNode>>();
	}
	
	public void addCommunication(int c1, int c2, int timestamp) {
		//check that createGraph wasn't called
		if( createGraphCalled ) {
			return;
		}
		//add to an array list
		triples.add(new CommunicationTriple(c1,c2,timestamp));
	}
	
	public void createGraph() {
		createGraphCalled = true;
		
		sortTriples();
		
		//for each triple ( now ordered )
		Iterator<CommunicationTriple> ait = triples.iterator();
		CommunicationTriple curr;
		while( ait.hasNext() ) {
			curr = ait.next();
			ComputerNode ci = new ComputerNode(curr.getC1(),curr.getTimestamp());
			ComputerNode cj = new ComputerNode(curr.getC2(),curr.getTimestamp());
			
			//add ci and cj to the graph if not already added, O(1)
			ci = addNodeToGraph(ci);
			cj = addNodeToGraph(cj);
			
			//connect ci and cj
			ci.addNeighborNode(cj);
			cj.addNeighborNode(ci);
			
		}//end of while, total time complexity of this while is O(m)
		
	}
	
	//returns a new node if the node already exists, otherwise
	//it returns the same node you gave it
	//adds an entry to the hash table if needed
	//runs in constant time, O(1)
	public ComputerNode addNodeToGraph(ComputerNode cn) {
		
		ArrayList<ComputerNode> listc = graph.get(cn.getID());
		//if the list doesn't exist create it in O(1)
		if( listc == null ) {
			ArrayList<ComputerNode> newList = new ArrayList<ComputerNode>();
			listc = newList;
			graph.put(cn.getID(), newList );
		}
		
		//look for the timestamp in listc in O(1) because each list is sorted and our input data is sorted
		if( listc.size() > 0 ) {
			//get the last element, it's timestamp will be >= to cn's timestamp
			ComputerNode lastNode = listc.get(listc.size() - 1);
			//if that last element's timestamp matches cn's timestamp, use that element
			if( lastNode.getTimestamp() == cn.getTimestamp() ) {
				cn = lastNode;
			} else {
				//add our new element to the list in O(1)
				listc.add(cn);
				//then connect it to the previous node
				lastNode.addNeighborNode(cn);
			}
		} else {
			//add node to list, no backtrack pointing
			listc.add(cn);
		}
		return cn;
	}
	
	public List<ComputerNode> queryInfection(int c1, int c2, int x, int y){
		//TODO:
		//find first node that works with c1
		
		//run BFS from that node
		
		
		return null;
	}
	
	public ComputerNode runBFS(int c2, int y) {
		
		//run standard BFS but include an if statement, checking if each node is
		//from c2 with a timestamp <= y
		
		
		//if the BFS has run fully return null
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
