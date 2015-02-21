package com.addymotion.Battleship;

import java.util.Random;

public class AIPlayer extends Player{
/*	private boolean lastHit;
	private int lastX;
	private int lastY;
	private int checkNum;
	*/
	private AIStrategy strategy;
	public void setAIStrategy(AIStrategy s){ strategy = s; }
	private World world;
	private int worldSize;
	
	@Override
	public void makeMove(World pWorld) {
		strategy.makeMove(pWorld);
	}
/* 		Random rnd = new Random();
		int x=-1;
		int y=-1;
		boolean valid =false;
		while(valid!=true){
			if(lastHit==true && checkNum<4){	//If the ai hits a ship, it searches the surrounding squares
			
				if(checkNum==0) { x = lastX-1; y = lastY; }
				if(checkNum==1) { x = lastX+1; y = lastY; }
				if(checkNum==2) { x = lastX; y = lastY-1; }
				if(checkNum==3) { x = lastX; y = lastY+1; }
				
			}
			else{								//otherwise, the ai picks a random square
				x = rnd.nextInt(pWorld.getSize());
				y = rnd.nextInt(pWorld.getSize());
			}
			if (x>=0 && x<pWorld.getSize() && y>=0 && y<pWorld.getSize() && pWorld.getCell(x, y).getState()!=2 
				&& pWorld.getCell(x, y).getState()!=3) valid = true; 	//check the square is a sensible choice
			else checkNum++;
			}
		ShipCell currentCell = pWorld.getCell(x,y);
		try {
			System.out.println("\nThe opponent is thinking...\n");
			Thread.sleep(1000);
		}
	    catch (InterruptedException e) {
	       e.printStackTrace();
	    }
		if (currentCell.getState()==1){
			currentCell.kill();
			pWorld.killCell();
			System.out.println("Direct hit to your "+currentCell.getShipName()+" at "+x+", "+y+"!");
			if (currentCell.getShipAlive()==false){
				System.out.println("Your "+currentCell.getShipName()+" was sunk!");
				lastHit = false;
			}
			else{
				lastHit = true;
				lastX=x;
				lastY=y;
				
			}
			checkNum=0;
		}
		else if (currentCell.getState()!=2) {currentCell.miss(); lastHit=false;
		}
		
	}
*/
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


