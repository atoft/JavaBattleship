package com.addymotion.Battleship;

/**
 * A class representing a Battleship game grid. Can be used for either
 * human or AI players.
 * 
 * The grid is represented by a 2D array of ShipCell objects. Each ShipCell
 * has a reference to the Ship object of which it is a part.
 * @author alastair
 *
 */
public class World {
	private int cellsAlive;
	private ShipCell[][] worldArray;
	private int size;

	
	public int getAliveTotal() { return cellsAlive; }
	public void setAliveTotal(int total) {
		if (total>-1 && total <size*size) cellsAlive = total;
	}
	public void killCell() { cellsAlive--; }
	public int getSize(){ return size; }
	public ShipCell getCell(int x, int y){ return worldArray[x][y]; }
	public void setCell(int x, int y, Ship shipType){
		worldArray[x][y]= new ShipCell(shipType);
	}

	/**
	 * Prints the World's current state to the console. Cells in the grid are
	 * represented by special Unicode characters.
	 * 
	 * @param showShipLocations If false, ship cells which have not been hit are displayed as
	 * normal cells.
	 */
	public void printWorld(boolean showShipLocations) {
		System.out.print("# ");
		for (int x = 0; x<size;x++) System.out.print(x +" ");
		System.out.print("\n");
		for (int y = 0; y<size; y++) {
			System.out.print(y +" ");
			for (int x = 0; x<size; x++) {
				if (showShipLocations && worldArray[x][y].getState()==1) System.out.print("\u25CF"); //Cell with ship
				else if (worldArray[x][y].getState()==0 
						|| worldArray[x][y].getState()==1) System.out.print("\u25CB");			//Empty cell or ship cell
				else if (worldArray[x][y].getState()==2) System.out.print("\u25CE");			//Cell with a hit ship
				else System.out.print("\u25A1");												//Cell with a missed shot
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	
	
	public World(int n){
		worldArray = new ShipCell[n][n];
		cellsAlive = 0;
		size = n;
		worldArray = new ShipCell[n][n];
		for (int y = 0; y<size; y++) {;
			for (int x = 0; x<size; x++) {
				worldArray[x][y]=new ShipCell();
				}
			}
	}
	
}
