import java.util.Scanner;

public class MontyHall
{
	public static void main(String[] args)
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
		
		//Tell them if they win or not
		if (finalDoor == carDoor)
			System.out.println("I am so happy to tell you that you have won a new car!!!");
		else
			System.out.println("Darn! Didn't win a car today! Better luck next time...");
	}
	
	
	public static int getDoor()
	{	
		//Create Scanner
		Scanner keyboard = new Scanner(System.in);
		
		//Get user door
		System.out.println("There is a car behind doors 1, 2, or 3. Which one is it?");
		int userDoor = keyboard.nextInt();
		keyboard.nextLine(); 
		return userDoor;
	}
	
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
}