package vivas_luis_taller1;

public class Estrella extends Figura {

	public Estrella(Main app) {
		super(app);
		x = app.random(app.width);
		y= app.random(0, 202);
		tam = 5;
	}

	@Override
	public void pintar() {
		app.fill(0, 0, 100);
		app.ellipse(x, y, tam, tam);
		
	}

}
