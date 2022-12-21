package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;

public class screen extends ApplicationAdapter implements InputProcessor,Screen {
    private Stage stage;
    private background bg_img;
    private Buttons bg_img2;
    private String back_path;
    private float aspectratio;
    public screen(String s){
        back_path=s;
        aspectratio=(float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        create();
    }

    public screen() {
    }

    @Override
    public void create(){
        stage=new Stage(new FitViewport(100,90*aspectratio));
        bg_img=new background(back_path);
        bg_img2=new Buttons(new Texture(Gdx.files.internal(back_path)),0,0,100,90*aspectratio);
        bg_img2.setName("intro");
        stage.addActor(bg_img2);
        stage.addActor(bg_img);
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

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }
}
