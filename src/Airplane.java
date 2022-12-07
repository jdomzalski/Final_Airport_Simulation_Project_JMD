import java.util.*;
public class Airplane {
	
	Random rand = new Random();
	public int ID = rand.nextInt(50000)+10000;
	public int distance = rand.nextInt(200)+1;
	public int priority = rand.nextInt(20)+1;
	
	
	public Airplane() {
		this.ID = ID;
		this.distance = distance;
		this.priority = priority;
	
	}
	
}
