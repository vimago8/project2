import java.io.FileNotFoundException;

public class TestDriver {
	public static void main (String[] args) throws FileNotFoundException {
		TripPoint.readFile("triplog.csv");
		
		//System.out.println(TripPoint.getTrip().toString());
		
		System.out.println(TripPoint.totalTime());
		
		System.out.println(TripPoint.totalDistance());
		
		
	}
}
