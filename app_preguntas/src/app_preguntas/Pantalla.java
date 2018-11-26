package app_preguntas;

import java.util.ArrayList;

import Objetos.Pregunta;

public class Pantalla {

	private InPantalla pantallas;
	private String actual;
	

	public Pantalla(InPantalla p) {
		this.pantallas = p;
		actual = "inicio";
		
	}

	public void visualizar() {
		if (pantallas != null) {
			switch (actual) {
			case "inicio":
				pantallas.inicio();
				break;

			case "juego":
				pantallas.juego();
				break;

			case "resumen":
				pantallas.resumen();
				break;

			case "ganador":
				pantallas.ganador();
				break;
			default:
				pantallas.defult();
				break;
			}
		}
	}
	
	

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}



	public interface InPantalla {
		public void inicio();

		public void juego();

		public void resumen();

		public void ganador();

		public void defult();

	}

}
