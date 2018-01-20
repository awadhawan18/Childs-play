package com.childsplay;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import sun.rmi.runtime.Log;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor{
	private SpriteBatch batch;
	private TextureAtlas bubbleAtlas;
	private Texture greenBubbleTexture,blueBubbleTexture,redBubbleTexture;
	private BitmapFont font;
	private Animation<TextureRegion> animation;
	private float screenWidth, screenHeight;
	private ArrayList<Bubble> Bubbles;
	private Timer timer;
	private long timeLeft,startTime;
	private int score;

	@Override
	public void create () {
		batch = new SpriteBatch();
		greenBubbleTexture = new Texture("greenBubble0.png");
		blueBubbleTexture = new Texture("blue_bubble0.png");
        redBubbleTexture = new Texture("red_bubble0.png");
//		bubbleAtlas = new TextureAtlas(Gdx.files.internal("green_bubble.atlas"));
//		animation = new Animation<TextureRegion>(1/10f,bubbleAtlas.getRegions());
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();

		startTime = System.currentTimeMillis();

		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().scale(2);

		Bubbles = new ArrayList<Bubble>();
		timer = new Timer();
        score = 0;

        //Generates bubbles at regular intervals
        TimerTask generateGreen = new TimerTask() {
            @Override
            public void run() {
                Bubble bubble= new Bubble(greenBubbleTexture, (float)Math.random()*(screenWidth-200),
                        -100-(float)Math.random()*100,3,5);
                Bubbles.add(bubble);
            }
        };
        TimerTask generateBlue = new TimerTask() {
            @Override
            public void run() {
                Bubble bubble= new Bubble(blueBubbleTexture, (float)Math.random()*(screenWidth-200),
                        -100-(float)Math.random()*100,5,10);
                Bubbles.add(bubble);
            }
        };
        TimerTask generateRed = new TimerTask() {
            @Override
            public void run() {
                Bubble bubble= new Bubble(redBubbleTexture, (float)Math.random()*(screenWidth-200),
                        -100-(float)Math.random()*100,7,15);
                Bubbles.add(bubble);
            }
        };
		timer.scheduleAtFixedRate(generateGreen,2000,2000);
        timer.scheduleAtFixedRate(generateBlue,4000,4000);
        timer.scheduleAtFixedRate(generateRed,6000,6000);




		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void dispose () {
		batch.dispose();
		greenBubbleTexture.dispose();
		redBubbleTexture.dispose();
		blueBubbleTexture.dispose();
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
        //render each bubble and removes them
        font.draw(batch,"Score: "+String.valueOf(score),50,screenHeight-100);
        for(Bubble i:new ArrayList<Bubble>(Bubbles)){
            i.updatePosition();
            i.drawB(batch);
            if(i.py>screenHeight-100){
                Bubbles.remove(i);
            }

        }



		batch.end();
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
        for(Bubble i:new ArrayList<Bubble>(Bubbles)){
            if(screenX>=i.px && screenX<=i.px+200 && screenY>=i.py && screenX<=i.py+200){
                score+=i.point;
                Bubbles.remove(i);
            }
        }
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
    public boolean scrolled(int amount) {
        return false;
    }
}
