//DOWNLOAD THIS FILE AND RUN IT A FEW TIMES WITH TEST VALUES!!
//IT'LL BE A LOT EASIER TO UNDERSTAND THE CODE AFTER EVEN 1 min of testing
package srboost.costCalculator;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.text.NumberFormatter;

/**
 * This class calculates the amount of money needed to boost a player to their
 * desired rank. Calculations are based on $$ per 100 SR (Dependent on rank)
 * 
 * @author Kevin
 *
 */
public class CalculationFinal {

	/** Scanner for input (Java-Only) */
	public static Scanner in = new Scanner(System.in);

	/** The customer's current SR at time of purchase */
	private static int startingSR;

	/** The SR which the customer wishes to obtain */
	private static int desiredSR;


	//This is the cost in $$ per 100 sr within each rank. (ie. if amtBronze was
	//$1, it would take $1 to go from 100 --> 200)
	private static double amtBronze = .50;
	private static double amtSilver = .75;
	private static double amtGold = 1.0;
	private static double amtPlatinum = 1.5;
	private static double amtDiamond = 2;
	private static double amtMaster = 7.5;
	private static double amtGrandmaster = 75;

	//An array makes the code much more simplified later, I took the time to write out every single variable so you wouldn't get confused <3 (no hobo)
	/**Put these values of the prices ^^^^ in an array*/
	private static double[] prices = {amtBronze, amtSilver, amtGold, amtPlatinum, amtDiamond, amtMaster, amtGrandmaster};

	/**Formatter for instantly converting doubles into currency values*/
	private static NumberFormat formatter = NumberFormat.getCurrencyInstance();

	
	
	/**
	 * The main method of the CalculationFinal class
	 * Serves to break the process into manageable methods 
	 * Keeps everything on constant loop
	 * @param args
	 */
	public static void main(String[] args) 
	{
		while (true) 
		{
			//Obtains the user's current rank and desired rank
			getRanks();
			
			//Clears up console
			printSeparator();
			
			//Calculates SR needed to get to each next tier, and total cost
			calculateCost();
		}

	}



	/**
	 * Private helper method that obtains the rank information about the client.
	 * Contains check to see if any values are incorrect
	 */
	private static void getRanks() 
	{
		//Obtain client's current SR
		System.out.print("Enter your current Skill Rating: ");
		startingSR = in.nextInt();

		//Obtain client's desired SR
		System.out.print("\nEnter your desired Skill Rating: ");
		desiredSR = in.nextInt();

		//Check for invalid values (runs method again if something is wrong)
		if (startingSR >= 5000 || startingSR < 1 || desiredSR > 5000 || desiredSR <= 1 || (startingSR >= desiredSR)) 
		{
			System.out.println("A value was invalid! Please try again... \n");
			getRanks();
		}
	}

	
	/**
	 * Prints a separator line (why TF did I make this...?)
	 */
	private static void printSeparator()
	{
		System.out.print("\n-----------------------------------------------\n");
	}


	/**Private helper method that calculates the total cost of the boost, while also printing out some key details
	 * Slightly tricky, but just read the comments and you'll understand
	 */
	private static void calculateCost()
	{
		//The SR variable used to calculate the SR gains in each tier (constantly updated)
		int SR = startingSR;

		//Variables to keep track of how much SR is needed to be gained through each tier
		int SRGainBronze = 0;
		int SRGainSilver = 0;
		int SRGainGold = 0;
		int SRGainPlatinum = 0;
		int SRGainDiamond = 0;
		int SRGainMaster = 0;
		int SRGainGrandmaster = 0;
		
		//Again, these values need to be in an array, or else the code is far too messy later on.
		/**The amounts needed to reach the next tiers*/
		int[] SRGains = {SRGainBronze, SRGainSilver, SRGainGold, SRGainPlatinum, SRGainDiamond, SRGainMaster, SRGainGrandmaster};

		//Total SR needed to go from starting to desired
		int totalSRGain = desiredSR - startingSR;

		//The total cost
		double cost = 0;





		/**THE FOLLOWING IF STATEMENTS FOLLOW THE SAME LOGIC:
		 * 1. Scan tiers Bronze through Grandmaster
		 * 2. If desired rank is above current tier that is being scanned, add appropriate amount to SRGain variable
		 * 3. Otherwise, calculate remaining rank needed
		 */
		
		//Scan each rank tier
		for(int i = 0; i < 7; i ++)
		{
			//(1500 for bronze, 2000 for silver, 2500 for gold, etc.)
			int tierMaxSR = 0;
			
			if (i == 0) tierMaxSR = 1500; //bronze
			if (i == 1) tierMaxSR = 2000; //silver
			if (i == 2) tierMaxSR = 2500; //gold
			if (i == 3) tierMaxSR = 3000; //plat
			if (i == 4) tierMaxSR = 3500; //diamond
			if (i == 5) tierMaxSR = 4000; //master
			if (i == 6) tierMaxSR = 5000; //grandmaster
			
			/**The following comments work on the first iteration only, for subsequent iterations, just update "Bronze" to correct rank tier name.*/
			
			//Check that the desired rank is above Bronze 
			if(desiredSR > tierMaxSR)
			{
				//Check that the starting SR isn't already above Bronze
				if (SR < tierMaxSR)
				{
					//Add appropriate amount to SRGainBronze
					SRGains[i] = tierMaxSR - SR;

					//Update SR variable, for we have accounted for the Bronze tier
					SR = tierMaxSR;
				}
			}

			//Runs if desiredSR is within Bronze tier
			else
			{
				//Add appropriate amount to SRGainBronze
				SRGains[i] = desiredSR - SR;

				//Update SR variable, for we have reached the desired rank
				SR = desiredSR;
			}
		}

		//Calculate the total price
		for(int i = 0; i < 7; i ++)
		{
			cost += (SRGains[i] * prices[i]/100.0);
		}
		
		//Make console easier to read
		printSeparator();

		//Final Cost and Gains
		System.out.println("Total Skill Rating Gain: " + totalSRGain + "SR for " + formatter.format(cost));

		//Final Breakdown of Calculations (really for the purpose of price review)
		System.out.println("\nBreakdown:\n");
		for(int i = 0; i < 7; i ++)
		{
			System.out.print("Skill rating gains in ");
			if (i == 0) System.out.print("Bronze");
			if (i == 1) System.out.print("Silver");
			if (i == 2) System.out.print("Gold");
			if (i == 3) System.out.print("Platinum");
			if (i == 4) System.out.print("Diamond");
			if (i == 5) System.out.print("Master");
			if (i == 6) System.out.print("Grandmaster");
			System.out.println(" tier: " + SRGains[i] + "SR for " + formatter.format(SRGains[i]*prices[i]/100.0));
		}
		
		//Make console easier to read
		printSeparator();

	}

}
