package Objetos;

import app_preguntas.Logica;
import app_preguntas.Main;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Pregunta {

	private PApplet app;
	private Logica log;

	public float calificacion;

	private int estado;
	private String pregunta;
	private Respuesta A;
	private Respuesta B;
	private Respuesta C;
	private Respuesta D;
	private PVector posQuestion, posA, posB, posC, posD;
	private PVector tamQuestion, tamRequest;
	public boolean comprobada;

	private int colorPregunta, colorRespuesta;
	private int tamTextPregunta, tamTextRespuesta;

	public Pregunta(String pregunta, Respuesta a, Respuesta b, Respuesta c, Respuesta d) {
		this.app = Main.app;
		this.log = Logica.log;
		this.pregunta = pregunta;
		A = a;
		B = b;
		C = c;
		D = d;
		tamQuestion = new PVector(app.width, app.height / 3);
		tamRequest = new PVector(app.width / 2, (app.height - (tamQuestion.y)) / 2);
		posQuestion = new PVector(app.width / 2, tamQuestion.y / 2);

		posA = new PVector(app.width / 4, tamQuestion.y + (tamRequest.y / 2));
		posB = new PVector(app.width / 4, tamQuestion.y + (tamRequest.y / 2) + (tamRequest.y));
		posC = new PVector((app.width / 4) + tamRequest.x, tamQuestion.y + (tamRequest.y / 2));
		posD = new PVector((app.width / 4) + tamRequest.x, tamQuestion.y + (tamRequest.y / 2) + (tamRequest.y));

		colorPregunta = app.color(255);
		colorRespuesta = app.color(0);

		tamTextPregunta = 40;
		tamTextRespuesta = 30;
		
		estado = 1;
	}

	public void pintar() {
		app.rectMode(PConstants.CENTER);
		app.fill(colorPregunta);
		app.rect(posQuestion.x, posQuestion.y, tamQuestion.x, tamRequest.y);

		app.textSize(tamTextPregunta);
		app.textAlign(PConstants.CENTER, PConstants.CENTER);
		app.fill(colorRespuesta);
		app.text(pregunta, posQuestion.x, posQuestion.y, tamQuestion.x, tamRequest.y);
		app.textSize(tamTextRespuesta);
		A.pintar(posA, tamRequest);
		B.pintar(posB, tamRequest);
		C.pintar(posC, tamRequest);
		D.pintar(posD, tamRequest);

	}

	public void setColorText(int colorA, int colorB) {
		colorPregunta = colorA;
		colorRespuesta = colorB;
	}

	public void setTamText(int tamA, int tamB) {
		tamTextPregunta = tamA;
		tamTextRespuesta = tamB;
	}

	public void selectRespond(int select) {
		if (comprobada == false) {
			this.estado = select;

			switch (select) {
			case 1:

				A.select = 1;
				B.select = 0;
				C.select = 0;
				D.select = 0;

				break;

			case 2:

				A.select = 0;
				B.select = 1;
				C.select = 0;
				D.select = 0;

				break;

			case 3:

				A.select = 0;
				B.select = 0;
				C.select = 1;
				D.select = 0;

				break;

			case 4:

				A.select = 0;
				B.select = 0;
				C.select = 0;
				D.select = 1;

				break;

			default:
				break;
			}
		}
	}

	public void comprobar() {
		if (comprobada == false) {
			A.select = 2;
			B.select = 2;
			C.select = 2;
			D.select = 2;

			if (estado == 1 && A.valor) {
				calificacion = 5;
				Logica.log.arduino.enviarDatos(8);

			} else if (estado == 2 && B.valor) {
				calificacion = 5;
				Logica.log.arduino.enviarDatos(8);

			} else if (estado == 3 && C.valor) {

				calificacion = 5;
				Logica.log.arduino.enviarDatos(8);
			} else if (estado == 4 && D.valor) {

				calificacion = 5;
				Logica.log.arduino.enviarDatos(8);
			} else {
				calificacion = 0;
				Logica.log.arduino.enviarDatos(10);
			}

			if (estado == 1 && !A.valor) {
				A.select = 3;

			} else if (estado == 2 && !B.valor) {
				B.select = 3;

			} else if (estado == 3 && !C.valor) {

				C.select = 3;
			} else if (estado == 4 && !D.valor) {

				D.select = 3;
			}
			comprobada = true;
		}
	}
}
