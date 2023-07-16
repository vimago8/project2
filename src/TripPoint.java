import java.io.File;
import java.io.FileNotFoundException;
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
	private static ArrayList<TripPoint> trip = new ArrayList<TripPoint>();
	
	/**
	 * The constructor for a TripPoint
	 * @param time
	 * @param lat
	 * @param lon
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
	 */
	public static ArrayList<TripPoint> getTrip() {
		return trip;
	}
	
	/**
	 * Reads triplog.csv and stores each line of time, lat, and long into TripPoints
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public static void readFile(String filename) throws FileNotFoundException {
		trip.clear();
		
		
		
		//read in data from triplog.csv to arraylist (time in minutes)
		
		File triplog = new File(filename);
		Scanner scnr = new Scanner(triplog);
		TripPoint point;
		
		//skip the time/longitude/latitude header
		scnr.nextLine();
		
		//set delimiter to commas in order to read csv values 
		//delimits by spaces and commas
		scnr.useDelimiter(",|\\n");
		
		int tempTime;
		double tempLat;
		double tempLong;
		
		//this data will loop
		while(scnr.hasNext()) {
			tempTime = scnr.nextInt();
			tempLat = scnr.nextDouble();
			tempLong = scnr.nextDouble();
			
			//System.out.println(tempTime + " " + tempLat + " " + tempLong);
			point = new TripPoint(tempTime, tempLat, tempLong);
			//System.out.println(point.getLat());
	
			trip.add(point);
			//System.out.println(trip.get(0).getTime());
			
		}

		scnr.close();
		
		
		
}
	/**
	 * Adds up all time values in the trip array. 
	 * @return time in hours
	 */
	public static double totalTime() {
		//return total time of trip in hours 
		double timeInMinutes = 0;
		
		for (int i = 0; i < trip.size() - 1; ++i) { //trip.size() - 1 accounts for blank line at the end of file
			timeInMinutes += 5;
		}
		
		timeInMinutes /= 60.0;
		
		return timeInMinutes;// Math.round(timeInMinutes * 100.0) / 100.0; //time in hours
	}
	
	//haversine distance calculation
	public static double haversineDistance(TripPoint a, TripPoint b) {
		final int earthRadius = 6371; //in kilometers
		
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
	
	
	public static double totalDistance () {
		double totalDistance = 0;
		for (int i = 0; i < trip.size() - 1; ++i) {
			TripPoint a= trip.get(i);
			TripPoint b = trip.get(i+1);
			
			totalDistance += haversineDistance(a, b);
		}
		//totalDistance = Math.round(totalDistance * 1000.0) / 1000.0;
		return totalDistance;
	}
	
	public static double avgSpeed(TripPoint a, TripPoint b) {
		double timeElapsed = Math.abs(a.getTime() - b.getTime()); //minutes
		double distAB = haversineDistance(a, b); //kilometers
		
		timeElapsed /= 60.0;
		
		return distAB/timeElapsed;
	}
	
	
	//helper methods
	
}
