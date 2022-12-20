package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class gamescreen extends screen implements Screen, InputProcessor {
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Vector2 movement;
    private Stage stage;
    private final float TIMESTEP=1f/60f;
    private final int VELOCITYITERATIONS=8,POSITIONITERATION=3;
    private Body tire;

    private background bg_img;
    private String back_path;
    private float aspectratio;
    public gamescreen(){
        aspectratio=(float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        movement=new Vector2();
        create();
    }
    public background getBg_img(){
        return bg_img;
    }
    public Stage getStage(){
        return stage;
    }
    public void showhelper(){
        show();
    }
    @Override
    public void create(){
        stage=new Stage(new ScreenViewport());
    }
    @Override
    public void show() {

        world=new World(new Vector2(0,-9.8f),true);
        box2DDebugRenderer=new Box2DDebugRenderer();
        BodyDef tankdef=new BodyDef();
        FixtureDef fixtureDef=new FixtureDef();
        camera=new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tankdef.type= BodyDef.BodyType.StaticBody;
        tankdef.position.set(0,0);
        ChainShape shape=new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(-500,0),new Vector2(500,0)});
        fixtureDef.shape=shape;
        world.createBody(tankdef).createFixture(fixtureDef);
        tankdef.type= BodyDef.BodyType.DynamicBody;
        tankdef.position.set(5,251);
        PolygonShape box=new PolygonShape();
        box.setAsBox(6f,10);
        fixtureDef.shape=box;
        fixtureDef.density=2.5f;
        fixtureDef.friction=0.25f;
        fixtureDef.restitution=0.1f;
        tire=world.createBody(tankdef);
        tire.createFixture(fixtureDef);
        box.dispose();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        box2DDebugRenderer.render(world,camera.combined);
        world.step(TIMESTEP, VELOCITYITERATIONS,POSITIONITERATION);
        tire.applyForceToCenter(movement,true);
        stage.draw();
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
        dispose();
    }
    @Override
    public void dispose(){
        world.dispose();
        box2DDebugRenderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            movement.x=500;
        }
        return true;
    }
}
