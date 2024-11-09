package io.Snake.screens;

import io.Snake.main.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;

// Revisar primero los comentarios en TemplateScreen.java
public class MenuScreen extends TemplateScreen {

    private BitmapFont font;

    // Aquí es donde deben crear e instanciarse los objetos que se usarán en el renderizado
    public MenuScreen(Main game) {
        super(game);
        // Se crea un elemento para escribir en la pantalla
        font = new BitmapFont();
        // Se ajusta la escala de tamaño de los caracteres
        font.getData().setScale(2f);
        // El batch es el lienzo donde se dibujarán los elementos
        batch = new SpriteBatch();
    }

    // El renderizado es un ciclo infinito en el que se limpia la pantalla 
    // + se actualizan los estados + se vuelve a dibujar en el batch
    @Override
    public void render(float delta) {
        // Actualizar la cámara
        actualizarCamara();

        // Limpiar la pantalla
        limpiarPantalla();

        // Dibujar el menu principal
        //Entre begin y end NO SE DEBEN HACER CAMBIOS DE PANTALLA porque causarán errores en el manejo de la memoria 
        batch.begin();
        // Se escribe por la pantalla
        // Con Gdx.graphics se obtienen las medidas de la pantalla
        font.draw(batch, "Menu Principal", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 100);
        font.draw(batch, "Jugar (Presione la palabra)", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 150);
        font.draw(batch, "Highscore (Presione E)", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 200);
        batch.end();

        // Acción cuando se presiona la pantalla
        if (Gdx.input.isTouched()) {
            System.out.println("X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY());
        }

        // Acción cuando se presiona la pantalla en una posición exacta
        if(Gdx.input.isTouched() && (Gdx.input.getX() >= Gdx.graphics.getWidth()/2 - 300 && Gdx.input.getX() <= Gdx.graphics.getWidth()/2 - 30) && (Gdx.input.getY() >= 140 && Gdx.input.getY() <= 180)) {
            System.out.println("Jugar");
		    game.setScreen(new GameScreen(game));
			dispose();
		}

        if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            System.out.println("Highscore");
            game.setScreen(new HighscoreScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}