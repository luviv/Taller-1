package vivas_luis_taller1;

public class Burbuja extends Figura {
	float vel;

	public Burbuja(Main app) {
		super(app);
		vel = 0;
		x = app.random((app.width/2)-200, (app.width/2)+200);
		y = app.height;
	}

	public void pintar() {
		
	}
}
