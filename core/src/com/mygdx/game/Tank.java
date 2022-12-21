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
    private Sprite boxSprite;
    private float plax;
    private float play;
    private int flag;
    private Body body,left_wheel,right_wheel,muzzle;
    private float motorspeed=100000;
    private WeldJointDef projectilejointdef;
    private WheelJoint leftaxis;
    private WheelJoint muzzlejoint;
    private String Name;
    private int xco;
    private int yco;
    private WeldJoint projectilejoint;
    private Body projectile;
    private float width;
    private float height;

    public WheelJoint getLeftaxis() {
        return leftaxis;
    }

    private WheelJoint rightaxis;
    private FixtureDef projfix;
    private Sprite proj_sprite;

    public WheelJoint getRightaxis() {
        return rightaxis;
    }
    public WheelJoint returnmuzzlejoint(){
        return muzzlejoint;
    }
    public Tank(String s,World world, FixtureDef bodyfixture,FixtureDef wheelfix,FixtureDef muzzledef, float x, float y, float width,float height,int flag){
        this.flag=flag;
        plax=x;
        play=y;
        Name=s;
        xco=0;
        yco=0;
        this.width=width;
        this.height=height;
        proj_sprite=new Sprite(new Texture(Gdx.files.internal("gamescreen/missile.png")));
        if(flag==0){
            if(tankselect1.type()==0){
                boxSprite=new Sprite(new Texture(Gdx.files.internal("gamescreen/tank1.png")));
            }
            else if(tankselect1.type()==1){
                boxSprite=new Sprite(new Texture(Gdx.files.internal("gamescreen/tank2.png")));
            }
            else{
                boxSprite=new Sprite(new Texture(Gdx.files.internal("gamescreen/tank3.png")));
            }
        }else{
            if(tankselect.type()==0){
                boxSprite=new Sprite(new Texture(Gdx.files.internal("gamescreen/tank1f.png")));
            }
            else if(tankselect.type()==1){
                boxSprite=new Sprite(new Texture(Gdx.files.internal("gamescreen/tank2f.png")));
            }
            else{
                boxSprite=new Sprite(new Texture(Gdx.files.internal("gamescreen/tank3f.png")));
            }
        }

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
        CircleShape projshape=new CircleShape();
        projshape.setRadius(height/8);
        boxSprite.setSize(120,105);
        boxSprite.setOrigin(boxSprite.getWidth()/2,boxSprite.getHeight()/2);
        proj_sprite.setSize(30,20);
        proj_sprite.setOrigin(proj_sprite.getWidth()/2,proj_sprite.getHeight()/2);
        body.setUserData(boxSprite);
        projectile= world.createBody(bodyDef);
        projectile.setUserData(proj_sprite);
        projfix=new FixtureDef();
        projfix.shape=projshape;
        projfix.density=3;
        projfix.restitution=0;
        projectile.createFixture(projfix);
        left_wheel= world.createBody(bodyDef);
        left_wheel.createFixture(wheelfix);
        right_wheel=world.createBody(bodyDef);
        right_wheel.createFixture(wheelfix);
        projectilejointdef=new WeldJointDef();
        projectilejointdef.bodyA=body;
        projectilejointdef.bodyB=projectile;
        if(flag==0){
            projectilejointdef.localAnchorA.set(width/2,height/2);
        }else{
            projectilejointdef.localAnchorA.set(-width/2,height/2);
        }
        projectilejoint=(WeldJoint)world.createJoint(projectilejointdef);
        WheelJointDef muzzelrot=new WheelJointDef();
        muzzelrot.bodyA=body;
        muzzelrot.bodyB=muzzle;
        muzzelrot.maxMotorTorque=99999999;
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
    public void shoot(World world,Body proj){
        world.destroyJoint(projectilejoint);
        if(flag==0){
            proj.applyForceToCenter(100000000*xco,1000000000*yco,true);
        }else{
            proj.applyForceToCenter(-100000000*xco,1000000000*yco,true);
        }
    }
    public void delete(World world,Body proj){
        world.destroyBody(proj);
        createprojectile(world);
    }
    public Body projectile(){
        return projectile;
    }
    public void createprojectile(World world){
        BodyDef bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(plax,play);
        projectile=world.createBody(bodyDef);
        projectile.setUserData(proj_sprite);
        projectile.createFixture(projfix);
        projectilejointdef.bodyA=body;
        projectilejointdef.bodyB=projectile;
        if(flag==0){
            projectilejointdef.localAnchorA.set(width/2,height/2);
        }else{
            projectilejointdef.localAnchorA.set(-width/2,height/2);
        }
        projectilejoint=(WeldJoint) world.createJoint(projectilejointdef);
    }
    public void xinc(){
        xco+=10;
    }
    public void xdec(){
        xco-=10;
    }
    public void yinc(){
        yco+=10;
    }
    public void ydec(){
        yco-=10;
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
