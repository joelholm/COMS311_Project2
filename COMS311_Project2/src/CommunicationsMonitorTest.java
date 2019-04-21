import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;

class CommunicationsMonitorTest {

	@Test
	void addCommunicationTest() {
		
		CommunicationsMonitor comMon = new CommunicationsMonitor();
		for( int i = 0; i < 10; i++ ) {
			comMon.addCommunication(i + 1, i + 6, i + 1);
		}
		comMon.addCommunication(-1, -100, -100);
		/*
		if( true ) {
			for( int i = 0; i <= 10; i++ ) {
				System.out.println(comMon.triples.get(i).toString());
			}
		}
		*/
	}
	
	@Test
	void createGraphTest() {
		CommunicationsMonitor comMon = new CommunicationsMonitor();
		comMon.addCommunication(1,2,4);
		comMon.addCommunication(2,4,8);
		comMon.addCommunication(3,4,8);
		comMon.addCommunication(1,4,12);
		
		comMon.createGraph();
		
		for(int i = 0; i < 4; i++ ) {
			ArrayList<ComputerNode> list = comMon.graph.get(i);
			Iterator<ComputerNode> it = list.iterator();
			while( it.hasNext() ) {
				System.out.println(it.next().toString());
			}
		}
		
		assertEquals(comMon.graph.get(1).get(1).getTimestamp(), 4);
		assertEquals(comMon.graph.get(1).get(2).getTimestamp(), 12);
		assertEquals(comMon.graph.get(2).get(1).getTimestamp(), 4);
		assertEquals(comMon.graph.get(1).get(2).getTimestamp(), 8);
		//...
		
	}

}
