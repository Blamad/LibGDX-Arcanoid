package com.eryk.arcanoid.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Brick extends Rectangle {
	
	private Color color;
	private int durablity;
	
	public Brick(float xPos, float yPos, float width, float height) {
		super(xPos, yPos, width, height);
		color = Color.MAGENTA;
		durablity = 3;
	}
	
	public Brick initPos(float x, float y) {
		setX(x);
		setY(y);
		return this;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int hit() {
		--durablity;
		if(durablity == 2)
			color = Color.RED;
		if(durablity == 1)
			color = Color.GRAY;
		
		return durablity;
	}
	
}
