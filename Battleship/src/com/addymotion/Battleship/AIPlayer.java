package com.addymotion.Battleship;

import java.util.Random;

/**
 * A computer controlled player. Uses a Strategy pattern to allow for
 * different AI difficulties to be implemented.
 * @author alastair
 *
 */
public class AIPlayer extends Player{

	private AIStrategy strategy;
	public void setAIStrategy(AIStrategy s){ strategy = s; }
	private World world;
	private int worldSize;
	
	@Override
	public void makeMove(World pWorld) {
		strategy.makeMove(pWorld);
	}

	@Override
	public void initialise(int size, Ship[] shipList) {
		world =new World(size);
		worldSize = size;
		setAIStrategy(new AINormalStrategy()); //This may be changed to implement other AI behaviours
		
		int numShips = shipList.length; 
		world.setAliveTotal(17);		//TODO: Calculate this value
		for(int i=0;i<numShips;i++){
			addShip(shipList[i]);
		}
		
	}

	@Override
	public void addShip(Ship shipType){
		int valid =0;
		int x = 0;
		int y = 0;
		int z = 0;
		Random rnd = new Random();
		z = rnd.nextInt(2);	
		if(z==1){											//Placing a left-right ship
			while (valid !=shipType.getSize()){			
				x = rnd.nextInt(worldSize-shipType.getSize()+1);
				y = rnd.nextInt(worldSize);

				for(int i=0; i<shipType.getSize(); i++){			//This loop checks if the space is already occupied
					if (world.getCell(x+i, y).isOccupied()) valid = 0;
					else valid++;
				}
			}
			for(int i=0; i<shipType.getSize(); i++){				//This loop updates the cells
				world.setCell(x+i, y, shipType);
			}
		}
		else{												//Placing a north-south ship
			while (valid !=shipType.getSize()){			
				x = rnd.nextInt(worldSize);
				y = rnd.nextInt(worldSize-shipType.getSize()+1);

				for(int i=0; i<shipType.getSize(); i++){			//This loop checks if the space is already occupied
					if (world.getCell(x, y+i).isOccupied()) valid = 0;
					else valid++;
				}
			}
			for(int i=0; i<shipType.getSize(); i++){				//This loop updates the cells	
				world.setCell(x, y+i, shipType);
			}
		}
	}

	@Override
	public World getWorld() {
		return world;
	}
}


