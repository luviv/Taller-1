package vivas_luis_taller1;

public abstract class Figura {
	
	protected Main app;
	protected float x, y, tam, color;
	protected boolean mover;
	
	public Figura(Main app) {
		this.app = app;
		mover = false;
	}
	
	public abstract void pintar();

}
