package com.oli.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oli.core.Screen_;
import com.oli.main.Game;

public class GameOver extends Screen_ {

    public GameOver(Game game) {
        super(game);
    }

    SpriteBatch batch;
    Texture gameOver;

    @Override
    public void show() {

        batch = new SpriteBatch();
        gameOver = new Texture("gui/consumed.png");

    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(gameOver, 0, 0);
        batch.end();

    }

}
