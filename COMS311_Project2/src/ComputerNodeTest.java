import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;

class ComputerNodeTest {

	@Test
	void test() {
		
		ComputerNode[] n = new ComputerNode[6];
		n[0] = new ComputerNode(1,1);
		n[1] = new ComputerNode(2,2);
		n[2] = new ComputerNode(3,3);
		n[3] = new ComputerNode(1,4);
		n[4] = new ComputerNode(2,6);
		n[5] = new ComputerNode(4,9);
		
		n[0].addNeighborNode(n[1]);
		n[0].addNeighborNode(n[3]);
		n[1].addNeighborNode(n[3]);
		n[1].addNeighborNode(n[4]);
		n[2].addNeighborNode(n[5]);
		n[3].addNeighborNode(n[5]);
		n[4].addNeighborNode(n[5]);
		
		for( int j = 0; j < 6; j++) {
			System.out.println("Node " + j + " neighbors");
			List<ComputerNode> nebs = n[j].getOutNeighbors();
			Iterator<ComputerNode> it = nebs.iterator();
			int num = nebs.size();
			for( int i = 0; i < num; i++ ) {
				ComputerNode curr = it.next();
				System.out.println("\tID: " + curr.getID() + " Timestamp: " + curr.getTimestamp());
			}
		}
	}

}
