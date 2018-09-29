package vivas_luis_taller1;

public class Esfera extends Figura {

	private boolean validarRotar;
	private float ang;
	
	public Esfera(Main app) {
		super(app);
		
		x = app.random(800, 1100);
		y = app.random(300, 600);
		tam = 40;
		ang = 15;
	}

	public void pintar() {
		app.strokeWeight(3);
		app.stroke(357,86,78);
		app.fill(58, 83, 91);
		app.ellipse(x, y, tam, tam);
		if(validarRotar) {
			app.pushMatrix();
			app.translate(app.width/2, 700);
			app.rotate(app.radians(ang));
			app.ellipse(x, y, tam, tam);
			app.popMatrix();
			ang  += 3;
		}
	}
	
	public boolean validar(float x, float y) {
		if(app.dist(this.x, this.y, x, y) < tam/2) {
			return true;
		} else {return false;
				
		}
	}
	
	public void mover(float x, float y) {
		this.x = x;
		this.y = y;
		
	}
	
	public void girar() {
		validarRotar = true;
		x = 0;
		y = -350;
	}
	
	public void setAng(float a) {
		ang = a;
	}


}
