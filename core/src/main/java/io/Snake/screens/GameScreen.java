package io.Snake.screens;

import io.Snake.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;

public class GameScreen extends TemplateScreen {

    private BitmapFont font;

    // Los estados del juego pueden crearse aquí o llegar desde el main junto con el parámetro Main game
    private int score = 0;

    public GameScreen(Main game) {
        super(game);
        font = new BitmapFont();
        font.getData().setScale(2f);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        // Actualizar la cámara
        actualizarCamara();

        // Limpiar la pantalla
        limpiarPantalla();

        // Actualizar el estado score
        score += 1;

        // Dibujar la pantalla de juego
        batch.begin();
        font.draw(batch, "Score: " + Integer.toString(score), Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 100);
        font.draw(batch, "Durante el juego", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 150);
        font.draw(batch, "Presione Esc para pausar", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 200);
        font.draw(batch, "Presione X para ir a la pantalla final", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 250);
        
        // Aquí va el código para dibujar el juego cuando no está en pausa
        // Por ejemplo, dibujar la serpiente, la comida, etc.
        batch.end();

        // Acción cuando se presiona la tecla Esc = cambia a la pantalla pause
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Lógica para pausar el juego
            // El this es porque debe enviarse el estado actual de esta pantalla, para poder reanudarse desde la ventana Pause
            game.setScreen(new PauseScreen(game, this));
            // NO SE HACE DISPOSE DE SUS ELEMENTOS PORQUE SE SEGUIRÁN UTILIZANDO
        }

        // Acción cuando se presiona la tecla X = cambia a la pantalla final
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            // Lógica para ir a la pantalla final
            // El estado score también puede crearse y actualizarse dentro de game para evitar pasar demasiados argumentos
            game.setScreen(new EndgameScreen(game, score));
            dispose();
        }
        
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}