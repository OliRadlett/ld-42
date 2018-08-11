package com.oli.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.oli.main.Game;

public class DesktopLauncher {

	private int width;
	private int height;
	private String title;
	private boolean fullscreen;
	private boolean resizeable;
	private int refreshRate;

	public DesktopLauncher() {

		width = 1280;
		height = 800;
		title = "LD-42 entry, By Oli";
		fullscreen = false;
		resizeable = false;
		refreshRate = 60;

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = width;
		config.height = height;
		config.title = title;
		config.fullscreen = fullscreen;
		config.resizable = resizeable;
		config.backgroundFPS = -1;
		config.foregroundFPS = refreshRate;

		new LwjglApplication(new Game(), config);

	}

	public static void main (String[] arg) {

		new DesktopLauncher();

	}

}