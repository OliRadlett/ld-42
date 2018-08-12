package com.oli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.oli.core.LevelLoader;
import com.oli.core.Screen_;
import com.oli.main.Game;

public abstract class Level extends Screen_ {

    int[][] levelData;
    int[] playerStart;
    LevelLoader levelLoader;
    SpriteBatch batch;
    SpriteBatch guibatch;
    OrthographicCamera camera;
    Vector3 mPos;
    Texture wall;
    Texture lava;
    Player player;
    Darkness darkness;
    Texture outrun;
    float duration;
    String name;

    public Level(Game game, String levelName, float duration) {

        super(game);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();
        guibatch = new SpriteBatch();

        wall = new Texture("tiles/wall.png");
        lava = new Texture("tiles/lava.png");

        levelLoader = new LevelLoader();
        levelData = levelLoader.loadLevelFromPNG(levelName);

        playerStart = new int[2];

        for (int x = 0; x < levelData.length; x++) {

            for (int y = 0; y < levelData[0].length; y++) {

                if (levelData[x][y] == constants.player) {

                    playerStart[0] = x;
                    playerStart[1] = y;
                    break;

                }

            }

        }

        this.duration = duration;
        this.name = levelName;

        outrun = new Texture("gui/outrun.png");

        darkness = new Darkness(duration);
        player = new Player(playerStart[0], playerStart[1], levelData, darkness, this);

    }

    float getDuration() {

        return duration;

    }

    String getName() {

        return name;

    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        for (int x = 0; x < levelData.length; x++) {

            for (int y = 0; y < levelData[x].length; y++) {

                switch (levelData[x][y]) {

                    case constants.wall:
                        batch.draw(wall, x * 32, y * 32);
                        break;

                    case constants.lava:
                        batch.draw(lava, x * 32, y * 32);
                        break;

                }

            }

        }

        player.render(batch, delta);

        darkness.render(batch);

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);

        guibatch.begin();
        guibatch.draw(outrun, 0, -30);
        guibatch.end();

    }

}
