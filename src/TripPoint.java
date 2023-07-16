import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TripPoint {
	private double lat;
	private double lon;
	private int time;
	private static ArrayList<TripPoint> trip = new ArrayList<TripPoint>();
	
	//constructor
	public TripPoint (int time, double lat, double lon) {
		this.time = time;
		this.lat = lat;
		this.lon = lon;
	}
	
	//getters
	public int getTime () {
		return this.time;
	}
	
	public double getLat() {
		return this.lat;
	}
	
	public double getLon() {
		return this.lon;
	}
	
	public static ArrayList<TripPoint> getTrip() {
		return trip;
	}
	
	//other methods
	public static void readFile(String filename) throws FileNotFoundException {
		trip.clear();
		
		
		
		//read in data from triplog.csv to arraylist (time in minutes)
		
		File triplog = new File(filename);
		Scanner scnr = new Scanner(triplog);
		TripPoint point;
		
		//skip the time/longitude/latitude header
		scnr.nextLine();
		
		//Arraylist of each file line
//		ArrayList<String> lines = new ArrayList<String>();
//		
//		while(scnr.hasNextLine()) {
//			String line = scnr.nextLine();
//			lines.add(line);
//		}
//		
//		for (int i = 0; i < lines.size(); ++i) {
//			
//		}
		
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
//		System.out.println(trip.get(0).getTime());
//		System.out.println(trip.get(0).getLat());
//		System.out.println(trip.get(0).getLon());
//		System.out.println(trip.size());
//		System.out.println(trip.get(trip.size()-1).getTime());
//		int tempTime = scnr.nextInt();
//		double tempLat = scnr.nextDouble();
//		double tempLong = scnr.nextDouble();
////		
//		System.out.println(tempTime);
//		System.out.println(tempLat);
//		System.out.println(tempLong);
		
//		System.out.println(lines.get(0));
//		System.out.println(lines.get(1));
//		System.out.println(lines.get(2));
		scnr.close();
		
		
		
}
	
	public static double totalTime() {
		//return total time of trip in hours 
		double timeInMinutes = 0;
		
		for (int i = 0; i < trip.size() - 1; ++i) { //trip.size() - 1 accounts for blank line at the end of file
			timeInMinutes += 5;
			System.out.println(timeInMinutes);
		}
		
		timeInMinutes /= 60.0;
		
		return Math.round(timeInMinutes * 100.0) / 100.0; //time in hours
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
		totalDistance = Math.round(totalDistance * 1000.0) / 1000.0;
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
