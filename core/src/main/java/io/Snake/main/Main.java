package io.Snake.main;

import com.badlogic.gdx.Game;

import io.Snake.screens.MenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    // Se pueden guardar los estados del juego aquí o dentro de cada pantalla de nivel, como GameScreen
    // Desventaja de guardarlos aquí es que se deben crear muchos getters y setters, o bien al menos uno
    // de alguna clase que guarde los datos como el "Modelo" de su proyecto anterior


    @Override
    public void create() {
        // Si se requiere cambiar el tamaño de la ventana al iniciar el juego,
        // Se puede hacer desde el archivo Lwjgl3Launcher.java
        setScreen(new MenuScreen(this));
    }

}