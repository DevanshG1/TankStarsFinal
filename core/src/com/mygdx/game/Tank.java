package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;

public class Tank implements InputProcessor {
    class Weapon{
        private Body body;
        private Joint WeaponJoint;
        public Weapon(float x, float y){
            BodyDef bodyDef=new BodyDef();
            bodyDef.type= BodyDef.BodyType.DynamicBody;

        }
    }
    private Sprite boxSprite;
    private Body body,left_wheel,right_wheel,muzzle;
    private float motorspeed=100000;
    private WheelJoint leftaxis;
    private WheelJoint muzzlejoint;
    private String Name;

    public WheelJoint getLeftaxis() {
        return leftaxis;
    }

    private WheelJoint rightaxis;

    public WheelJoint getRightaxis() {
        return rightaxis;
    }
    public WheelJoint returnmuzzlejoint(){
        return muzzlejoint;
    }
    public Tank(String s,World world, FixtureDef bodyfixture,FixtureDef wheelfix,FixtureDef muzzledef, float x, float y, float width,float height,int flag){
        Name=s;
        boxSprite=new Sprite(new Texture(Gdx.files.internal("gamescreen/tank1.png")));
        BodyDef bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);
        PolygonShape bodyshape=new PolygonShape();
        bodyshape.set(new float[]{-width/2,-height/2,width/2,-height/2,width/2*0.8f,height/2,-width/2*0.8f,height/2*0.8f});
        bodyfixture.shape=bodyshape;
        body= world.createBody(bodyDef);
        body.createFixture(bodyfixture);
        PolygonShape muzzleshape=new PolygonShape();
        muzzleshape.setAsBox(30,5);
        muzzledef.shape=muzzleshape;
        muzzle= world.createBody(bodyDef);
        muzzle.createFixture(muzzledef);
        CircleShape wheelshape=new CircleShape();
        wheelshape.setRadius(height/4);
        wheelfix.shape=wheelshape;
        boxSprite.setSize(120,105);
        boxSprite.setOrigin(boxSprite.getWidth()/2,boxSprite.getHeight()/2);
        body.setUserData(boxSprite);
        left_wheel= world.createBody(bodyDef);
        left_wheel.createFixture(wheelfix);
        right_wheel=world.createBody(bodyDef);
        right_wheel.createFixture(wheelfix);
        WheelJointDef muzzelrot=new WheelJointDef();
        muzzelrot.bodyA=body;
        muzzelrot.bodyB=muzzle;
        muzzelrot.localAnchorA.set(width,height);
        muzzelrot.maxMotorTorque=99999999;
        muzzlejoint=(WheelJoint)world.createJoint(muzzelrot);
        WheelJointDef axisdef=new WheelJointDef();
        axisdef.bodyA=body;
        axisdef.bodyB=left_wheel;
        axisdef.localAxisA.set(Vector2.Y);
        axisdef.localAnchorA.set(-width/2*.8f+wheelshape.getRadius(),-height/2);
        axisdef.frequencyHz=bodyfixture.density;
        axisdef.maxMotorTorque=999999999;
        leftaxis=(WheelJoint) world.createJoint(axisdef);
        axisdef.bodyB=right_wheel;
        axisdef.localAnchorA.x*=-1;
        rightaxis=(WheelJoint) world.createJoint(axisdef);
    }
    public Body getBody(){
        return body;
    }
    public Body muzzle(){
        return muzzle;
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
        System.out.println("Shoot!");
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
