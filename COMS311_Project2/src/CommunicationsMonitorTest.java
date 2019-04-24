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
	
	@Test
	void addCommunicationTest() {
		
		CommunicationsMonitor comMon = new CommunicationsMonitor();
		for( int i = 0; i < 10; i++ ) {
			comMon.addCommunication(i + 1, i + 6, i + 1);
		}
		comMon.addCommunication(-1, -100, -100);
		
		if( false ) {
			for( int i = 0; i <= 10; i++ ) {
				System.out.println(comMon.triples.get(i).toString());
			}
		}
		assertEquals(comMon.triples.get(10).getC1(),-1);
	}
	
	@Test
	void createGraphTest() {
		CommunicationsMonitor comMon = new CommunicationsMonitor();
		comMon.addCommunication(1,2,4);
		comMon.addCommunication(2,4,8);
		comMon.addCommunication(3,4,8);
		comMon.addCommunication(1,4,12);
		
		comMon.createGraph();
		
		/*for(int i = 1; i <= 4; i++ ) {
			ArrayList<ComputerNode> list = (ArrayList<ComputerNode>)comMon.graph.get(i);
			Iterator<ComputerNode> it = list.iterator();
			while( it.hasNext() ) {
				System.out.println(it.next().toString());
			}
		}*/
		
		assertEquals(4, comMon.graph.get(1).get(0).getTimestamp());
		assertEquals(12, comMon.graph.get(1).get(1).getTimestamp());
		assertEquals(4, comMon.graph.get(2).get(0).getTimestamp());
		assertEquals(8, comMon.graph.get(2).get(1).getTimestamp());
		assertEquals(8, comMon.graph.get(3).get(0).getTimestamp());
		assertEquals(8, comMon.graph.get(4).get(0).getTimestamp());
		assertEquals(12, comMon.graph.get(4).get(1).getTimestamp());
		
		CommunicationsMonitor comMon2 = new CommunicationsMonitor();
		comMon2.addCommunication(1,3,6);
		comMon2.addCommunication(2,3,7);
		comMon2.addCommunication(4,1,4);
		comMon2.addCommunication(3,2,12);
		comMon2.addCommunication(1,3,7);
		comMon2.addCommunication(4,3,10);
		
		comMon2.createGraph();
		
		assertEquals(4,  comMon2.graph.get(1).get(0).getTimestamp());
		assertEquals(6,  comMon2.graph.get(1).get(1).getTimestamp());
		assertEquals(7,  comMon2.graph.get(1).get(2).getTimestamp());
		assertEquals(7,  comMon2.graph.get(2).get(0).getTimestamp());
		assertEquals(12, comMon2.graph.get(2).get(1).getTimestamp());
		assertEquals(6,  comMon2.graph.get(3).get(0).getTimestamp());
		assertEquals(7,  comMon2.graph.get(3).get(1).getTimestamp());
		assertEquals(10, comMon2.graph.get(3).get(2).getTimestamp());
		assertEquals(12, comMon2.graph.get(3).get(3).getTimestamp());
		assertEquals(4,  comMon2.graph.get(4).get(0).getTimestamp());
		assertEquals(10, comMon2.graph.get(4).get(1).getTimestamp());
		
	}
	
	@Test
	void testQueryInfectionColor() {
		CommunicationsMonitor comMon = new CommunicationsMonitor();
		comMon.addCommunication(1,2,4);
		comMon.addCommunication(2,4,8);
		comMon.addCommunication(3,4,8);
		comMon.addCommunication(1,4,12);
		comMon.addCommunication(4,3,15);
		comMon.addCommunication(2,4,16);
		comMon.addCommunication(1,3,20);
		
		comMon.createGraph();
		List<ComputerNode> list = comMon.queryInfection(2,3,8,12);
		for(int i = 0; i < list.size(); i++) 
			System.out.print("ID: " + list.get(i).getID() + "   Time: " + list.get(i).getTimestamp() + "\n");
		list = comMon.queryInfection(2,4,8,12);
		System.out.print("\n");
		for(int i = 0; i < list.size(); i++) 
			System.out.print("ID: " + list.get(i).getID() + "   Time: " + list.get(i).getTimestamp() + "\n");
		
	}
	
	
	//tests BFS
	@Test
	void testQueryInfection() {
		CommunicationsMonitor comMon = new CommunicationsMonitor();
		comMon.addCommunication(1,3,6);
		comMon.addCommunication(2,3,7);
		comMon.addCommunication(4,1,4);
		comMon.addCommunication(3,2,12);
		comMon.addCommunication(1,3,7);
		comMon.addCommunication(4,3,10);
		
		comMon.createGraph();
		
		List<ComputerNode> list1 = comMon.queryInfection(1, 3, 2, 8);
		assertEquals(true, list1 != null);
		
		list1 = comMon.queryInfection(1, 2, 8, 12);
		assertEquals(true, list1 == null);
		
		list1 = comMon.queryInfection(4, 2, 3, 6);
		assertEquals(true, list1 == null);
		
		list1 = comMon.queryInfection(4, 2, 0, 12);
		assertEquals(true, list1 != null);
		
		System.out.println("Size: " + list1.size());
		Iterator<ComputerNode> it = list1.iterator();
		ComputerNode curr;
		while( it.hasNext() ) {
			curr = it.next();
			System.out.println(curr.toString());
		}
		
		
		CommunicationsMonitor comMon2 = new CommunicationsMonitor();
		comMon2.addCommunication(1,2,4);
		comMon2.addCommunication(2,4,8);
		comMon2.addCommunication(3,4,8);
		comMon2.addCommunication(1,4,12);
		
		comMon2.createGraph();
		List<ComputerNode> list2 = comMon2.queryInfection(1, 4, 4, 10);

		
		List<ComputerNode> list3 = comMon2.queryInfection(1, 3, 1, 15);
		List<ComputerNode> list4 = comMon2.queryInfection(4, 3, 9, 12);
		assertEquals(true, list2 != null);
		assertEquals(true, list3 != null);
		assertEquals(true, list4 == null);
		
		
		if(list2 != null)
			for(int i = 0; i < list2.size(); i++) 
				System.out.print("ID: " + list2.get(i).getID() + "   Time: " + list2.get(i).getTimestamp() + "\n");
		System.out.print("\n");
		
	/*	List<ComputerNode> list3 = comMon2.queryInfection(1, 3, 1, 15);
		if(list3 != null)
			for(int i = 0; i < list3.size(); i++) 
				System.out.print("ID: " + list3.get(i).getID() + "   Time: " + list3.get(i).getTimestamp() + "\n");
		
		List<ComputerNode> list4 = comMon2.queryInfection(4, 3, 1, 15);
		if(list4 != null)
			for(int i = 0; i < list4.size(); i++) 
				System.out.print("ID: " + list4.get(i).getID() + "   Time: " + list4.get(i).getTimestamp() + "\n");
	*/
	
	}
	
	@Test
	void testBothgetComputerMapping() {
		CommunicationsMonitor comMon = new CommunicationsMonitor();
		comMon.addCommunication(1,2,4);
		comMon.addCommunication(2,4,8);
		comMon.addCommunication(3,4,8);
		comMon.addCommunication(1,4,12);
		
		comMon.createGraph();
		
		HashMap<Integer,List<ComputerNode>> hash = comMon.getComputerMapping();
		List<ComputerNode> list = comMon.getComputerMapping(1);
		assertEquals( 12 , list.get(1).getTimestamp() );
		list = comMon.getComputerMapping(-1);
		assertEquals( true , list == null );
		assertEquals(8, hash.get(3).get(0).getTimestamp());
	}

}
