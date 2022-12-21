package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class tankselect1 extends screen implements InputProcessor {
    public static void shower(int flag){
        tank_player1=flag;
    }
    private static Stage stage;
    private static int tank_player1;
    private Overlay[] Maintank;
    private Buttons Tank1;
    private Buttons Tank2;
    private Buttons Tank3;
    private background bg_img;
    private String back_path;
    private float aspectratio;
    private Overlay line;
    private Overlay hscreen;
    private Buttons tomain;
    private Buttons play;

    public tankselect1(String s){
        back_path=s;
        tank_player1=0;
        Maintank= new Overlay[]{new Overlay("tanksel/tank1.png", 25, 5, 30, 30), new Overlay("tanksel/tank2.png", 25, 5, 30, 30), new Overlay("tanksel/tank3.png", 25f, 10.5f, 20, 30)};
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
        Tank1=new Buttons(new Texture(Gdx.files.internal("tanksel/Tank_circle1.png")),75f,17f,20,10);
        Tank2=new Buttons(new Texture(Gdx.files.internal("tanksel/Tank_circle2.png")),75f,25f,20,10);
        Tank3=new Buttons(new Texture(Gdx.files.internal("tanksel/Tank_circle3.png")),75f,33f,20,10);
        Tank1.setName("Tank11");
        Tank2.setName("Tank21");
        Tank3.setName("Tank31");
        play.setName("next");
        line.setName("line");
        tomain.setName("tomain1");
        stage.addActor(bg_img);
        stage.addActor(line);
        stage.addActor(hscreen);
        stage.addActor(play);
        stage.addActor(Tank1);
        stage.addActor(Tank2);
        stage.addActor(Tank3);
       stage.addActor(Maintank[0]);
        stage.addActor(tomain);
    }
    public background getBg_img(){
        return bg_img;
    }
    public Stage getStage(){
        return stage;
    }
    public static int type(){
        return tank_player1;
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
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
