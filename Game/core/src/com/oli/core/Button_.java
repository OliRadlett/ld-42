package com.oli.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class Button_ {

    int x;
    int y;
    int width;
    int height;
    Texture texture;
    Texture textureNoHover;
    Texture textureHover;
    Rectangle bounds;
    Runnable callback;
    String name;

    public Button_(int x, int y, String name) {

        this.x = x;
        this.y = y;
        this.textureNoHover = new Texture("buttons/" + name + ".png");
//        this.textureHover = new Texture("buttons/" + name + "Hover.png");
        this.texture = textureNoHover;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
        this.name = name;

    }

    public void onClick(Runnable onClick) {

        this.callback = onClick;

    }

    public void render(SpriteBatch batch) {

        batch.draw(texture, x, y);

    }

    public void logic(Vector3 mPos) {

        if (this.bounds.contains(mPos.x, mPos.y)) {

            System.out.println("Mouse over " + name);

//            this.texture = textureHover;

            if (Gdx.input.justTouched()) {

                this.callback.run();

            }

        } else {

            this.texture = textureNoHover;

        }

    }

}
