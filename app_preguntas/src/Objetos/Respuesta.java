package Objetos;

import app_preguntas.Main;
import processing.core.PApplet;
import processing.core.PVector;

public class Respuesta {

	public String descripcion;
	public boolean valor;
	public PApplet app;
	public int select;

	public Respuesta() {
	}

	public Respuesta(String descripcion, boolean valor) {
		this.descripcion = descripcion;
		this.valor = valor;
		this.app = Main.app;
	}

	public void pintar(PVector pos, PVector tam) {
		if (select == 1) {
			app.fill(158, 158, 158);
		}else if(select == 2){
			if(valor) {
				app.fill(33, 232, 108);
			}else {
				app.fill(230, 205, 216);
			}
		}else if(select == 3){
			app.fill(255, 95, 197);
		}else {
			app.fill(255);
		}
		app.rect(pos.x, pos.y, tam.x, tam.y);
		app.fill(0);
		app.text(this.descripcion, pos.x, pos.y, tam.x, tam.y);
	}

}
