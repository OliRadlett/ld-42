package com.oli.core;

import com.badlogic.gdx.Screen;
import com.oli.main.Game;

public class ScreenManager {

    private Game game;

    public ScreenManager(Game game) {

        this.game = game;

    }

    public void SetScreen(Screen_ screen) {

        Screen CurrentScreen = game.getScreen();

        game.setScreen(screen);

        if (CurrentScreen != null) {

            CurrentScreen.dispose();

        }

    }

}
