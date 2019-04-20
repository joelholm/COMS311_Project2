/**
 * This class represents a Communication Triple.  This is used in the CommunicationsMonitor
 * class before create graph is called.  
 * 
 * @author Joel Holm, Colin Ishman
 */
public class CommunicationTriple {

	private int c1,c2;
	private int timestamp;
	
	public CommunicationTriple(int c1, int c2, int timestamp) {
		this.c1 = c1;
		this.c2 = c2;
		this.timestamp = timestamp;
	}
	
	public int getTimestamp() {
		return timestamp;
	}
	
	public int getC1() {
		return c1;
	}
	
	public int getC2() {
		return c2;
	}
}
