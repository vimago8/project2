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
		
		for (int i = 0; i < trip.size() - 1; ++i) {
			timeInMinutes += 5;
			System.out.println(timeInMinutes);
		}
		
		return timeInMinutes / 60.0; //time in hours
	}
	
	//haversine distance calculation
	public static double haversineDistance(TripPoint a, TripPoint b) {
		return 0.0;
	}
	
	public static double totalDistance () {
		return 0.0;
	}
	
	public static double avgSpeed(TripPoint a, TripPoint b) {
		return 0.0;
	}
	
	//helper methods
	
}
