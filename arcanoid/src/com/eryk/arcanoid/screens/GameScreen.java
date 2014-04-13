package com.eryk.arcanoid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.eryk.arcanoid.controller.BallController;
import com.eryk.arcanoid.controller.BrickController;
import com.eryk.arcanoid.controller.PlatformController;
import com.eryk.arcanoid.model.World;
import com.eryk.arcanoid.view.Renderer;

public class GameScreen implements Screen, InputProcessor {

	private enum GameState {RUNNING, PAUSED, END};
	
	private GameState state;
	private World world;
	private Renderer worldRender;
	private PlatformController platformController;
	private BallController ballController;
	private BrickController brickController;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(state == GameState.RUNNING) {
			platformController.update(delta);
			ballController.update(delta);
			brickController.update(delta);
		}
		worldRender.render(delta);
		
	}

	@Override
	public void show() {
		this.world = new World();
		this.worldRender = new Renderer(world);
		this.platformController = new PlatformController(world);
		this.ballController = new BallController(world);
		this.brickController = new BrickController(world);
		Gdx.input.setInputProcessor(this);
		state = GameState.PAUSED;
		
	}

	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if(state == GameState.PAUSED)
			state = GameState.RUNNING;
		platformController.moveRequest(x);
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		platformController.moveRequest(x);
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
