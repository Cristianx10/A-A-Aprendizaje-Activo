package app_preguntas;

import java.util.ArrayList;

import Arduino.ArduinoConexion;
import Arduino.ArduinoConexion.ArduinoData;
import Objetos.Pregunta;
import Objetos.Respuesta;
import Objetos.Usuario;
import Arduino.ComunicacionMulticast;
import Arduino.Comunicador;
import app_preguntas.Pantalla.InPantalla;
import processing.core.PApplet;

public class Logica implements InPantalla, ArduinoData, Comunicador {

	public static Logica log;

	public int opcion;

	public Usuario usuario;
	private Pantalla p;
	private int pregunta;
	private PApplet app;
	public ArduinoConexion arduino;
	private ComunicacionMulticast multicast;
	private ArrayList<Pregunta> preguntas;

	private float puntuacion;
	private boolean calculado;

	public Logica() {
		usuario = new Usuario("Nombre", "uid");
		log = this;
		this.p = new Pantalla(this);
		this.app = Main.app;
		arduino = new ArduinoConexion(this);
		multicast = new ComunicacionMulticast(this);
		multicast.start();
		preguntas = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			Respuesta r1 = new Respuesta("America", true);
			Respuesta r2 = new Respuesta("Europa", false);
			Respuesta r3 = new Respuesta("Francia", false);
			Respuesta r4 = new Respuesta("Estados unidos", false);

			Pregunta p = new Pregunta("Donde vivimos", r1, r2, r3, r4);
			preguntas.add(p);
		}

	}

	public void draw() {
		p.visualizar();
	}

	@Override
	public void inicio() {
		app.background(226, 235, 248);
		app.ellipse(app.width / 2, app.height / 2, 50, 50);
		p.setActual("juego");
	}

	@Override
	public void juego() {
		app.background(226, 235, 248);

		if (pregunta < preguntas.size()) {
			preguntas.get(pregunta).pintar();
			preguntas.get(pregunta).selectRespond(opcion);

		}

	}

	@Override
	public void resumen() {
		app.background(248, 226, 166);

		if (calculado == false) {
			int puntaje = 0;
			for (int i = 0; i < preguntas.size(); i++) {
				Pregunta p = preguntas.get(i);
				puntaje += p.calificacion;
				System.out.println( p.calificacion);
			}

			puntuacion = puntaje / preguntas.size();

			multicast.enviar(usuario.uid + ":" + puntuacion);
			calculado = true;
		}

	}

	@Override
	public void ganador() {
		// TODO Auto-generated method stub

	}

	@Override
	public void defult() {
		// TODO Auto-generated method stub

	}

	public void mousePressed() {
		// TODO Auto-generated method stub
		siguiente();
	}

	@Override
	public void serialEvent(String puerto) {
		int i = 0;
		try {
			i = Integer.parseInt(puerto);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (i <= 4) {
			opcion = i;
		}

		if (i == 6) {
			siguiente();
		}

	}

	@Override
	public void Receptor(String mensaje) {
		// TODO Auto-generated method stub
		if (mensaje.contains("holaSoy") && usuario == null) {
			String[] separa = mensaje.split(":");
			Usuario user = new Usuario(separa[1], separa[2]);
			usuario = user;
		}

		if (mensaje.contains("pregunta:")) {
			String[] separa = mensaje.split(":");

			boolean q1 = separa[3].equals("false") ? false : true;
			boolean q2 = separa[5].equals("false") ? false : true;
			boolean q3 = separa[7].equals("false") ? false : true;
			boolean q4 = separa[9].equals("false") ? false : true;

			Respuesta r1 = new Respuesta(separa[2], q1);
			Respuesta r2 = new Respuesta(separa[4], q2);
			Respuesta r3 = new Respuesta(separa[6], q3);
			Respuesta r4 = new Respuesta(separa[8], q4);

			Pregunta p = new Pregunta(separa[1], r1, r2, r3, r4);

			preguntas.add(p);
		}
	}

	public void siguiente() {
		if (pregunta < preguntas.size()) {
			int condicion = 0;
			if (preguntas.get(pregunta).comprobada) {
				condicion = 1;
			}
			switch (condicion) {
			case 1:
				this.pregunta++;
				if (pregunta < preguntas.size()) {
					arduino.enviarDatos(9);
				} else {
					p.setActual("resumen");
				}

				break;

			case 0:
				if (pregunta < preguntas.size()) {
					if(opcion == 0) {
						preguntas.get(pregunta).selectRespond(1);
					} System.out.println(opcion);
					preguntas.get(pregunta).comprobar();
					
				}
				break;
			}
		} else {
			arduino.enviarDatos(9);
			p.setActual("resumen");
		}

	}

}
