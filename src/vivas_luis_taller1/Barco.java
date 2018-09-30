package vivas_luis_taller1;

public class Barco extends Figura {
	
	private boolean desplazar;

	public Barco(Main app) {
		super(app);
		x = 106;
		y= 132;
		tam = 36;
		mover = true;
		desplazar = false;
	}

	public void pintar() {
		app.noStroke();
		app.fill(243, 32, 25);
		app.rect(x, y, 200, 28);
		for (int i = 0; i < 5; i++) {
			float newX = x+(35*i);
			app.fill(45, 61, 88);
			app.ellipseMode(app.CORNER);
			app.ellipse(newX+17, 138, 20, 20);
			app.ellipseMode(app.CENTER);
		}
		app.fill(251, 36, 17);
		app.rect(x, y+28, 200, 56);
		app.triangle(x-75, y+28, x, y+28, x, y+84);
		app.triangle(x+275, y+28, x+200, y+28, x+200, y+84);
		app.textSize(30);
		app.fill(45, 61, 88);
		app.text("VIGILANT", x+20, y+60);
		stop();
	}
	
	public boolean validar(float x, float y) {
		if(x > this.x - 34 && x < this.x +232 && y > this.y && y < this.y +92) {
			desplazar = true;
			return true;
		} else {return false;
		}
	}
	
	public void mover(float x) {
		if(mover) {
			this.x = x;
		}
	}
	
	public void stop() {
		if(x > 872) {
			mover = false;
		}
	}

	public boolean getDesplazar() {
		return desplazar;
	}

	public void setDesplazar(boolean desplazar) {
		this.desplazar = desplazar;
	}
	
}
