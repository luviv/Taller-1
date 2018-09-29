package vivas_luis_taller1;

public class Barco extends Figura {

	public Barco(Main app) {
		super(app);
		x = 106;
		y= 132;
		tam = 36;
		mover = false;
	}

	public void pintar() {
		app.noStroke();
		app.fill(243, 32, 25);
		app.rect(x, y, 200, 28);
		for (int i = 0; i < 5; i++) {
			app.fill(45, 61, 88);
			app.ellipse(x-17, 141, 24, 24);
			x += 35;
		}
		app.fill(251, 36, 17);
		app.rect(x, y+28, 200, 56);
		app.triangle(x-75, y+28, x, y+28, x, y+84);
		app.triangle(x+275, y+28, x+200, y+28, x+200, y+84);
	}
}
