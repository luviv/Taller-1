package vivas_luis_taller1;

import java.util.ArrayList;
import java.util.LinkedList;

public class Logica {
	
	private Main app;
	private LinkedList<Figura> figuras;
	private ArrayList<Estrella> estrellas;
	private String[] texto;
	private String[] palabras;
	private float x, y, tam, mx, my;
	private Figura selector;
	private boolean arrastrar, pintarTentaculo, validarEstrellas;
	private StringBuffer cthu = new StringBuffer("       ");

	public Logica(Main app) {
		this.app = app;
		figuras  = new LinkedList<Figura>();
		selector = null;
		arrastrar = false;
		texto = app.loadStrings("texto.txt");
//		System.out.println(texto.length);
		palabras = app.splitTokens(texto[0]);
		app.textSize(40);
		mx = app.mouseX;
		my = app.mouseY;
		for (int i = 0; i < palabras.length; i++) {
			System.out.println(palabras[i]);
		}
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
		app.text(mx, 100, 100);
		app.text(my, 100, 200);
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
		app.fill(93, 40, 58);
		app.ellipse(app.width/2, 485, 200, 200);
		app.fill(356, 88, 78);
		app.arc(560, 465, 40, 40, 0, app.PI+app.QUARTER_PI, app.CHORD);
		app.ellipse(640, 465, 40, 40);
		app.fill(58, 83, 91);
		app.arc(560, 466, 33, 33, 0, app.PI+app.QUARTER_PI, app.CHORD);
		app.ellipse(640, 465, 33, 33);
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
	
//	public void pintarEstrellas() {
//		for (int i = 0; i < 50; i++) {
//			estrellas.add(new Estrella(app));
//		}
//	}
	
	public void interaccionEst() {
		if(app.key == 'e' || app.key == 'E') {
			validarEstrellas = true;
		}
		
//		if (validarEstrellas = true) {
//			pintarEstrellas();
//		}
		
		if (validarEstrellas = true && app.key == 'e' || app.key =='E') {
			validarEstrellas = false;
		}
	}
}
