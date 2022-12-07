import java.io.IOException;

/*
 * Clear method copy and pasted from code given to me in class
 * A class used to clear the monitor
 */

public class Clear {

	/*
	 * A method that clears the monitor
	 */
	public static void clear()

    {
        try
        {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    } //end clear
	
	
}//end class
