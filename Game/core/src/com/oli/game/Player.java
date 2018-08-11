package com.oli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

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

    public Player(int x, int y, int[][] levelData) {

        texture = new Texture("player/player.png");
        this.x = x * 32;
        this.y = y * 32;
        this.levelData = levelData;
        velocity = new Vector2(0, 0);
        gravity = 1;
        terminalVelocity = 6;
        jumpVelocity = 13;
        moveSpeed = 5;

        rectangles = new ArrayList<>();


        for (int i = 0; i < levelData.length; i++) {

            for (int ii = 0; ii < levelData[i].length; ii++) {

                if (levelData[i][ii] == constants.wall) {

                    System.out.println("Added rect");

                    rectangles.add(new Rectangle(i * 32, ii * 32, 32, 32));

                }

            }

        }

    }

    boolean onGround() {

        Rectangle rectangle = new Rectangle(x, y + 32, 30, 1);

        for (Rectangle r : rectangles) {

            if (rectangle.overlaps(r) || r.overlaps(rectangle) || rectangle.contains(r) || r.contains(rectangle)) {

                System.out.println("on ground");
                y = 32 * (Math.round(y / 32)) + 1;
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

//            if (y % 32 != 0) {
//
//                System.out.println("Player in floor?");
////                Hacky way of rounding as you can't have decimals in integer
//                y = (y / 32) * 32;
//
//            }

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

//        if (!rightFree()) {
//
//            if (x % 32 != 0) {
//
//                x = (x / 32) * 32;
//
//            }
//
//        }

//        Render
        batch.draw(texture, x, y);

//        Find out if player is over halfway through tile or not
//        Tile number: x / 32
//        Number of tiles = leveldata.length()
        System.out.println(x / 32);

    }

}
