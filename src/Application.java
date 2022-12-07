import java.util.*;
public class Application {

	public static void main(String[] args) {
		
		//Timestep t = new Timestep();
		//t.Timestep();
		
		//TimestepFun t = new TimestepFun();
		//t.TimestepFun();
		
		//MoreTimeStepFun t = new MoreTimeStepFun();
		//t.MoreTimeStepFun();
		
		
		Timer timer = new Timer();
		TimerTask task = new Airport();
		
		timer.schedule(task, 2000, 5000);
		

	}

}
