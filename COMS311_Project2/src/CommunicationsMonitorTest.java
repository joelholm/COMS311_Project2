import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import java.util.*;

class CommunicationsMonitorTest {

	@Test
	void sortTest() {
		CommunicationsMonitor cm = new CommunicationsMonitor();
		
		List<CommunicationTriple> trip = new ArrayList<CommunicationTriple>();
		trip.add(new CommunicationTriple(2,3,15));
		trip.add(new CommunicationTriple(2,3,5));
		trip.add(new CommunicationTriple(2,3,10));
		trip.add(new CommunicationTriple(2,3,5));
		
		cm.mergeSort(trip);
		assertEquals(5, trip.get(0).getTimestamp());
		assertEquals(5, trip.get(1).getTimestamp());
		assertEquals(10, trip.get(2).getTimestamp());
		assertEquals(15, trip.get(3).getTimestamp());
	}
	
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
