package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
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
    private Array<Body> temp;

    private float aspectratio;
    public gamescreen(){
        aspectratio=(float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        movement=new Vector2();
        Bg=new Texture(Gdx.files.internal("gamescreen/theme2.png"));
        ground=new Texture(Gdx.files.internal("gamescreen/terrain.png"));
        batch=new SpriteBatch();
        temp=new Array<Body>();
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
        world=new World(new Vector2(0,-25f),true);
        box2DDebugRenderer=new Box2DDebugRenderer();
        BodyDef tankdef=new BodyDef();
        FixtureDef fixtureDef=new FixtureDef();
        camera=new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tankdef.type= BodyDef.BodyType.StaticBody;
        tankdef.position.set(0,0);
        ChainShape shape=new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(-1000,338),new Vector2(-740,338),new Vector2(-562,-10),new Vector2(-290,-10),new Vector2(-150,108),new Vector2(-60,108),new Vector2(7,0),new Vector2(260,0),new Vector2(520,228),new Vector2(595,228),new Vector2(745,-63),new Vector2(1000,-63)});
        fixtureDef.shape=shape;
        fixtureDef.friction=1;
        world.createBody(tankdef).createFixture(fixtureDef);
        shape.dispose();
        FixtureDef wheelfix=new FixtureDef();
        fixtureDef.density=10;
        fixtureDef.friction=0.4f;
        fixtureDef.restitution=0.0000001f;
        wheelfix.density=fixtureDef.density*1000;
        wheelfix.friction=1f;
        wheelfix.restitution=0.000001f;
        FixtureDef muzzledef=new FixtureDef();
        PolygonShape muzzleshape=new PolygonShape();
        muzzleshape.setAsBox(30,10);
        muzzledef.shape=muzzleshape;
        tank1=new Tank("Player1",world,fixtureDef,wheelfix,muzzledef,-430,0,90,60,0);
        tank2=new Tank("Player2",world,fixtureDef,wheelfix,muzzledef,130,10,90,60,1);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(Bg,-838,-460,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(ground,-838,-460,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        world.getBodies(temp);
        for(Body body : temp){
            if(body.getUserData()!=null && body.getUserData() instanceof Sprite){
                Sprite temp_sp=(Sprite) body.getUserData();
                temp_sp.setPosition(body.getPosition().x-temp_sp.getWidth()/2,body.getPosition().y- temp_sp.getHeight()/2);
                temp_sp.setRotation(body.getAngle()* MathUtils.radiansToDegrees);
                temp_sp.draw(batch);
            }
        }
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
        batch.dispose();
    }


    public Tank getTank1(){
        return tank1;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.W:
                tank1.yinc();
                break;
            case Input.Keys.S:
                tank1.ydec();
                break;
            case Input.Keys.Q:
                tank1.xinc();
                break;
            case Input.Keys.E:
                tank1.xdec();
                break;
            case Input.Keys.F:
                tank1.shoot(world,tank1.projectile());
                break;
            case Input.Keys.TAB:
                tank1.delete(world, tank1.projectile());
                break;
            case Input.Keys.D:
                tank1.getLeftaxis().enableMotor(true);
                tank1.getLeftaxis().setMotorSpeed(-999999999);
                tank1.getRightaxis().enableMotor(true);
                tank1.getRightaxis().setMotorSpeed(-999999999);
                break;
            case Input.Keys.A:
                tank1.getLeftaxis().enableMotor(true);
                tank1.getLeftaxis().setMotorSpeed(999999999);
                tank1.getRightaxis().enableMotor(true);
                tank1.getRightaxis().setMotorSpeed(999999999);
                break;
            case Input.Keys.RIGHT:
                tank2.getLeftaxis().enableMotor(true);
                tank2.getLeftaxis().setMotorSpeed(-999999999);
                tank2.getRightaxis().enableMotor(true);
                tank2.getRightaxis().setMotorSpeed(-999999999);
                break;
            case Input.Keys.LEFT:
                tank2.getLeftaxis().enableMotor(true);
                tank2.getLeftaxis().setMotorSpeed(999999999);
                tank2.getRightaxis().enableMotor(true);
                tank2.getRightaxis().setMotorSpeed(999999999);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.D:
            case Input.Keys.A:
                tank1.getLeftaxis().enableMotor(false);
                break;
        }
        return true;
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
