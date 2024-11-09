package io.Snake.screens;

import io.Snake.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class HighscoreScreen extends TemplateScreen {

    private BitmapFont font;
    private Array<String> highScores;

    public HighscoreScreen(Main game) {
        super(game);
        font = new BitmapFont();
        font.getData().setScale(2f);
        batch = new SpriteBatch();
        highScores = gameModel.loadHighScores();
    }

    @Override
    public void render(float delta) {
        actualizarCamara();
        limpiarPantalla();

        batch.begin();
        font.draw(batch, "Highscores    Volver(ESC)", Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() - 50);
        int y = Gdx.graphics.getHeight() - 100;
        for (String score : highScores) {
            font.draw(batch, score, Gdx.graphics.getWidth() / 2 - 300, y);
            y -= 30;
        }
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}