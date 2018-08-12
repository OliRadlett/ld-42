package com.oli.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Darkness {

    float duration;
    Texture darkness;
    int x;
    int y;
    float speed;
    Rectangle rectangle;

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Darkness(float duration /*seconds*/) {

        this.duration = duration;
        darkness = new Texture("tiles/darkness.png");

        x = 1280;
        y = 0;

//        Speed per tick
        speed = 1280 / (duration * 60);

        rectangle = new Rectangle(1280, 0, 1280, 800);

    }

    public void render(SpriteBatch batch) {

        x -= speed;
        rectangle.x = x;
        rectangle.y = y;

        batch.draw(darkness, x, y);

    }

}