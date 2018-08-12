package com.oli.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.oli.core.Button_;
import com.oli.core.Music;
import com.oli.core.Screen_;
import com.oli.game.Level;
import com.oli.game.constants;
import com.oli.main.Game;

public class Menu extends Screen_ {

    SpriteBatch batch;
    Button_ exitButton;
    Button_ playButton;
    OrthographicCamera camera;
    Vector3 mPos;
    Music music;

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();

        exitButton = new Button_((Gdx.graphics.getWidth() / 2) - 135, 64, "exit");
        playButton = new Button_((Gdx.graphics.getWidth() / 2) - 135, 256, "play");

        exitButton.onClick(this::Exit);
        playButton.onClick(this::Play);

        music = new Music();
        music.load();
        music.play();
    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        exitButton.render(batch);
        playButton.render(batch);

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);
        exitButton.logic(mPos);
        playButton.logic(mPos);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void Exit() {

        System.out.println("Bye bye!");
        Gdx.app.exit();
        this.dispose();

    }

    public void Play() {

        getGame().setScreen(new Level(getGame(), "level0", 21.25f, constants.left) {});
        this.dispose();

    }

}