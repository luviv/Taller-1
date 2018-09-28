package vivas_luis_taller1;

import java.util.ArrayList;
import java.util.LinkedList;

public class Logica {
	
	private Main app;
	private LinkedList<Figura> figuras;
	private ArrayList<Burbuja> burbujas;
	private Estrella[] estrellas;
	private String[] texto;
	private String[] palabras;
	private float x, y, tam;
	private Figura selector;
	private int contador, opacidad;
	private boolean arrastrar, pintarTentaculo, validarEstrellas, crearBurbujas, pintarRayo;
	private StringBuffer cthu = new StringBuffer("       ");

	public Logica(Main app) {
		this.app = app;
		figuras  = new LinkedList<Figura>();
		selector = null;
		arrastrar = false;
		crearBurbujas = false;
		pintarRayo = false;
		contador = -5000;
		opacidad = 100;
		texto = app.loadStrings("texto.txt");
//		System.out.println(texto.length);
		burbujas = new ArrayList<Burbuja>();
		palabras = app.splitTokens(texto[0]);
		app.textSize(40);
		for (int i = 0; i < palabras.length; i++) {
			System.out.println(palabras[i]);
		}
		estrellas = new Estrella[150];
		for (int i = 0; i < estrellas.length; i++) {
			estrellas[i] = new Estrella(app);
		}
	}
	
	public void pintar() {
		pintarFondo();
		letrasCthulhu();
		pintarRocasIz();
		pintarRocasDer();
		pintarEstrellas();
		interBur();
		pintarR();
		if(!app.mousePressed) {
			contador = -5000;
		}
	}
	
	public void pintarR() {
		if(app.millis()-contador > 950 && app.millis()-contador < 1050) {
			pintarRayo = true;
			app.frameCount = 0;
		}
		
		if(pintarRayo) {
			if(app.frameCount < 60) {
				app.fill(0, 0, 100);
				app.quad(550, 0, 625, 0, 575, 50, 500, 50);
				app.quad(538, 50, 613, 50, 575, 100, 500, 100);
				app.triangle(537, 100, 612, 100, 500, 200);
				opacidad = 100;
			} else {
				pintarRayo = false;
			}
		} else if(app.frameCount < 120) {
			app.fill(0, 0, 100, opacidad);
			app.rect(0, 0, app.width, app.height);
			opacidad -= 3;
		}
	}
	
	public void interBur() {
		if(app.frameCount%20 == 0 && crearBurbujas) {
			burbujas.add(new Burbuja(app));
		}
		
		for(int i = burbujas.size()-1; i >= 0; i--) {
			Burbuja b = burbujas.get(i);
			b.pintar();
			if(b.getY() < 202) {
				burbujas.remove(i);
			}
		}
	}
	
	public void click() {
		if(app.dist(app.mouseX, app.mouseY, app.width/2, app.height) < 300) {
			crearBurbujas = !crearBurbujas;
		}
		
		contador = app.millis();
	}
	
	public void pintarFondo() {
		app.background(229, 52, 33);
		app.noStroke();
		app.fill(160, 42, 33);
		app.rect(0, 202, app.width, 498);
		app.fill(160, 41, 39);
		app.rect(0, 211, app.width, 489);
		app.fill(165, 6, 25);
		app.ellipse(app.width/2, 700, 550, 650);
		app.fill(159, 45, 25);
		app.ellipse(app.width/2, 700, 525, 625);
		app.fill(164,42,10);
		app.ellipse(app.width/2, 700, 300, 400);

	}
	
	public void letrasCthulhu() {
		app.textSize(50);
		app.fill(159, 41, 33);
		app.textAlign(app.CENTER);
		app.text("CTHULHU", app.width/2, 300);
		app.fill(0, 0, 100);
		app.textAlign(app.CORNER);
		app.text(cthu.toString(), app.width/2-117, 300);
		
		if(palabras[0] == "CTHULHU") {
			pintarCthulhu();
		}
		interaccionCthulhu();
	}
	
