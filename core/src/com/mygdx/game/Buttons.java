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
import java.util.Objects;

public class Buttons extends Image {
    public Buttons(Texture texture,float x, float y, float width,float height){
        super(texture);
        setBounds(x,y,width,height);
    }

    public void action(){

        if(Objects.equals(this.getName(), "exit")){
            Gdx.app.exit();
        }
        else if(Objects.equals(this.getName(), "pvp")){
            ((Main) Gdx.app.getApplicationListener()).setScreen((Screen) screenhandler.getStages().get(2));
            Main.seti(2);
            Main.setinput(Main.getinput().getnormalmux((screen) screenhandler.getStages().get(2),((screen)screenhandler.getStages().get(2)).getStage()));
        }
        else if(Objects.equals(this.getName(), "tomain")){
            ((Main) Gdx.app.getApplicationListener()).setScreen((Screen) screenhandler.getStages().get(1));
            Main.seti(1);
            Main.setinput(Main.getinput().getnormalmux((screen) screenhandler.getStages().get(1),((screen)screenhandler.getStages().get(1)).getStage()));
        }
        else if(Objects.equals(this.getName(), "play")){
            ((Main) Gdx.app.getApplicationListener()).setScreen((Screen) screenhandler.getStages().get(3));
            Main.setinput(Main.getinput().addinmux((screen) screenhandler.getStages().get(3),((screen)screenhandler.getStages().get(3)).getStage(),((gamescreen)screenhandler.getStages().get(3)).getTank1()));
            Main.seti(3);
        }
        else if(Objects.equals(this.getName(), "intro")){
            ((Main) Gdx.app.getApplicationListener()).setScreen((Screen) screenhandler.getStages().get(1));
            Main.seti(1);
            Main.setinput(Main.getinput().getnormalmux((screen) screenhandler.getStages().get(1),((screen)screenhandler.getStages().get(1)).getStage()));
        }
    }
}
