package com.addymotion.Battleship;

import java.util.Random;
import java.util.Scanner;

/**
 * A human controlled player. Methods allow the player to place their ships 
 * either manually or at random, and to select cells on their turn, using
 * command line inputs.
 * 
 * @author alastair
 *
 */
public class HumanPlayer extends Player{

	private World world;
	private int worldSize;
	
	@Override
	public void makeMove(World eWorld) {
		Scanner main_input=new Scanner(System.in);
		
		System.out.print("Input target (x,y):  ");
		String inputCell = main_input.next();
		String[] inputCellArray = inputCell.split(",");
		int x =		Integer.parseInt(inputCellArray[0]);
		int y =		Integer.parseInt(inputCellArray[1]);
		ShipCell currentCell = eWorld.getCell(x,y);
		System.out.println("\n");
		if (currentCell.getState()==1){
			currentCell.kill();
			eWorld.killCell();
			System.out.println("Direct hit to enemy "+currentCell.getShipName()+" at "+x+", "+y+"!");
			if (currentCell.getShipAlive()==false){
				System.out.println(currentCell.getShipName()+" sunk!");
			}
		}
		else if (eWorld.getCell(x,y).getState()!=2) currentCell.miss();
	}
	
	@Override
	public void initialise(int size, Ship[] shipList) {		//Takes user input for position of every ship in ship list
		world =new World(size);
		worldSize = size;
		
		int numShips = shipList.length;
		world.setAliveTotal(17);	//TODO: calculate this value
		System.out.println("\nYou have the following ships in your fleet:");
			for (Ship i : shipList){
				System.out.println(i.getName()+": \tsize "+i.getSize());
			}
			System.out.println("\n\n");
			
			Scanner randomcheck=new Scanner(System.in);
			System.out.print("Would you like to place your ships? (y/n)\nIf not, they will be placed randomly:  ");
			boolean valid = false;
			boolean doRandom = false;
			while(valid!=true){
				String yn = randomcheck.next();
				if(yn.equalsIgnoreCase("y")){ valid = true; doRandom=false; }
				else if(yn.equalsIgnoreCase("n")){ valid = true; doRandom=true; }
				else System.out.println("Please enter either y (yes) or n (no).");
			}
			if (doRandom==true) {
				initRandom(size, shipList);
				System.out.println("Your randomly generated board:");
				world.printWorld(true);
			}
			else{
				for(int i=0;i<numShips;i++){
					System.out.println("\nYou have "+ (numShips-i) +" ships to place.");
					try{
						addShip(shipList[i]);
					}
					catch(InvalidShipException e) {
						System.out.println("\nInvalid ship position!");
						i--;
					}
					world.printWorld(true);
				}	
			}
		
	}
	public void initRandom(int size, Ship[] shipList){
		world =new World(size);
		worldSize = size;
		
		int numShips = shipList.length; 
		world.setAliveTotal(17);		//TODO: Calculate this value
		for(int i=0;i<numShips;i++){
			int valid =0;
			int x = 0;
			int y = 0;
			int z = 0;
			Ship shipType = shipList[i];
			Random rnd = new Random();
			z = rnd.nextInt(2);	
			if(z==1){											//Placing a left-right ship
				while (valid !=shipType.getSize()){			
					x = rnd.nextInt(worldSize-shipType.getSize()+1);
					y = rnd.nextInt(worldSize);

					for(int j=0; j<shipType.getSize(); j++){			//This loop checks if the space is already occupied
						if (world.getCell(x+j, y).isOccupied()) valid = 0;
						else valid++;
					}
				}
				for(int k=0; k<shipType.getSize(); k++){				//This loop updates the cells
					world.setCell(x+k, y, shipType);
				}
			}
			else{												//Placing a north-south ship
				while (valid !=shipType.getSize()){			
					x = rnd.nextInt(worldSize);
					y = rnd.nextInt(worldSize-shipType.getSize()+1);

					for(int l=0; l<shipType.getSize(); l++){			//This loop checks if the space is already occupied
						if (world.getCell(x, y+l).isOccupied()) valid = 0;
						else valid++;
					}
				}
				for(int m=0; m<shipType.getSize(); m++){				//This loop updates the cells	
					world.setCell(x, y+m, shipType);
				}
			}
		}
	}
	
	
	@Override
	public void addShip(Ship shipType) throws InvalidShipException{
		Scanner user_input=new Scanner(System.in);
		System.out.print("Input coordinates for your "+shipType.getName()
				+" of size "+shipType.getSize()+", and its orientation \nnorth(n) or west(w) in the form x,y,n:  ");
		String inputCell = user_input.next();
		
		String[] inputCellArray = inputCell.split(",");
		if (inputCellArray.length!=3) throw new InvalidShipException();
		int x =		Integer.parseInt(inputCellArray[0]);
		int y =		Integer.parseInt(inputCellArray[1]);
		String z = inputCellArray[2];
		
		if (z.equalsIgnoreCase("w")){ 											//Left-right ships
			if(x+shipType.getSize() >worldSize || y>worldSize-1) throw new InvalidShipException();
			for(int i=0; i<shipType.getSize(); i++){							//This loop checks if the space is already occupied
				if (world.getCell(x+i,y).isOccupied()) throw new InvalidShipException();
			}
			
			for(int i=0; i<shipType.getSize(); i++){							//This loop sets the ship cells
				world.setCell(x+i,y,shipType);
			}
		}
		else if (z.equalsIgnoreCase("n")){										//Up-down ships
			if(y+shipType.getSize() >worldSize || x>worldSize-1) throw new InvalidShipException();
			for(int i=0; i<shipType.getSize(); i++){							//This loop checks if the space is already occupied
				if (world.getCell(x, y+i).isOccupied()) throw new InvalidShipException();
			}
			
			for(int i=0; i<shipType.getSize(); i++){							//This loop sets the ship cells
				world.setCell(x, y+i, shipType);
			}				
		}
		else throw new InvalidShipException();
	}

	@Override
	public World getWorld() {
		return world;
	}
}
