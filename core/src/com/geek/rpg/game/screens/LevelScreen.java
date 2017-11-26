package com.geek.rpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.geek.rpg.game.Assets;
import com.geek.rpg.game.GameSession;
import com.geek.rpg.game.ScreenManager;

/**
 * Created by jsavchenkova on 24.11.2017.
 */

public class LevelScreen implements Screen {
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private BitmapFont font36;
    private Stage stage;
    private Skin skin;
    private float time;

    public LevelScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        backgroundTexture = Assets.getInstance().getAssetManager().get("background.png", Texture.class);
        stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        Gdx.input.setInputProcessor(stage);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("zorque.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 96;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.shadowColor = Color.BLACK;
        parameter.shadowOffsetX = -3;
        parameter.shadowOffsetY = 3;
        parameter.color = Color.WHITE;
        parameter.size = 36;
        font36 = generator.generateFont(parameter);
        generator.dispose();
        skin = new Skin();

        skin.addRegions(Assets.getInstance().getAtlas());

        skin.add("font36", font36);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("menuBtn");
        textButtonStyle.font = font36;
        skin.add("tbs", textButtonStyle);

        Button btnNewLevel = new TextButton("NEW LEVEL", skin, "tbs");
        Button btnRestartLevel = new TextButton("RESTART LEVEL", skin, "tbs");
        btnNewLevel.setPosition(400, 300);
        btnRestartLevel.setPosition(400, 180);

        stage.addActor(btnNewLevel);
        stage.addActor(btnRestartLevel);

        btnNewLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
             //   GameSession.getInstance().startNewSession();
                GameSession.getInstance().setLevel(GameSession.getInstance().getLevel()+2);
                ScreenManager.getInstance().switchScreen(ScreenManager.ScreenType.BATTLE);

            }
        });
        btnRestartLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameSession.getInstance().loadSession();
                ScreenManager.getInstance().switchScreen(ScreenManager.ScreenType.BATTLE);
            }
        });
    }

    @Override
    public void render(float delta) {
        update(delta);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        batch.end();
        stage.draw();
    }

    public void update(float dt) {
        time += dt;
        stage.act(dt);
    }

    @Override
    public void resize(int width, int height) {
        ScreenManager.getInstance().onResize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
    }
}
