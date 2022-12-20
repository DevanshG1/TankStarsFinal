package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class background extends Actor {
    private final String path;

    public background(String s){
        path=s;
    }
    @Override
    public void draw(Batch batch,float x){
        batch.draw(new Texture(Gdx.files.internal(path)),0,0,100,50);
    }
}
