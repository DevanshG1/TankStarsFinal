package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;

public class Tank implements InputProcessor {
    private Body body,left_wheel,right_wheel,muzzle;
    private float motorspeed=100000;
    private WheelJoint leftaxis,rightaxis;
    private WheelJoint muzzlejoint;
    private MouseJoint point;
    public Tank(World world, FixtureDef bodyfixture,FixtureDef wheelfix,FixtureDef muzzledef, float x, float y, float width,float height,int flag){
        BodyDef bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);
        PolygonShape bodyshape=new PolygonShape();
        bodyshape.set(new float[]{-width/2,-height/2,width/2,-height/2,width/2*0.8f,height/2,-width/2*0.8f,height/2*0.8f});
        bodyfixture.shape=bodyshape;
        body= world.createBody(bodyDef);
        body.createFixture(bodyfixture);
        CircleShape wheelshape=new CircleShape();
        wheelshape.setRadius(height/4);
        wheelfix.shape=wheelshape;
        muzzle= world.createBody(bodyDef);
        muzzle.createFixture(muzzledef);
        left_wheel= world.createBody(bodyDef);
        left_wheel.createFixture(wheelfix);
        right_wheel=world.createBody(bodyDef);
        right_wheel.createFixture(wheelfix);
        WheelJointDef rotdef=new WheelJointDef();
        rotdef.bodyA=body;
        rotdef.bodyB=muzzle;
        if(flag==0)
            rotdef.localAnchorA.set(width*2/3,height/2*0.9f);
        else
            rotdef.localAnchorA.set(-width*2/3,height/2*0.9f);
        muzzlejoint = (WheelJoint) world.createJoint(rotdef);
        WheelJointDef axisdef=new WheelJointDef();
        axisdef.bodyA=body;
        axisdef.bodyB=left_wheel;
        axisdef.localAnchorA.set(-width/2*.8f+wheelshape.getRadius(),-height/2);
        axisdef.frequencyHz=bodyfixture.density;
        leftaxis=(WheelJoint) world.createJoint(axisdef);
        axisdef.bodyB=right_wheel;
        axisdef.localAnchorA.x*=-1;
        rightaxis=(WheelJoint) world.createJoint(axisdef);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.D:
                leftaxis.enableMotor(true);
                leftaxis.setMotorSpeed(-100000);
                break;
            case Input.Keys.A:
                leftaxis.enableMotor(true);
                leftaxis.setMotorSpeed(100000);
                break;

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.D:
            case Input.Keys.A:
                leftaxis.enableMotor(false);
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
        System.out.println("Shoot!");
        return true;
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
