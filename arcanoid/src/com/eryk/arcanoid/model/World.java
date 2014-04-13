package com.eryk.arcanoid.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class World {
	
	public final float screenWidth = Gdx.graphics.getWidth();
	public final float screenHeight = Gdx.graphics.getHeight();
	private Vector2 begVel = new Vector2(150,150);
	
	private static Platform platform;
	private static Ball ball;
	private static List<Brick>bricks;
	
	private Pool<Brick> brickPool = new Pool<Brick>() {
		@Override
		protected Brick newObject() {
			return new Brick(0,0,(int)screenWidth/10,(int)screenHeight/10);
		}
	};
	
	public World() {
		int platWidth = (int) (screenWidth/10);
		System.out.println(screenHeight+"x"+screenWidth);
		platform = new Platform((screenWidth - platWidth)/2, 8, platWidth, 6);
		ball = new Ball(screenWidth/2,16,6,6, begVel);
		//cegie³ki
		bricks = new ArrayList<Brick>();
		for(int i = (int) (screenWidth/20); i < screenWidth; i+= (int)screenWidth/5) {
			bricks.add(brickPool.obtain().initPos(i, screenHeight/2));
			bricks.add(brickPool.obtain().initPos(i, (screenHeight/2)+(screenHeight/10)+2));
			bricks.add(brickPool.obtain().initPos(i, (screenHeight/2)+2*(screenHeight/10)+4));
		}
	}
	
	public Platform getPlatform() {
		return platform;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public List<Brick> getBricks() {
		return bricks;
	}
}
