package io.Snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.Snake.main.Main;

// Esta clase es una plantilla para las pantallas del juego
public abstract class TemplateScreen implements Screen{

    //Se agrupan los elementos que todas las pantallas del juego deben tener
    protected Main game;
    protected SpriteBatch batch;
    protected OrthographicCamera camera;

    public TemplateScreen(Main game) {
        // Se recibe el archivo Main para hacer los cambios de pantalla (También puede instanciarse en un Objeto Game en lugar de Main)
        this.game = game;
        // Se crea una camara para poder ajustarse a cambios en el tamaño de la pantalla
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void actualizarCamara() {
        // Actualiza la cámara para que los sprites se dibujen correctamente
        // al cambiar el tamaño de la pantalla
        camera.update();
        //Si se elimina, las imágenes se estirarán por la pantalla
        batch.setProjectionMatrix(camera.combined);
    }

    public void limpiarPantalla() {
        // Limpia la pantalla con un color específico
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    // Las siguientes funciones son llamadas automáticamente por el juego 
    // al realizar la acción descrita, redimensiona, mostrar, etc
    @Override
    public void resize(int width, int height) {
        System.out.println("Resize Width: " + width + " Height: " + height);
        // Se actualiza la cámara al cambiar el tamaño de la pantalla
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void show() {
        
    }

    // Funciones de pause y resume se usan con app móviles
    // donde la aplicación puede ser minimizada
    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        
    }

    // Funcion para liberar los recursos del juego cuando 
    // se cierra o se cambia la pantalla
    @Override
    public void dispose() {
        batch.dispose();
    }
}
