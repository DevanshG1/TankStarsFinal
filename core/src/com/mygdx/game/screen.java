package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;

import java.io.Serializable;

public class screen extends ApplicationAdapter implements Screen,InputProcessor, Serializable {
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
    public void Serialize(){
        
    }
    @Override
    public void create(){
        stage=new Stage(new FitViewport(100,90*aspectratio));
        bg_img=new background(back_path);
        bg_img2=new Buttons(new Texture(Gdx.files.internal(back_path)),0,0,100,90*aspectratio);
        bg_img2.setName("intro");
        stage.addActor(bg_img2);
        stage.addActor(bg_img);
        announce();
        bg_imgnotnull(bg_img);
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
public void announce(){
    System.out.println("Checking to see if the Background image is null");
}
public void bg_imgnotnull(background bg_img){
        try{
            assert(bg_img!=null);
        }
        catch (AssertionError e){
            System.out.println("Error! Bg Image is null");
            e.printStackTrace();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
