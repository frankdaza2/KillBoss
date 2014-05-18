package com.knarf.killboss2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.knarf.killboss.GuardarPartida;
import com.knarf.killboss.KillBoss;
import com.knarf.killboss.MenuIntro;

public class ValentinaGana implements Screen {
	
	final KillBoss juego;
	public Texture fondoImg;
	public Rectangle fondoR;
	public OrthographicCamera camara;
	public int puntaje;
	public int vidas;
	public Sound gana;
	
	/**
	 * Método constructor
	 */
	public ValentinaGana(final KillBoss juego, int puntaje, int vidas) {
		this.juego = juego;
		this.puntaje = puntaje;
		this.vidas = vidas;
		
		// Cargo la imagen de fondo
		this.fondoImg = new Texture(Gdx.files.internal("valentinaGana.png"));
		
		// Configuro el sonido cuando gana una pregunta
		this.gana = Gdx.audio.newSound(Gdx.files.internal("sounds/gana.mp3"));
		
		// Configuro la cámara
		this.camara = new OrthographicCamera();
		this.camara.setToOrtho(false, 2048, 1024);
		
		// Creo el rectángulo lógico para el fondo
		this.fondoR = new Rectangle();
		this.fondoR.x = 2048 / 2 - 1024 / 2;
		this.fondoR.y = 1024 / 2 - 1024 / 2;
		this.fondoR.width = 1024;
		this.fondoR.height = 1024;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// Actualizo la cámara
		this.camara.update();
		
		// Dibulo el fondo
		this.juego.batch.begin();
		this.juego.batch.draw(this.fondoImg, this.fondoR.x, this.fondoR.y);
		this.juego.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {			
			GuardarPartida g = new GuardarPartida(this.juego, this.puntaje, 7);
        	g.inicio();
        	this.juego.setScreen(new MenuIntro(this.juego));
        	this.dispose();
		}			
	}	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		this.gana.play();		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		this.fondoImg.dispose();
		this.gana.dispose();
	}
}