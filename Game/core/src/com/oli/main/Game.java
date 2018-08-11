package com.oli.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.oli.core.ScreenManager;
import com.oli.menu.Menu;

public class Game extends com.badlogic.gdx.Game {

	Menu menu;
	ScreenManager screenManager;


	@Override
	public void create () {

		screenManager = new ScreenManager(this);
		menu = new Menu(this);
		screenManager.SetScreen(menu);

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.screen.render(Gdx.graphics.getDeltaTime());

	}

	@Override
	public void dispose () {

	}

}