import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestDriver {
	public static void main (String[] args) throws FileNotFoundException, IOException {
		TripPoint.readFile("triplog.csv");
		
		//System.out.println(TripPoint.getTrip().toString());
		System.out.println(TripPoint.getTrip().toString());
		
		for (int i = 0; i < TripPoint.getTrip().size(); ++i) {
			System.out.println(TripPoint.getTrip().get(i).getLat() + " " +
					TripPoint.getTrip().get(i).getLon() + " " + 
					TripPoint.getTrip().get(i).getTime());
		}
		
		//System.out.println(TripPoint.totalTime());
		
		//System.out.println(TripPoint.totalDistance());
		
		
	}
}