	public void pintarCthulhu() {
		app.noStroke();
		app.ellipseMode(app.CENTER);
		app.fill(87, 35, 53);
		app.ellipse(485, 575, 70, 70);
		app.quad(453, 560, 520, 575, 440, 700, 370, 700);
		app.fill(87, 35, 53);
		app.ellipse(715, 575, 70, 70);
		app.quad(747, 560, 680, 575, 760, 700, 830, 700);
		app.fill(90, 37, 47);
		app.ellipse(app.width/2, 700, 300, 400);
		app.noFill();
		app.strokeWeight(50);
		app.stroke(92, 48, 48);
		app.arc(510, 475, 125, 125, -app.QUARTER_PI, app.HALF_PI+app.QUARTER_PI, app.OPEN);
		app.stroke(93, 40, 58);
		app.arc(510, 470, 125, 125, -app.QUARTER_PI, app.HALF_PI+app.QUARTER_PI, app.OPEN);
		app.stroke(92, 48, 48);
		app.arc(500, 535, 125, 125, -app.QUARTER_PI, app.HALF_PI+app.QUARTER_PI, app.OPEN);
		app.stroke(93, 40, 58);
		app.arc(500, 530, 125, 125, -app.QUARTER_PI, app.HALF_PI+app.QUARTER_PI, app.OPEN);
		app.stroke(92, 48, 48);
		app.arc(700, 475, 125, 125, app.QUARTER_PI, (app.HALF_PI+app.QUARTER_PI), app.OPEN);
		app.stroke(93, 40, 58);
		app.arc(700, 470, 125, 125, app.QUARTER_PI, (app.HALF_PI+app.QUARTER_PI), app.OPEN);
		app.stroke(92, 48, 48);
		app.arc(700, 535, 125, 125, app.QUARTER_PI, (app.HALF_PI+app.QUARTER_PI), app.OPEN);
		app.stroke(93, 40, 58);
		app.arc(700, 530, 125, 125, app.QUARTER_PI, (app.HALF_PI+app.QUARTER_PI), app.OPEN);
		app.stroke(92, 48, 48);
		app.arc(530, 585, 125, 125, -app.QUARTER_PI, app.HALF_PI+app.QUARTER_PI, app.OPEN);
		app.stroke(92, 48, 48);
		app.arc(665, 585, 125, 125, app.QUARTER_PI, (app.PI+app.QUARTER_PI), app.OPEN);
		app.stroke(93, 40, 58);
		app.arc(665, 580, 125, 125, app.QUARTER_PI, (app.PI+app.QUARTER_PI), app.OPEN);
		app.stroke(93, 40, 58);
		app.arc(530, 580, 125, 125, -app.QUARTER_PI, app.HALF_PI+app.QUARTER_PI, app.OPEN);
		app.noStroke();
		app.fill(93, 40, 58);
		app.ellipse(app.width/2, 485, 200, 200);
		app.fill(356, 88, 78);
		app.arc(560, 465, 40, 40, 0, app.PI+app.QUARTER_PI, app.CHORD);
		app.arc(640, 465, 40, 40, -app.QUARTER_PI, app.PI, app.CHORD);
		app.fill(58, 83, 91);
		app.arc(560, 466, 33, 33, 0, app.PI+app.QUARTER_PI, app.CHORD);
		app.arc(640, 466, 33, 33, -app.QUARTER_PI, app.PI, app.CHORD);

	}
	
	public void interaccionCthulhu() {
		String temp = cthu.toString();
		int index = 0;
		for (int i = 0; i < temp.length(); i++) {
			if(temp.charAt(i) != ' ') {
				index++;
			}
		}
		if(app.key == palabras[0].charAt(0) && cthu.charAt(0) != 'C' && index == 0) {
			cthu.replace(0, 0, "C");
		} else if(app.key == palabras[0].charAt(1) && cthu.charAt(1) != 'T' && index == 1) {
			cthu.replace(1, 1, "T");
		} else if(app.key == palabras[0].charAt(2) && cthu.charAt(2) != 'H' && index == 2) {
			cthu.replace(2, 2, "H");
		} else if(app.key == palabras[0].charAt(3) && cthu.charAt(3) != 'U' && index == 3) {
			cthu.replace(3, 3, "U");
		} else if(app.key == palabras[0].charAt(4) && cthu.charAt(4) != 'L' && index == 4) {
			cthu.replace(4, 4, "L");
		} else if(app.key == palabras[0].charAt(5) && cthu.charAt(5) != 'H' && index == 5) {
			cthu.replace(5, 5, "H");
		} else if(app.key == palabras[0].charAt(6) && cthu.charAt(6) != 'U' && index == 6) {
			cthu.replace(6, 6, "U");
		}

		if(temp.contains("CTHULHU")) {
			palabras[0] = "CTHULHU";
		}
	}
	
	public void pintarRocasIz() {
		x = 0;
		y = 600; 
		tam = 300;
		app.fill(60, 5, 15);
		app.ellipseMode(app.CENTER);
		app.ellipse(x, y, tam, tam);
		app.fill(0, 0, 18);
		app.ellipse(x+100, y+100, tam, tam);
		app.fill(0, 0, 25);
		app.ellipse(x, y+100, tam, tam);
		
		rocasIz();
	}
	
	public void pintarTentaculo() {
		x = 240;
		y= 378;
		tam = 14;
		app.noStroke();
		app.fill(87, 35, 53);
		app.triangle(255, 278, 255, 370, 211, 370);
		app.quad(211, 370, 255, 370, 95, 700, 49, 700);
		app.fill(87, 23, 64);
		app.triangle(255, 317, 255, 370, 230, 370);
		app.quad(230, 370, 255, 370, 95, 700, 69, 700);
		app.fill(87, 35, 53);
		app.ellipseMode(app.CENTER);
		app.ellipse(249, 345, 10, 10);
		for (int i = 0; i < 10; i++) {
			app.ellipse(x, y, tam, tam);
			x -= 16.5;
			y += 34;
		}
	}
	
	public void rocasIz() {
		
		if(app.dist(app.mouseX, app.mouseY, 0, 600) < 150 || app.dist(app.mouseX, app.mouseY, 100, 700) < 150 || app.dist(app.mouseX, app.mouseY, 0, 700) < 150 ) {
			pintarTentaculo = true;
		} else {pintarTentaculo = false;}
		
		if(pintarTentaculo == true) {
			pintarTentaculo();
		}
	}
	
	public void pintarRocasDer() {
		x = 1200;
		y = 600; 
		tam = 300;
		app.fill(60, 5, 15);
		app.ellipseMode(app.CENTER);
		app.ellipse(x, y, tam, tam);
		app.fill(0, 0, 18);
		app.ellipse(x-100, y+100, tam, tam);
		app.fill(0, 0, 25);
		app.ellipse(x, y+100, tam, tam);
		
	}
	
	public void pintarEstrellas() {
		if(validarEstrellas) {
			for (int i = 0; i < estrellas.length; i++) {
				estrellas[i].pintar();
			}
		}
		
	}
	
	public void interaccionEst() {
		if(app.key == 'e' || app.key == 'E') {
			validarEstrellas = !validarEstrellas;
		}
	}
}
