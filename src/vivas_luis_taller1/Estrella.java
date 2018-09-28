package vivas_luis_taller1;

public class Estrella extends Figura {

	public Estrella(Main app) {
		super(app);
		x = app.random(app.width);
		y= app.random(0, 202);
		tam = 5;
		color = (360);
	}

	@Override
	public void pintar(Main app) {
		app.fill(color);
		app.ellipse(x, y, tam, tam);
		
	}

}
