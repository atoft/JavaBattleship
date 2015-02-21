package com.addymotion.Battleship;

/**
 * Abstract class to determine AI behaviour.
 * 
 * @author alastair
 *
 */
public abstract class AIStrategy {
	protected boolean lastHit;
	protected int lastX;
	protected int lastY;
	protected int checkNum;
	abstract void makeMove(World pWorld);

}
