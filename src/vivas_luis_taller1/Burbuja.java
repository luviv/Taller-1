package vivas_luis_taller1;

public class Burbuja extends Figura {
	private float vel;
	private boolean oscilar;
	
	public Burbuja(Main app) {
		super(app);
		
		x = app.random(app.width/2-225, app.width/2+225);
		y = app.height;
		tam = 30;
		vel = 0;
		oscilar = false;
		
	}

	public void pintar() {
		app.ellipseMode(app.CENTER);
		app.noStroke();
		app.fill(156, 4, 45, 60);
		app.ellipse(x, y, tam+8, tam+8);
		app.fill(0, 1, 73, 40);
		app.ellipse(x, y, tam, tam);
		app.fill(360);
		app.ellipse(x-8, y-8, tam-20, tam-20);
		vel += 0.1;
		y -= vel;
		if(oscilar) {
			x++;
		} else {
			x--;
		}
		
		if(app.frameCount%15 == 0) {
			oscilar = !oscilar;
		}
		
	}
	
	public float getY() {
		return y;
	}
}