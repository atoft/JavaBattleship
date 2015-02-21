package com.addymotion.Battleship;

public class Ship {
	private String mShipName;
	private boolean mAlive;
	private int mSize;
	private int mHealth;
	
	public Ship(String name, int size){
		mShipName = name;
		mAlive = true;
		mSize = mHealth = size;
	}
	public Ship(){
		mAlive = false;
		mSize = 0;
	}
	
	public void damageShip(){
		--mHealth;
		if (mHealth == 0) mAlive = false;
	}
	public boolean isAlive(){
		return mAlive;
	}
	public String getName(){
		return mShipName;
	}
	public int getSize(){
		return mSize;
	}
}
