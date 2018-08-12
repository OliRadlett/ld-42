package com.oli.game;

import com.badlogic.gdx.Gdx;
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
    int dir;

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Darkness(float duration /*seconds*/, int dir) {

        this.duration = duration;
        darkness = new Texture("tiles/darkness.png");
        this.dir = dir;

//        Speed per tick

        switch (dir) {

            case constants.left:
                speed = 1280 / (duration * 60);
                x = 1280;
                y = 0;
                break;

            case constants.right:
                speed = (1280 / (duration * 60)) * -1;
                x = 0;
                y = 0;
                break;

            case constants.up:
                speed = 1600 / (duration * 60);
                y = 800;
                x = 0;
                break;

            case constants.down:
                y = -1600;
                x = 0;
                speed = (1600 / (duration * 60)) * -1;
                break;

        }

        rectangle = new Rectangle(x, y, darkness.getWidth(), darkness.getHeight());

    }

    public void render(SpriteBatch batch) {

        if (dir == constants.left || dir == constants.right) {

            x -= speed;

        } else if (dir == constants.up || dir == constants.down) {

            y -= speed;

        }

        rectangle.x = x;
        rectangle.y = y;

        batch.draw(darkness, x, y);

    }

}