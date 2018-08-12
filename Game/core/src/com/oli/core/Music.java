package com.oli.core;

import com.badlogic.gdx.Gdx;

public class Music {

    com.badlogic.gdx.audio.Music music;



    public void load() {

        music = Gdx.audio.newMusic(Gdx.files.internal("music/main.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);

    }

    public void play() {

        music.play();

    }

}
