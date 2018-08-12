package com.oli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.oli.main.Game;

import java.util.ArrayList;

public class Player {

    int x;
    int y;
    int gravity;
    int terminalVelocity;
    int jumpVelocity;
    int moveSpeed;
    int[][] levelData;
    Texture texture;
    Vector2 velocity;
    ArrayList<Rectangle> rectangles;
    Darkness darkness;
    Game game;

    public Player(int x, int y, int[][] levelData, Darkness darkness, Game game) {

        texture = new Texture("player/player.png");
        this.x = x * 32;
        this.y = y * 32;
        this.levelData = levelData;
        velocity = new Vector2(0, 0);
        gravity = 1;
        terminalVelocity = 6;
        jumpVelocity = 13;
        moveSpeed = 5;
        this.darkness = darkness;
        this.game = game;

        rectangles = new ArrayList<>();


        for (int i = 0; i < levelData.length; i++) {

            for (int ii = 0; ii < levelData[i].length; ii++) {

                if (levelData[i][ii] == constants.wall || levelData[i][ii] == constants.lava) {

                    rectangles.add(new Rectangle(i * 32, ii * 32, 32, 32));

                }

            }

        }

    }

    boolean onGround() {

        Rectangle rectangle = new Rectangle(x, y + 32, 30, 1);

        for (Rectangle r : rectangles) {

            if (rectangle.overlaps(r) || r.overlaps(rectangle) || rectangle.contains(r) || r.contains(rectangle)) {

                y = 32 * (Math.round(y / 32)) + 1;

                if (levelData[x / 32][(y + 32) / 32] == constants.lava) {

                    game.setScreen(new GameOver(game));

                }

                return true;

            }

        }

        return false;

    }

    boolean hitRoof() {

        Rectangle rectangle = new Rectangle(x, y, 30, 1);

        for (Rectangle r : rectangles) {

            if (rectangle.overlaps(r) || r.overlaps(rectangle) || rectangle.contains(r) || r.contains(rectangle)) {

                return true;

            }

        }

        return false;

    }

    boolean leftFree() {

        if (levelData[(x - moveSpeed) / 32][y / 32] == constants.empty) {

            return true;

        } else {

            return false;

        }

    }

    boolean rightFree() {

        if (levelData[(x + moveSpeed + 32) / 32][y / 32] == constants.empty) {

            return true;

        } else {

            return false;

        }

    }

    public void render(SpriteBatch batch, float delta) {

//        Gravity
        if (!onGround()) {

            if (velocity.y < terminalVelocity) {

                velocity.y += gravity;

            }

            if (hitRoof()) {

                velocity.y = 1;

            }

        } else {

            velocity.y = 0;

        }

//        Jumping
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            if (onGround()) {

                velocity.y = -jumpVelocity;

            }

        }

//        Movement
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

            if (leftFree()) {

                x -= moveSpeed;

            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            if (rightFree()) {

                x += moveSpeed;

            }

        }

//        Apply vertical velocity
        y += velocity.y;


//        Render
        batch.draw(texture, x, y);

        Rectangle rectangle = new Rectangle(x, y, 32, 32);

        if (rectangle.overlaps(darkness.getRectangle()) || darkness.getRectangle().overlaps(rectangle) || rectangle.contains(darkness.getRectangle()) || darkness.getRectangle().contains(rectangle)) {

            game.setScreen(new GameOver(game));

        }

    }

}
