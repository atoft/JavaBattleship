package com.addymotion.Battleship;

import java.util.Random;

/**
 * Implementation of the AIStrategy. If the AI hits a ship, it will 
 * search around the surrounding cells until it gets another hit.
 * 
 * Currently used by the game.
 * 
 * @author alastair
 *
 */
public class AINormalStrategy extends AIStrategy{

	@Override
	public void makeMove(World pWorld) {
		Random rnd = new Random();
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

}
