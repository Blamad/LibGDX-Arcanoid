package com.eryk.arcanoid.controller;

import com.eryk.arcanoid.model.Platform;
import com.eryk.arcanoid.model.World;

public class PlatformController {
	
	private enum Direction {LEFT, RIGHT};
	
	private World world;
	private static Platform platform;
	private static int requestedX;
	
	private Direction DIR = null;
	
	private float DEFAULT_VEL = 400.0f;
	private float MAX_VEL = 550.0f;
	private final float ACCELERATION = 4f;
	
	public PlatformController(World world) {
		this.world = world;
		platform = world.getPlatform();
		platform.setVelocity(DEFAULT_VEL);
		requestedX = (int) platform.x;
	}
	
	public void update(float delta) {
		if((int)(platform.x + platform.width/2) != requestedX) {
			
			//sprawdzamy czy nie nast¹pi³a zmiana kierunku
			if(platform.x < requestedX && DIR == Direction.LEFT) { 
				DIR = Direction.RIGHT;
				platform.setVelocity(DEFAULT_VEL);
			} else if (platform.x > requestedX && DIR == Direction.RIGHT){ 
				DIR = Direction.LEFT;
				platform.setVelocity(DEFAULT_VEL);
			}
			
			platform.move(requestedX-platform.width/2, delta);
			
			if(platform.getVelocity() < MAX_VEL)
				platform.setVelocity(platform.getVelocity() + ACCELERATION);
		} else 
			platform.setVelocity(DEFAULT_VEL);
	}
	
	public void moveRequest(int x) {
		if(x > platform.width/2 && x < world.screenWidth - platform.width/2) {
			requestedX = x;
		} else if (x < platform.width/2)
			requestedX = (int)platform.width/2;
		else 
			requestedX = (int)(world.screenWidth - platform.width/2);
	}
}
