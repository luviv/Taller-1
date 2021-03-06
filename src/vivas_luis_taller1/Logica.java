package vivas_luis_taller1;

import java.util.ArrayList;
import java.util.LinkedList;

public class Logica {
	
	private Main app;
	private LinkedList<Figura> figuras;
	private ArrayList<Burbuja> burbujas;
	private Estrella[] estrellas;
	private Esfera[] esferas;
	private Esfera esfera;
	private Barco barco;
	private String[] texto;
	private String[] palabras;
	private float x, y, tam;
	private Figura selector;
	private int contador, opacidad, opa;
	private boolean arrastrar, pintarTentaculo, validarEstrellas, crearBurbujas, pintarRayo, pintarEsf;
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
		burbujas = new ArrayList<Burbuja>();
		palabras = app.splitTokens(texto[0]);
		app.textSize(40);
		for (int i = 0; i < palabras.length; i++) {
			System.out.println(i+". "+palabras[i]);
		}
		estrellas = new Estrella[150];
		for (int i = 0; i < estrellas.length; i++) {
			estrellas[i] = new Estrella(app);
		}
		esferas = new Esfera[4];
		for (int i = 0; i < esferas.length; i++) {
			esferas[i] = new Esfera(app);
		}
		
		barco = new Barco(app);
		
		esfera = null;
	}
	
	//Este metodo recolecta los metodos de que pintan todo el lienzo
	public void pintar() {
		pintarFondo();
		letrasCthulhu();
		pintarRocasIz();
		pintarRocasDer();
		pintarEstrellas();
		interBur();
		barco.pintar();
		pintarEsf();
		pintarR();
		//Condición para el contador para la interacción del rayo
		if(!app.mousePressed) {
			contador = -5000;
		}
		
		//For para la interacción de las esferas
		for (int i = 0; i < esferas.length; i++) {
			if(esfera != null && esferas[i] == esfera) {
				esferas[i].mover(app.mouseX, app.mouseY);
				esfera.mover(app.mouseX, app.mouseY);
			}
		}
		
		//Condición para la interacción del barco
		if(barco.getDesplazar()) {
			barco.mover(app.mouseX);
		}
		
	}
	
	//Este metodo pinta el rayo
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
				
				palabras[35] = "ahora";
				palabras[36] = "en";
			} else {
				pintarRayo = false;
			}
		} else if(app.frameCount < 120) {
			app.fill(0, 0, 100, opacidad);
			app.rect(0, 0, app.width, app.height);
			opacidad -= 3;
		}
		
		if(pintarRayo == false) {
			opa = 100;
			app.textSize(20);
			app.fill(229, 38, 44, opa);
			app.text("manten presionado click", 950, 20);
		} else {
			opa = 0;
		}
	}
	
	//Este metodo se encarga de la interacción de las burbujas
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
	
	//Este metodo se encarga de todas las interacción que tengan que ver con clickear
	public void click() {
		if(app.dist(app.mouseX, app.mouseY, app.width/2, app.height) < 300) {
			crearBurbujas = !crearBurbujas;
			palabras[65] = "ha";
			palabras[66] = "tenido";
			palabras[67] = "que";
			palabras[68] = "ser";
			palabras[69] = "liberado";
	
		}
		
		
		for (int i = 0; i < esferas.length; i++) {
			if(esferas[i].validar(app.mouseX, app.mouseY)) {
				esfera = esferas[i];
			}
		}
		
		if(esfera == null) {
			contador = app.millis();
		}
		
		barco.validar(app.mouseX, app.mouseY);
		if(barco.validar(app.mouseX, app.mouseY)) {
			palabras[31] = "se";
			palabras[32] = "hundio";
		}
	}
	
	//Este metodo se encarga de lo que pasa al soltar el click
	public void release() {
		for (int i = 0; i < esferas.length; i++) {
			if(esfera == esferas[i] && app.dist(app.mouseX, app.mouseY, app.width/2, 700) < 275) {
				esferas[i].girar();
			}
			esferas[i].setAng((360/4)*i);
		}
		
		esfera = null;
		barco.setDesplazar(false);
	}
	
	//Este metodo pinta el fondo del lienzo
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
	
	//Este metodo pinta las letras que ayudan al usuario a la interacción de Cthulhu
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
			palabras[1] = "ha";
			palabras[2] = "despertado";
		}
		interaccionCthulhu();
	}
	//Este metodo pinta al personaje Cthulhu cuando se cumple la condición
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
	//Este metodo se encarga de la interacción de escribir la palabra "CTHULHU"
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
	//Este metodo pinta las rocas de la izquierda
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
	//Este metodo pinta el tentaculo si se cumple la condición
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
	//Este metodo se encarga de la interacción del tentaculo
	public void rocasIz() {
		
		if(app.dist(app.mouseX, app.mouseY, 0, 600) < 150 || app.dist(app.mouseX, app.mouseY, 100, 700) < 150 || app.dist(app.mouseX, app.mouseY, 0, 700) < 150 ) {
			pintarTentaculo = true;
			palabras[92] ="se";
			palabras[93] ="hundira";
			
		} else {pintarTentaculo = false;}
		
		if(pintarTentaculo == true) {
			pintarTentaculo();
			
		}
	}
	//Este metodo se encarga de la interacción de las esferas
	public void rocasDer() {
		
		if(app.dist(app.mouseX, app.mouseY, 1200, 600) < 150 || app.dist(app.mouseX, app.mouseY, 1100, 700) < 150 || app.dist(app.mouseX, app.mouseY, 1200, 700) < 150 ) {
			pintarEsf = true;
		} 
		
	}
	
	//Este metodo pinta las rocas de la derecha
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
	
	//Este metodo se encarga de la interacción de las estrellas
	public void pintarEstrellas() {
		if(validarEstrellas) {
			for (int i = 0; i < estrellas.length; i++) {
				estrellas[i].pintar();
			}
			palabras[113] = "las";
			palabras[114] = "estrellas";
			palabras[115] = "sobre";
		}
		if(validarEstrellas == false) {
			opa = 100;
			app.textSize(20);
			app.fill(229, 38, 44, opa);
			app.text("presiona 'e'", 8, 20);
		} else {
			opa = 0;
		}
		
	}
	
	//Este metodo valida la interacción de las estrellas
	public void interaccionEst() {
		if(app.key == 'e' || app.key == 'E') {
			validarEstrellas = !validarEstrellas;
		}
	}
	
	//Este metodo pinta las esferas
	public void pintarEsf() {
		if(pintarEsf) {
			for (int i = 0; i < esferas.length; i++) {
				esferas[i].pintar();
				
			}
			palabras[120] = "ahora";
			palabras[121] = "flota";
		}
	}
}
