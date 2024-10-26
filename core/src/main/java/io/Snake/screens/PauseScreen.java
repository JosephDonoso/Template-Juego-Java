package io.Snake.screens;

import io.Snake.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;


public class PauseScreen extends TemplateScreen {

    private BitmapFont font;
    GameScreen gameScreen;

    public PauseScreen(Main game, GameScreen gameScreen) {
        super(game);
        this.gameScreen = gameScreen; 
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

        // Dibujar el menú de pausa
        batch.begin();
        font.draw(batch, "Juego en Pausa", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 50);
        batch.end();

        // Acción cuando se presiona la tecla Esc = cambia a la pantalla del juego
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Lógica para reanudar el juego
            // NO SE CREA UN NEW GAMESCREEN, SE LE ENTREGA EL MISMO PARA MANTENER EL ESTADO ANTERIOR DEL JUEGO
            game.setScreen(gameScreen);
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}
