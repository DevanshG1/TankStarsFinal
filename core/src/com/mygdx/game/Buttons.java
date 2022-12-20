package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.Game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Buttons extends Image {
    public Buttons(Texture texture,float x, float y, float width,float height){
        super(texture);
        setBounds(x,y,width,height);
    }

    public void action(){
        if(this.getName()=="exit"){
            Gdx.app.exit();
        }
        else if(this.getName()=="pvp"){
            ((Main) Gdx.app.getApplicationListener()).setScreen((Screen) screenhandler.getStages().get(2));
            Main.seti(2);
        }
        else if(this.getName()=="tomain"){
            ((Main) Gdx.app.getApplicationListener()).setScreen((Screen) screenhandler.getStages().get(1));
            Main.seti(1);
        }
        else if(this.getName()=="play"){
            ((Main) Gdx.app.getApplicationListener()).setScreen((Screen) screenhandler.getStages().get(3));
            Main.seti(3);
        }
    }
}
