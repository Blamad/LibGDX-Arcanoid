package com.eryk.arcanoid.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball extends Rectangle{
	
	private Vector2 velocity;
	private Vector2 begPos, begVel;

	public Ball(float x, float y, float width, float height, Vector2 vel) {
		super(x, y, width, height);
		begPos = new Vector2(x,y);
		velocity = begVel = vel.cpy();
	}
	
	public void move(float delta) {
		this.x += velocity.x*delta;
		this.y += velocity.y*delta;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public void resetVelocityY() {
		int x_pos = (int) velocity.x;
		velocity = begVel.cpy();
		
		//odpowiedni znak dla x
		if((x_pos > 0 && velocity.x < 0) || (x_pos < 0 && velocity.x > 0))
			velocity.x *= -1;
	}
	
	public void restart() {
		velocity = begVel.cpy();
		this.x = begPos.x;
		this.y = begPos.y;
	}
}
