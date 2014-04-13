package com.eryk.arcanoid.model;

import com.badlogic.gdx.math.Rectangle;

public class Platform extends Rectangle {
	
	private float DEFAULT_VEL = 400.0f;
	private float VELOCITY = 0;
	private float MAX_VEL = 550.0f;
	
	public Platform(float posX, float posY, float width, float height) {
		super();
		this.set(posX, posY, width, height);
		
	}
	
	public void move(float pos, float delta) {
		//ruch w prawo
		if(pos > x) {
			x += delta*VELOCITY;
			if(pos < x)
				x = pos;
		} else {
			x -= delta*VELOCITY;
			if(pos > x)
				x = pos;
		}
	}
	
	public void setVelocity(float vel) {
		VELOCITY = vel;
	}
	
	public float getVelocity() {
		return VELOCITY;
	}
}
