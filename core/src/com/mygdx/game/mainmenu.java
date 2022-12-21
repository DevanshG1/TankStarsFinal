package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Objects;

public class mainmenu extends screen implements InputProcessor {
    private Stage stage;
    private background bg_img;
    private Overlay settings;
    private String set_path;
    private String back_path;
    private float aspectratio;
    private Overlay hscreen;
    private Overlay logo;
    private Overlay line;
    private Buttons exit;
    private Buttons pvp;

    private Buttons pve;
    private Buttons saved;

    public mainmenu(String s) {
        back_path=s;
        aspectratio=(float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        set_path="mainmenu/setting.png";
        create();
    }


    @Override
    public void create(){
        stage=new Stage(new FitViewport(100,90*aspectratio));
        bg_img=new background(back_path);
        settings=new Overlay(set_path,0, 43f,7,7);
        line=new Overlay("mainmenu/line.png",66.66f,0,90*aspectratio,0.2f);
        hscreen=new Overlay("mainmenu/Homescreen.png",0,0,90*aspectratio,66.66f);
        logo=new Overlay("mainmenu/tankstars.png",19f,33f,15,30);
        exit=new Buttons(new Texture(Gdx.files.internal("mainmenu/exit.png")),75f,7f,16,8);
        pvp=new Buttons(new Texture(Gdx.files.internal("mainmenu/vsFriend.png")),75f,25f,16,8);
        saved=new Buttons(new Texture(Gdx.files.internal("mainmenu/savedGames.png")),75f,16f,16,8);
        line.setName("line");
        settings.setName("settings");
        bg_img.setName("Bg_img");
        hscreen.setName("homescreen");
        logo.setName("logo");
        exit.setName("exit");
        pvp.setName("pvp");
        saved.setName("saved");
        stage.addActor(bg_img);
        stage.addActor(hscreen);
        stage.addActor(logo);
        stage.addActor(line);
        stage.addActor(exit);
        stage.addActor(pvp);
        stage.addActor(saved);
        stage.addActor(settings);
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
    public void dispose(){
        stage.dispose();
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
