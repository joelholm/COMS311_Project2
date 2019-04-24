/**
 * This class represents a Communication Triple.  This is used in the CommunicationsMonitor
 * class before create graph is called.  
 * 
 * @author Joel Holm, Colin Ishman
 */

import java.lang.String;

public class CommunicationTriple {

	private int c1,c2;
	private int timestamp;
	
	/**
	 * Constructor for the class
	 * @param c1 The first computer
	 * @param c2 The second computer
	 * @param timestamp Time the computers interact
	 */
	public CommunicationTriple(int c1, int c2, int timestamp) {
		this.c1 = c1;
		this.c2 = c2;
		this.timestamp = timestamp;
	}
	
	/**
	 * Getter method for timestap
	 * @return the timestamp of the triple
	 */
	public int getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Getter method for C1
	 * @return The ID of the first computer
	 */
	public int getC1() {
		return c1;
	}
	
	/**
	 * Getter method for C2
	 * @return The ID of the second computer
	 */
	public int getC2() {
		return c2;
	}
	
	/**
	 * Override for toString
	 */
	@Override
	public String toString() {
		return new String("c1: " + c1 + " c2: " + c2 + " timestamp: " + timestamp);
	}
}
