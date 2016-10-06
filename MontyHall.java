import java.util.Scanner;

/**
 * This class simulates the Monty Hall problem. 
 * @author Drew Beckmen
 * @version 1.0
 */

public class MontyHall
{
	/**
	 * Main method calls other methods for better abstraction 
	 * Walks user through the Monty Hall problem. 
	 * @param args
	 *
	 */
	public static void main(String[] args)
	{
		//Test whether or not the user has passed in an argument
		if (args.length == 0) 
			interactiveGame();
		else
		{
			//figure out how many sims to run
			int numberSims = Integer.parseInt(args[0]);
			System.out.println("Going to run " + numberSims + " Simulations.");
			
			simulateGame(numberSims);
		}
	}

	/**
	 * This method will carry out the simulation according to inputted number of times.
	 * @param numTimes - number of times to run the simulation
	 */
	public static void simulateGame(int numTimes)
	{
		//Variables to keep track of won games and if switched
		int swapGames = 0;
		int swapWins  = 0;
		int stayGames = 0;
		int stayWins  = 0;
		
		int userDoor; //MUST DO THIS OUTSIDE OF LOOP
		boolean swapOrStay; 
		boolean result; 
		while (numTimes > 0)
		{
			userDoor = (int) (Math.random() * 3 + 1);
			swapOrStay = Math.random() > 0.5; // Generate a random boolean. true half of time and false other half
			result = simulateSingleGame(userDoor, swapOrStay);
			
			if  (swapOrStay) {
				swapGames++;
				if (result){
					swapWins++;
				}	
			}
			else {
				stayGames++;
				if (result){
					stayWins++;
				}
			}
			numTimes--;
		}
	
		//Declare variables
		double swapSuccessRate;
		double staySuccessRate;
		
		//Prevent division by zero 
		if (swapGames > 0){
			swapSuccessRate = (double) swapWins / swapGames;
			System.out.println("You won " + swapSuccessRate + " of the games when you swapped.");
		}
		else
			System.out.println("The computer never played a game in which it chose to swap doors.");
		if (stayGames > 0){
			staySuccessRate = (double) stayWins / stayGames;
			System.out.println("You won " + staySuccessRate + " of the games when you stayed.");
		}
		else
			System.out.println("The computer never played a game in which it chose to stay with same door.");
		
		
		
	}
	
	/**
	 * Simulates a single game of Monty Hall
	 * @param userDoor - the door the user wants to play
	 * @param swapOrStay - whether the user wants to swap doors or keep the same door
	 * @return Whether the user wins a car
	 */	
	public static boolean simulateSingleGame(int userDoor, boolean swapOrStay)
	{
		//Choose a random car door
		int carDoor = (int) (Math.random() * 3 + 1);
		
		//Open a goat door
		int revealDoor = showGoat(carDoor, userDoor);
		
		// Swap if necessary
		if (swapOrStay){
			userDoor = findFinalDoor(swapOrStay, userDoor, revealDoor);
		}
		
		// Return whether they won
		return userDoor == carDoor; //returns a boolean
	}
 
	//Creating new method that holds main program of interactive game
	public static void interactiveGame()
	{
			
		
		System.out.println("Hello! Welcome to the Monty Hall Game! Hopefully you win a car today!");
		
		//Make sure to cast double to int
		int carDoor = (int) (Math.random() * 3 + 1);

		
		//Ask the user for a door
		int userDoor = getDoor();
		
		//Tell the user which door has a goat behind it
		int goatDoor = showGoat(carDoor, userDoor); 
		System.out.println("Just want to let you know that there is a goat behind door #" + goatDoor + ". Good work so far!");
		
		//Ask if they want to switch
		boolean switchDecision = askUserSwitch();
		
		//Get the final user door
		int finalDoor = findFinalDoor(switchDecision, userDoor, goatDoor);
		
		
		//Tell them if they win or not
		if (finalDoor == carDoor)
			System.out.println("I am so happy to tell you that you have won a new car!!!");
		else
			System.out.println("Darn! Didn't win a car today! Better luck next time...");
	}
	
	
	/**
	 * This method asks the user to choose a door 
	 * No parameters
	 * @return userDoor - the door that the user selects
	 */
	public static int getDoor()
	{	
		//Create Scanner (MUST DO IT IN EACH METHOD)
		Scanner keyboard = new Scanner(System.in);
		
		//Get user door
		System.out.println("There is a car behind doors 1, 2, or 3. Which one is it?");
		int userDoor = keyboard.nextInt();
		keyboard.nextLine(); 
		return userDoor;
	}
	
	/**
	 * This method finds the door the doesn't have the car and that the user hasn't picked
	 * @param carLocation - the door that the car is behind
	 * @param userPick - the door the user picked
	 * @return goatDoor - the door that has a goat behind it
	 */
	public static int showGoat(int carLocation, int userPick)
	{
		int goatDoor = 0; //Define beforehand
		for (int x = 1; x<=3; x ++)
		{
			if (x != carLocation && x != userPick)
				goatDoor = x ;
		}
		return goatDoor;
	}
	
	/**
	 * This method asks the user if they want to switch
	 * Returns booleans true for yes and false for no
	 * No parameters
	 * @return boolean whether or not user wants to switch 
	 */
	public static boolean askUserSwitch()
	{
		//Create Scanner
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Would you like to switch your door now that you have this new information? Type 'yes' or 'no'... ");
		String userInput = keyboard.nextLine();
		String userDecision = userInput.toLowerCase(); //make all lower case
		if (userDecision.equals("yes"))
			return true;
		else
			return false;
	}
	
	/**
	 * This method determines the final door reflecting any switches the user might have made 
	 * @param switchDecision - whether or not the user wanted to switch
	 * @param userDoor - the door user originally picked
	 * @param goatDoor - the door revealed with goat behind it
	 * @return finalDoor - the final door reflecting all changes
	 */
	public static int findFinalDoor(boolean switchDecision, int userDoor, int goatDoor)
	{
		int finalDoor = 0; //Define beforehand
		
		if (switchDecision == false)
			finalDoor = userDoor;
		else
		{
			for (int x = 1; x<=3; x ++)
			{
				if (x != goatDoor && x != userDoor)
					finalDoor = x; 
			}
		}
		return finalDoor;	
	}
}