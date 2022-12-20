package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class tankselect extends screen implements InputProcessor {
    private Stage stage;
    private background bg_img;
    private String back_path;
    private float aspectratio;
    private Overlay line;
    private Overlay hscreen;
    private Buttons tomain;
    private Buttons play;
    private Buttons next_tank;
    private Buttons prev_tank;

    public tankselect(String s){
        back_path=s;
        aspectratio=(float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        create();
    }

    @Override
    public void create(){
        stage=new Stage(new FitViewport(100,90*aspectratio));
        bg_img=new background(back_path);
        line=new Overlay("tanksel/line.png",66.66f,0,90*aspectratio,0.2f);
        hscreen=new Overlay("tanksel/Homescreen.png",0,0,90*aspectratio,66.66f);
        tomain=new Buttons(new Texture(Gdx.files.internal("tanksel/arrow_left.png")),0,45f,3,2.5f);
        play=new Buttons(new Texture(Gdx.files.internal("tanksel/play.png")),76f,5f,16,8);
        next_tank=new Buttons(new Texture(Gdx.files.internal("tanksel/arrow_right.png")),90f,22f,3,2.5f);
        prev_tank=new Buttons(new Texture(Gdx.files.internal("tanksel/arrow_left.png")),75f,22f,3,2.5f);
        play.setName("play");
        line.setName("line");
        tomain.setName("tomain");
        stage.addActor(bg_img);
        stage.addActor(line);
        stage.addActor(hscreen);
        stage.addActor(play);
        stage.addActor(tomain);
        stage.addActor(next_tank);
        stage.addActor(prev_tank);
    }
    public background getBg_img(){
        return bg_img;
    }
    public Stage getStage(){
        return stage;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
