package com.addymotion.Battleship;

public class ShipCell {
	private int mState;		// 0= unoccupied cell, 1 = Cell with ship, 2 = Hit ship 3 = missed shot
	private Ship mShip;
	
	
	public ShipCell(){
		mState = 0;
	}
	public ShipCell(Ship ship){
		mShip = ship;
		mState = 1;
	}
	
	
	public int getState(){
		return mState;
	}
	public boolean getShipAlive(){
		if(mState!=0){
			return mShip.isAlive();
		}
		else return false;
	}
	public boolean isOccupied(){
		return mState==1;
	}
	public String getShipName(){
		if(mState!=0){
			return mShip.getName();
		}
		else return " ";
	}	

	public void kill(){
		mState=2;
		mShip.damageShip();
	}
	public void miss(){
		mState=3;
	}
}
