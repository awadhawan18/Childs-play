package com.childsplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;


/**
 * Created by Abhi on 19-Jan-18.
 */

public class Bubble extends Actor {
    Sprite bubble;
    float px,py;
    int speed,point;
    public Bubble(Texture texture, float px, float py, int speed,int point){
        bubble = new Sprite(texture);
        bubble.setSize(200,200);
        this.px = px;
        this.py = py;
        this.speed = speed;
        this.point = point;

    }

    public void updatePosition(){
        py+=speed;
        bubble.setPosition(px,py);
    }
    public void drawB(Batch batch){
        bubble.draw(batch);
    }

}
