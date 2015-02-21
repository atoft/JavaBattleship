package com.addymotion.Battleship;

public abstract class AIStrategy {
	protected boolean lastHit;
	protected int lastX;
	protected int lastY;
	protected int checkNum;
	abstract void makeMove(World pWorld);

}
