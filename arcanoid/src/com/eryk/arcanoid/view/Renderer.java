package com.eryk.arcanoid.view;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.eryk.arcanoid.model.Ball;
import com.eryk.arcanoid.model.Brick;
import com.eryk.arcanoid.model.Platform;
import com.eryk.arcanoid.model.World;

public class Renderer {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture backgroundTexture;
	private TextureRegion backgroundRegion;
	
	private static Platform platform;
	private static Ball ball;
	private static List<Brick>bricks;
	private static ShapeRenderer renderer;

	public Renderer(World world) {
		this.camera = new OrthographicCamera(world.screenWidth, world.screenHeight);
		this.camera.position.set((world.screenWidth/2), (world.screenHeight/2), 0);
		this.camera.update();
		
		renderer = new ShapeRenderer();
		batch = new SpriteBatch();
		
		platform = world.getPlatform();
		ball = world.getBall();
		bricks = world.getBricks();
		
		loadTextures();
	}
	
	public void render(float delta) {	
		
		renderer.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(backgroundRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		renderer.begin(ShapeType.FilledRectangle);
		renderer.setColor(new Color(Color.WHITE));
		renderer.filledRect(platform.x,platform.y,platform.width,platform.height);
		renderBall();
		renderBricks();
		renderer.end();
	}
	
	private void renderBall() {
		renderer.setColor(new Color(Color.ORANGE));
		renderer.filledRect(ball.x,ball.y,ball.width,ball.height);
	}
	
	private void renderBricks() {
		for(Brick it : bricks) {
			renderer.setColor(it.getColor());
			renderer.filledRect(it.x, it.y, it.width, it.height);
		}
	}
	
	private void loadTextures() {
		Texture.setEnforcePotImages(false);
		backgroundTexture = new Texture("data/background1.png");
		backgroundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		backgroundRegion = new TextureRegion(backgroundTexture, 0, 0, 600, 375);
	}
}