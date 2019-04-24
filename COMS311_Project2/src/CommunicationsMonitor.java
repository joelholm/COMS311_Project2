import java.util.*;

public class CommunicationsMonitor {
	
	private boolean createGraphCalled;
	ArrayList<CommunicationTriple> triples;
	HashMap<Integer,List<ComputerNode>> graph;
	
	public CommunicationsMonitor() {
		//TODO:
		createGraphCalled = false;
		triples = new ArrayList<CommunicationTriple>();
		graph = new HashMap<Integer,List<ComputerNode>>();
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
		if( createGraphCalled ) {
			//no need to call it again															//Right?
			return;
		}
		createGraphCalled = true;
		
		mergeSort(triples); //O(nlogn)
		
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
		
		ArrayList<ComputerNode> listc = (ArrayList<ComputerNode>)graph.get(cn.getID());
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
		//find first node that works with c1
		ArrayList<ComputerNode> cnList = (ArrayList<ComputerNode>)graph.get(c1);
		ComputerNode firstRef = null;
		Iterator<ComputerNode> it = cnList.iterator();
		while( it.hasNext() ) {
			ComputerNode next = it.next();
			if( next.getTimestamp() >= x ) {
				firstRef = next;
				break;
			}
		}
		if( firstRef == null ) {
			//if null then there exists no timestamp of C >= x, so return null
			return null;
		}
		
		//run BFS from that node
		ComputerNode foundNode = runBFS(c2, y,firstRef);
		if( foundNode == null ) {
			//if null, no node was found
			return null;
		}
		
		//get the backtrack path
		ComputerNode curr = foundNode;
		ArrayList<ComputerNode> path = new ArrayList<ComputerNode>();
		
		while(curr.pred != null) {
			path.add(curr);
			curr = curr.pred;
		}
		path.add(firstRef);
		
		/*while( curr.getID() != firstRef.getID() && curr.getTimestamp() != firstRef.getTimestamp() ) {
			path.add(curr);
			curr = curr.pred;
		}*/
		
		return path;
	}
	
	public ComputerNode runBFS(int c2, int y, ComputerNode cn) {
		
		//run standard BFS but include an if statement, checking if each node is
		//from c2 with a timestamp <= y
		//Start with cn and run BFS on that
		
		//reset everything
		Iterator<Integer> it = graph.keySet().iterator();
		while( it.hasNext() ) {
			List<ComputerNode> next = graph.get(it.next());
			for(int i = 0; i < next.size(); i++) {
				next.get(i).color = 0;
				next.get(i).pred = null;
			}
		}
		
		//List for current layer nodes and list of nodes visited
		Queue<ComputerNode> currQ = new LinkedList<>();
		//ArrayList<ComputerNode> visited = new ArrayList<>();
		
		currQ.add(cn);
		cn.color = 1; cn.dist = 0;
		//While the layer of node is not empty
		while(!currQ.isEmpty()) {
			
			ComputerNode current = currQ.peek();
			//For each neighbor of current
			List<ComputerNode> neigh = current.getOutNeighbors();
			for(int i = 0; i < current.getOutNeighbors().size(); i++) {
				ComputerNode c = neigh.get(i);
				if(c.color == 0) {
					c.color = 1;
					c.dist = current.dist + 1;
					c.pred = current;
					currQ.add(c);
				}
				if(c.getID() == c2 && c.getTimestamp() <= y)
					return c;
			}
			currQ.remove();
			current.color = 2;
		}
			
			
			
			
			/*
			
			//List of nodes for the next layer
			Queue<ComputerNode> nextQ = new LinkedList<>();
			
			//Goes through the current layer of nodes
			for(int i = 0; i < currQ.size(); i++) {
				ComputerNode current = currQ.remove();
				List<ComputerNode> neigh = current.getOutNeighbors();
				
				//Goes through all the neighbors of the current node 
				for(int j = 0; j < current.getOutNeighbors().size(); j++) {
					
					//Checks if the node has already been visited
					if(!visited.contains(neigh.get(j))) {
						neigh.get(j).pred = current;
						nextQ.add(neigh.get(j));
					}
					
					//If this is the node we want
					if(neigh.get(j).getID() == c2 && neigh.get(j).getTimestamp() <= y)
						return neigh.get(j);
				}
				visited.add(current);
			}
			currQ = nextQ;
		}
		//If we don't find the node 
		 * 
		 */
		return null;
	}
	
	
	
	public HashMap<Integer, List<ComputerNode>> getComputerMapping(){
		return graph;
	}
	
	
	public List<ComputerNode> getComputerMapping(int c){
		return graph.get(c);
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
