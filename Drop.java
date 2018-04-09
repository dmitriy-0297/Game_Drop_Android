package com.gdk1743.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drop extends Game {

    SpriteBatch batch;
    BitmapFont font;
    int stope = 0;

    @Override
    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    public void stoped(){
        this.setScreen(new GameOverScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        screen.dispose();
        font.dispose();
    }
}
