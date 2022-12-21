package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Main extends Game implements InputProcessor {
	private Screen temp_screen;
	private static int i=0;
	private int flag=0;
	private Stage stage;
	private static InputHandler inputHandler;
	@Override
	public void create () {
		temp_screen=screenhandler.init(new screen("loading/tank.png"));
		Screen hey=screenhandler.init(new mainmenu("mainmenu/bg.png"));
		hey=screenhandler.init(new tankselect1("tanksel/bg.png"));
		hey=screenhandler.init(new tankselect("tanksel/bg.png"));
		hey=screenhandler.init(new gamescreen());
		stage =((screen)temp_screen).getStage();
		inputHandler=new InputHandler(this);
		setinput(inputHandler.getnormalmux((screen)temp_screen,((screen) temp_screen).getStage()));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		super.render();
		Iterator<Map.Entry<Integer, Screen>> it=screenhandler.getStages().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Screen> ele= it.next();
			if((int)ele.getKey()==i && i!=4){
				temp_screen=(screen)ele.getValue();
			}
		}
		stage =((screen)temp_screen).getStage();
		stage.act(Gdx.graphics.getDeltaTime());
		if(i==4 && flag==0){
			stage.clear();
			flag=1;
		}
		if(flag==1){
			screenhandler.getStages().put(3,screenhandler.init(new tankselect("tanksel/bg.png")));
			flag=0;
		}
		stage.draw();
	}
	
	@Override
	public void dispose () {
		Iterator<Map.Entry<Integer, Screen>> it=screenhandler.getStages().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Screen> ele= it.next();
			ele.getValue().dispose();
		}

	}
	public static void seti(int it){
		i=it;
	}
	public static InputHandler getinput(){
		return inputHandler;
	}
	public static void setinput(InputMultiplexer it){
		Gdx.input.setInputProcessor(it);
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
		Vector2 coord=stage.screenToStageCoordinates(new Vector2((float)screenX,(float)screenY));
		Image hita=(Image) stage.hit(coord.x, coord.y, false);
		if(hita!=null)
			if(hita.getClass()== Buttons.class)
				((Buttons) hita).action();

		System.out.println("HEY!!!");
		return true;
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
