package com.addymotion.Battleship;

public abstract class Player {

	public abstract void makeMove(World world);
	public abstract void initialise(int size, Ship[] shipList);
	public abstract void addShip(Ship shipType) throws InvalidShipException;
	public abstract World getWorld();
}
