package com.eryk.arcanoid.controller;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.eryk.arcanoid.model.Ball;
import com.eryk.arcanoid.model.Brick;
import com.eryk.arcanoid.model.Platform;
import com.eryk.arcanoid.model.World;

public class BallController {
	
	private float wWidth, wHeight;
	private Platform platform;
	private Ball ball;
	private Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/blip.wav"));
	private List<Brick> bricks;
	
	public BallController(World world) {
		wWidth = world.screenWidth;
		wHeight = world.screenHeight;
		ball = world.getBall();
		platform = world.getPlatform();
		bricks = world.getBricks();
	}
	
	public void update(float delta) {
		checkCollision(delta);
		ball.move(delta);
	}
	
	
	private void checkCollision(float delta) {
		ball.move(delta);
		//granice ekranu
		if(ball.x <= 0) {
			sound.play();
			ball.getVelocity().x *= -1;
			ball.x = 0;
		}
		else if(ball.x >= wWidth - ball.width) {
			sound.play();
			ball.getVelocity().x *= -1;
			ball.x = wWidth - ball.width;
		}
		else if(ball.y >= wHeight - ball.height) {
			sound.play();
			ball.getVelocity().y *= -1;
			ball.y = wHeight - ball.height;
		}
		
		//jezeli spadnie pod platforme 
		else if(ball.y < 0)
			ball.restart();
		//kolizja z platforma
		if(platform.overlaps(ball) && ball.y < platform.y) {
			//przesuwam pi³eczkê aby  by³a nad platform¹
			ball.setY(platform.y + platform.height + 1);
			//obliczam przyspieszenie pileczki wzgledem ruchu platformy, odbijam
			ball.resetVelocityY();
			ball.getVelocity().x -= (float) ((platform.getVelocity()/10));
			ball.getVelocity().y *= -1;
			
			sound.play();
		} else
		//jezeli trafi cegie³ke
		for(Brick it : bricks) {
			if(it.overlaps(ball)) {
				//trzeba j¹ odpowiednio odbiæ. Wiemy, ¿e pi³eczka znajduje siê w obrêbie cegie³ki
				//W³asna intersekcja!
				Rectangle rect = intersect(it, ball);
				if(rect.width < rect.height) { //jezeli dlugosc = 6.0 to zanurzyl sie calym bokiem
					ball.getVelocity().x*=-1;
					
					if(rect.x == it.x)
						ball.x = it.x - ball.width;
					else
						ball.x = it.x +it.width;
				} else {				//inaczej zanurzyl sie gora lub dolem
					ball.getVelocity().y*=-1;
					if(rect.y == it.y)
						ball.y = it.y - ball.height;
					else
						ball.y = it.y +it.height;
				}
				
				sound.play();
			}
		}
		
		ball.move(-delta);
	}
	
	private Rectangle intersect(Rectangle r1, Rectangle r2) {
		//Funkcja zwraca nowy protok¹t bêd¹cy dok³adnie czêœci¹ r1 i r2 która siê pokrywa.
		Rectangle intersection = new Rectangle();
		intersection.setX(Math.max(r1.x, r2.x));
		intersection.setY(Math.max(r1.y, r2.y));
		intersection.setWidth(Math.min(r1.x + r1.width, r2.x + r2.width) - intersection.x);
		intersection.setHeight(Math.min(r1.y + r1.height, r2.y + r2.height) - intersection.y);
		return intersection;
	}
}
