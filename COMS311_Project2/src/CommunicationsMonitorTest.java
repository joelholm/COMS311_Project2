import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

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

}
