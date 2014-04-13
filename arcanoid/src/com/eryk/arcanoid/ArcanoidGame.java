package com.eryk.arcanoid;

import com.badlogic.gdx.Game;
import com.eryk.arcanoid.screens.GameScreen;


public class ArcanoidGame extends Game {

	@Override
	public void create() {
		this.setScreen(new GameScreen());
	}
	
}
