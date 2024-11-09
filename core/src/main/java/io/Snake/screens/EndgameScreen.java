package io.Snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.Snake.main.Main;

public class EndgameScreen extends TemplateScreen{

    private BitmapFont font;
    private int finalScore;

    public EndgameScreen(Main game) {
        super(game);
        font = new BitmapFont();
        font.getData().setScale(2f);
        batch = new SpriteBatch();
        finalScore = gameModel.getScore();
    }

    @Override
    public void render(float delta) {
        // Actualizar la cámara
        actualizarCamara();

        // Limpiar la pantalla
        limpiarPantalla();

        // Dibujar el endgame
        batch.begin();
        font.draw(batch, "Fin del juego", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 150);
        font.draw(batch, "Score obtenido: " + Integer.toString(finalScore), Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 100);
        batch.end();

        // Acción cuando se presiona la pantalla = Vuelve a comenzar del menu principal
        if (Gdx.input.isTouched()) {
            // Subir el puntaje a la base de datos
            game.getModel().uploadScoreToDatabase();
            dispose();
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
    
}
