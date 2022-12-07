import java.util.*;
import java.util.function.Consumer;
import java.io.*;
/**
 * @author Joshua Domzalski
 * Airport Simulation
 * A class representing a standard Airport. This class is extending TimerTask
 */
public class Airport extends TimerTask{

	/*
	 * Declaring different types of variables that will be used/updated throughout the methods of the class
	 */
	Random random = new Random();
	public static Queue<Airplane> ReadyToLandQueue = new LinkedList<Airplane>();
	public static Queue<Airplane> ApproachQueue = new LinkedList<Airplane>();
	public static ArrayList<Airplane> RunwayOne = new ArrayList<Airplane>();
	public static ArrayList<Airplane> RunwayTwo = new ArrayList<Airplane>();
	public static ArrayList<Airplane> RunwayThree = new ArrayList<Airplane>();
	public static ArrayList<Airplane> CompletedFlights = new ArrayList<Airplane>();
	Clear c = new Clear();
		
	public static int i = 0;
	
	/*
	 * Our method that will run our program. This method is running on a timer, and will not stop until program is manually stopped
	 */
	public void run() {
		/*
		 * Printing out what time step we are on
		 */
		System.out.println("\n***** Timer ran ***** "+i++);
		/*
		 * Creating a new random number from 1-1000
		 */
		int rand = random.nextInt(1000)+1;
		/*
		 * At each time step, there is a 70% chance that a new plane will be created.
		 * If a new plane is created, it is added to the ApproachQueue
		 */
		if(rand>=300) {
			Airplane A = new Airplane();
				ApproachQueue.add(A);
		}
		/*
		 * Checking each plane currently in the ApproachQueue and adding it to the ReadyToLandQueue if the plane has reached the airport.
		 */
		for(Airplane a: ApproachQueue) {
			if(a.distance<= 0 && !ReadyToLandQueue.contains(a)) {
				ReadyToLandQueue.add(a);
			}
	}
		/*
		 * Removing the plane added to the ReadyToLandQueue from the ApproachQueue
		 */
		for(Airplane b: ReadyToLandQueue) {
			if(!ApproachQueue.isEmpty()) {
			ApproachQueue.remove(b);
		}
		}
		/*
		 * If a plane is ready to land, this generates the small possibility for an emergency.
		 * If an emergency is generated, the plane is immediately landed in Runway Three
		 */
		for(Airplane b: ReadyToLandQueue) {
			int emergency = random.nextInt(500)+1;
			if(emergency>499) {
				RunwayThree.clear();
				RunwayThree.add(b);
			}
			
		}
		
		/*
		 * When a plane is ready to land, it will be added to the next available Runway.
		 */
		for(Airplane b: ReadyToLandQueue) {
			if(RunwayOne.isEmpty()) {
				RunwayOne.add(b);
			}
			else if(!RunwayOne.isEmpty() && RunwayTwo.isEmpty() && !RunwayOne.contains(b)) {
				RunwayTwo.add(b);
			}
			else if(!RunwayOne.isEmpty() && !RunwayTwo.isEmpty() && RunwayThree.isEmpty() && !RunwayOne.contains(b)
					&& !RunwayTwo.contains(b)) {
				RunwayThree.add(b);
			}
		}
		/*
		 * Adding each plane that makes it to Runway One to a "Completed Flights" list.
		 */
		for(Airplane c: RunwayOne) {
			CompletedFlights.add(c);
		}
		/*
		 * Removing each plane that makes it to Runway One from the ReadyToLand queue.
		 */
		for(Airplane c: RunwayOne) {
				if(!ReadyToLandQueue.isEmpty()) {
					ReadyToLandQueue.remove(c);
				}				
		}
		/*
		 * Adding each plane that makes it to Runway Two to a "Completed Flights" list.
		 */
		for(Airplane d: RunwayTwo) {
			CompletedFlights.add(d);
		}
		/*
		 * Removing each plane that makes it to Runway Two from the ReadyToLand queue.
		 */
		for(Airplane d: RunwayTwo) {
			if(!ReadyToLandQueue.isEmpty()) {
				ReadyToLandQueue.remove(d);
			}
		}
		/*
		 * Adding each plane that makes it to Runway Three to a "Completed Flights" list.
		 */
		for(Airplane e: RunwayThree) {
			CompletedFlights.add(e);
		}
		/*
		 * Removing each plane that makes it to Runway Three to a "Completed Flights" list.
		 */
		for(Airplane e: RunwayThree) {
			if(!ReadyToLandQueue.isEmpty()) {
				ReadyToLandQueue.remove(e);
			}
		}
		
		System.out.println("\nAPPROACHING");
		if(!ApproachQueue.isEmpty()) {
			for(Airplane a: ApproachQueue) {
				System.out.println("Flight #"+a.ID + " "+"Miles: "+a.distance);
				a.distance = (a.distance - 40);
			}
		}
		else if(ApproachQueue.isEmpty()) {
			System.out.println("No active flights approaching airport");
		}
		
		System.out.println("\nREADY TO LAND");
		if(!ReadyToLandQueue.isEmpty()) {
			for(Airplane a: ReadyToLandQueue) {
				System.out.println(a.ID);
				System.out.println("A runway will be cleared in 5 minutes");
			}
		}
		else if(ReadyToLandQueue.isEmpty()) {
			System.out.println("No active flights waiting to land");
		}
		
		System.out.println("\nRUNWAY ONE");
		if(!RunwayOne.isEmpty()) {
			for(Airplane a: RunwayOne) {
				System.out.println(a.ID);
			}
		}
		else if(RunwayOne.isEmpty()) {
			System.out.println("Clear");
		}
		
		System.out.println("\nRUNWAY TWO");
		if(!RunwayTwo.isEmpty()) {
			for(Airplane a: RunwayTwo) {
				System.out.println(a.ID);
			}
		}
		else if(RunwayTwo.isEmpty()) {
			System.out.println("Clear");
		}
		
		System.out.println("\nRUNWAY THREE");
		if(!RunwayThree.isEmpty()) {
			for(Airplane a: RunwayThree) {
				System.out.println(a.ID);
			}
		}
		else if(RunwayThree.isEmpty()) {
			System.out.println("Clear");
		}
		

		/**
		 * Clearing each Runway after every respective 5 minute time step
		 */
		for(Airplane f: CompletedFlights) {
			if(!RunwayOne.isEmpty()) {
				RunwayOne.remove(f);
			}
		}
		for(Airplane g: CompletedFlights) {
			if(!RunwayTwo.isEmpty()) {
				RunwayTwo.remove(g);
			}
		}
		for(Airplane h: CompletedFlights) {
			if(!RunwayThree.isEmpty()) {
				RunwayThree.remove(h);
			}
		}
	
	/**
	 * Clearing the console (Does not working within IDE console)
	 */
	c.clear();
	

}//end run
}//end class
