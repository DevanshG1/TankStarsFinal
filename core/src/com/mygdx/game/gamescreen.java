package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private Tank tank1;
    private Tank tank2;
    private SpriteBatch batch;
    private Texture Bg;
    private Texture ground;

    private float aspectratio;
    public gamescreen(){
        aspectratio=(float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        movement=new Vector2();
        Bg=new Texture(Gdx.files.internal("gamescreen/theme2.png"));
        ground=new Texture(Gdx.files.internal("gamescreen/terrainFinal.png"));
        batch=new SpriteBatch();
        create();
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
        world=new World(new Vector2(0,-49f),true);
        box2DDebugRenderer=new Box2DDebugRenderer();
        BodyDef tankdef=new BodyDef();
        FixtureDef fixtureDef=new FixtureDef();
        camera=new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tankdef.type= BodyDef.BodyType.StaticBody;
        tankdef.position.set(0,0);
        ChainShape shape=new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(-1000,338),new Vector2(-740,338),new Vector2(-562,-10),new Vector2(-290,-10),new Vector2(-230,108),new Vector2(-60,108),new Vector2(7,0),new Vector2(260,0),new Vector2(383,228),new Vector2(595,228),new Vector2(745,-63),new Vector2(1000,-63)});
        fixtureDef.shape=shape;
        fixtureDef.friction=1;
        world.createBody(tankdef).createFixture(fixtureDef);
        shape.dispose();
        FixtureDef wheelfix=new FixtureDef();
        fixtureDef.density=1000000;
        fixtureDef.friction=0.4f;
        fixtureDef.restitution=0.001f;
        wheelfix.density=fixtureDef.density*10;
        wheelfix.friction=1f;
        wheelfix.restitution=0.001f;
        tank1=new Tank(world,fixtureDef,wheelfix,-430,-5,90,60);
        tank2=new Tank(world,fixtureDef,wheelfix,130,5,90,60);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(Bg,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(ground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        box2DDebugRenderer.render(world,camera.combined);
        stage.draw();
        world.step(TIMESTEP, VELOCITYITERATIONS,POSITIONITERATION);
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
        return false;
    }

    public Tank getTank1(){
        return tank1;
    }
}
