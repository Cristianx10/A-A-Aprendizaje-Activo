package app_preguntas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.Buffer;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import Arduino.ArduinoConexion.ArduinoData;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import processing.core.PApplet;
//import processing.serial.*;

public class Main extends PApplet {

	static public PApplet app;

	Logica log;

	public static void main(String[] args) {
		PApplet.main("app_preguntas.Main");

	}

	@Override
	public void settings() {
		size(1200, 700);

	}

	@Override
	public void setup() {
		app = this;
		log = new Logica();
	}

	@Override
	public void draw() {
		log.draw();
	}

	@Override
	public void mousePressed() {
		log.mousePressed();
	}

}