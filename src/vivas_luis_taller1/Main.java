package vivas_luis_taller1;

import processing.core.PApplet;

public class Main extends PApplet {
	
	private Logica logica;

	public static void main(String[] args) {
		PApplet.main("vivas_luis_taller1.Main");		

	}
	
	public void settings() {
		size(1200, 700);
	}
	
	public void setup() {
		colorMode(HSB, 360, 100, 100, 100);
		logica = new Logica(this);
	}
	
	public void draw() {
		logica.pintar();
	}
	
	public void mousePressed() {
		logica.click();
	}
	
	public void mouseDragged() {
		
	}
	
	public void mouseReleased() {
		
	}
	
	public void mouseMoved() {
		logica.rocasIz();
	}
	
	public void keyPressed() {
		logica.interaccionEst();
	}

}
