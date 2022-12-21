package com.mygdx.game;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputHandler{
    private Main m;

    public InputHandler(Main main){
        this.m=main;
    }
    public InputMultiplexer getnormalmux(screen s,Stage stage){
        return new InputMultiplexer(stage,s,m);
    }
    public InputMultiplexer addinmux( screen s,Stage stage,InputProcessor i){
        return new InputMultiplexer(i,stage,s,m);
    }
    public Main getM(){
        return m;
    }
}
