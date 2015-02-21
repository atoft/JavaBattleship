package com.addymotion.Battleship;
import java.util.Scanner;

/**
 * The main game class for Battleship. Handles initialisation of the game inside
 * the main method and contains methods for the main game loop.
 * 
 * @author alastair
 *
 */
public class Game {
	
	static Scanner main_input=new Scanner(System.in);
	static int n;
	static HumanPlayer player = new HumanPlayer();
	static AIPlayer ai = new AIPlayer();	

	
	/**
	 * Controls the main gameplay cycle. Given two player objects, allows them to take turns
	 * and ends the game when all of one player's ships are destroyed.
	 * @param player1 The first player - the human player in this implementation
	 * @param player2 The second player - the AI in this implementation
	 */
	public static void play(Player player1, Player player2){ 
		World pWorld = player1.getWorld();
		World eWorld = player2.getWorld();
		while(pWorld.getAliveTotal()!=0){
			
			try{
				player.makeMove(eWorld);
			}
			catch(Exception e){
				System.out.println("Invalid target!");
				continue;
			}
			eWorld.printWorld(false);
			if (eWorld.getAliveTotal()==0) break;
			ai.makeMove(pWorld);
			pWorld.printWorld(true);
		}
		if(eWorld.getAliveTotal()==0){
			System.out.println("Congratulations, you win!");
		}
		else {
			System.out.println("You are defeated!");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("\n\u272A  Welcome to Battleship! \u272A \n");
		boolean validsize = false;
		while(validsize!=true){
			System.out.print("Input a size for the world grid:  ");
			String inputN = main_input.next();
			try{
				n = Integer.parseInt(inputN);
				if (n>5 && n<17) validsize = true;
				else System.out.println("Not a valid size: size must be between 6 and 16.");
			}
			catch(Exception e){
				System.out.println("Not a valid size");
			}
		}
		
		Ship[] shipList1 = {new Ship("Carrier", 5),
				new Ship("Battleship", 4),
				new Ship("Cruiser", 3),
				new Ship("Submarine", 3),
				new Ship("Destroyer", 2),
				};
		Ship[] shipList2 = {new Ship("Carrier", 5),	//Two lists are needed so that each player has an independent
				new Ship("Battleship", 4),			//set of Ship objects
				new Ship("Cruiser", 3),
				new Ship("Submarine", 3),
				new Ship("Destroyer", 2),
				};
		player.initialise(n,shipList1);
		ai.initialise(n,shipList2);
		
		play(player,ai);


	}

}
