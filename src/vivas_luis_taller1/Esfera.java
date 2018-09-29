package vivas_luis_taller1;

public class Esfera extends Figura {

	private boolean moverEsf;
	
	public Esfera(Main app) {
		super(app);
		
		x = app.random(800, 1100);
		y = app.random(300, 600);
		tam = 40;
	}

	public void pintar() {
		app.strokeWeight(3);
		app.stroke(357,86,78);
		app.fill(58, 83, 91);
		app.ellipse(x, y, tam, tam);
	}

}
