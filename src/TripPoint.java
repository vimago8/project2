import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Grace Lee
 * @version 1.0
 *
 */
public class TripPoint {
	private double lat;
	private double lon;
	private int time;
	private static ArrayList<TripPoint> trip= new ArrayList<TripPoint>();
	
	/**
	 * The constructor for a TripPoint
	 * @param time (minutes)
	 * @param lat coordinate
	 * @param lon coordinate
	 */
	public TripPoint (int time, double lat, double lon) {
		this.time = time;
		this.lat = lat;
		this.lon = lon;
	}
	
	/**
	 * Returns the time class field, in minutes
	 * @return this.time
	 */
	public int getTime () {
		return this.time;
	}
	
	/**
	 * Returns latitude 
	 * @return this.lat
	 */
	public double getLat() {
		return this.lat;
	}
	/**
	 * Returns longitude
	 * @return this.lon
	 */
	public double getLon() {
		return this.lon;
	}
	/**
	 * Returns the ArrayList trip, which consists of TripPoints read in from triplog.csv
	 * @return trip
	 * @throws IOException 
	 */
	public static ArrayList<TripPoint> getTrip() {
		
		return trip;
	}
	

	
	public static void readFile(String filename) throws IOException {
		trip.clear();
		
		trip = new ArrayList<TripPoint>();
		
		File file = new File(filename);
		Scanner scnr = new Scanner(file);
        scnr.nextLine(); // skip first line
        

        while ((scnr.hasNextLine())) {
        	String line = scnr.nextLine();
        	String[] delimitedLine = line.split(",");
        	int time = Integer.parseInt(delimitedLine[0]);
        	double lat = Double.parseDouble(delimitedLine[1]);
        	double lon = Double.parseDouble(delimitedLine[2]);

            trip.add(new TripPoint(time, lat, lon));

        }

        scnr.close();
	}
	
	/**
	 * Adds up all time values in the trip array, then converts to hours
	 * @return time in hours
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static double totalTime() throws FileNotFoundException, IOException {
		//return total time of trip in hours 
		ArrayList<TripPoint>trip = getTrip();
		double timeInMinutes = 0;
		
		for (int i = 0; i < trip.size() - 1; ++i) { //trip.size() - 1 accounts for blank line at the end of file
			timeInMinutes += 5;
		}
		
		timeInMinutes /= 60.0;
		
		return timeInMinutes;// Math.round(timeInMinutes * 100.0) / 100.0; //time in hours
	}
	
	/**
	 * Returns the haversineDistance between TripPoints a and b. The haversine distance is in kilometers. 
	 * @param a first TripPoint
	 * @param b second TripPoint
	 * @return distance (kilometers)
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static double haversineDistance(TripPoint a, TripPoint b) {
		final int earthRadius = 6371; //in kilometers
		
		//trip = new ArrayList<TripPoint>();
		//TripPoint.readFile("triplog.csv");
		
		double latA = a.getLat();
		double latB = b.getLat();
		
		double lonA = a.getLon();
		double lonB = b.getLon();
		
		double latDistance = Math.toRadians(latB - latA);
		double lonDistance = Math.toRadians(lonB - lonA);
		
		double part1 = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
				 Math.cos(Math.toRadians(latA)) * Math.cos(Math.toRadians(latB)) * 
				 Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double part2 = 2 * Math.atan2(Math.sqrt(part1), Math.sqrt(1-part1));
		double distance = earthRadius * part2;
		
		return distance;
	}
	
	/**
	 * Returns the total by calculating the haversine distance between each pair of adjacent TripPoints in the trip
	 * array, then adding them up. totalDistance is in kilometers. 
	 * @return totalDistance
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static double totalDistance () throws FileNotFoundException, IOException {
		double totalDistance = 0;
		
		//TripPoint.readFile("triplog.csv");
		ArrayList<TripPoint>trip = getTrip();
		
		for (int i = 0; i < trip.size() - 1; ++i) {
			TripPoint a= trip.get(i);
			TripPoint b = trip.get(i+1);
			
			totalDistance += haversineDistance(a, b);
		}
		//totalDistance = Math.round(totalDistance * 1000.0) / 1000.0;
		return totalDistance;
	}
	
	/**
	 * Returns the average speed between two TripPoints. Speed is in kilometers/hour. 
	 * @param a
	 * @param b
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static double avgSpeed(TripPoint a, TripPoint b) {
		//TripPoint.readFile("triplog.csv");
		
		double timeElapsed = Math.abs(a.getTime() - b.getTime()); //minutes
		double distAB = haversineDistance(a, b); //kilometers
		
		timeElapsed /= 60.0;
		
		return distAB/timeElapsed;
	}
	
	
	//helper methods
	
}
