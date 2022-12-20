package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Overlay extends Actor {
    private String path;
    private float bheight;
    private float bwidth;
    private float x;
    private float y;
    public Overlay(String s,float x,float y,float h,float w){
        path=s;
        bheight=h;
        bwidth=w;
        this.x=x;
        this.y=y;
    }
    @Override
    public void draw(Batch batch, float xi){
        batch.draw(new Texture(Gdx.files.internal(path)), x,y,bwidth,bheight);
    }
}
