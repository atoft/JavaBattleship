package com.addymotion.Battleship;

/**
 * Abstract class representing either a human or AI player.
 * 
 * The object must contain a reference to its World.
 * @author alastair
 *
 */
public abstract class Player {

	public abstract void makeMove(World world);
	public abstract void initialise(int size, Ship[] shipList);
	public abstract void addShip(Ship shipType) throws InvalidShipException;
	public abstract World getWorld();
}
