package com.eryk.arcanoid.controller;

import java.util.ArrayList;
import java.util.List;

import com.eryk.arcanoid.model.Ball;
import com.eryk.arcanoid.model.Brick;
import com.eryk.arcanoid.model.World;

public class BrickController {
	
	private List<Brick> bricks;
	private List<Brick> removeBricks;
	private Ball ball;
	
	public BrickController(World world) {
		ball = world.getBall();
		bricks = world.getBricks();
		removeBricks = new ArrayList<Brick>();
	}
	
	public void update(float delta) {
		checkCollision(delta);
		disposeBricks();
	}
	
	private void checkCollision(float delta) {
		//ball.move(delta);
		for(Brick it : bricks) {
			if(it.overlaps(ball))
				if(it.hit() == 0)
					removeBricks.add(it);
		}
		//ball.move(-delta);
	}
	
	private void disposeBricks() {
		for(Brick it : removeBricks) {
			bricks.remove(it);
		}
		removeBricks.clear();
	}
}