package com.mygdx.game;

import com.badlogic.gdx.Screen;

import java.util.*;

public class screenhandler {
    private static int i=0;
    private static HashMap<Integer,Screen> stages=new HashMap<Integer, Screen>();;
    private screenhandler(){}
    public static Screen init(Screen x){
        if(!stages.containsKey(x)){
            stages.put(i++,x);
        }
        return x;
    }
    public static HashMap<Integer,Screen> getStages(){
        return stages;
    }
}
