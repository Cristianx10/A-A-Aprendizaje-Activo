package Arduino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class ArduinoConexion implements SerialPortEventListener {

	private OutputStream Output = null;
	private InputStream Input = null;
	SerialPort serialPort;
	private final String PORT_NAME = "COM6";
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	private static final int ERROR = 0;

	public ArduinoData arduinoData;

	public ArduinoConexion( ArduinoData arduinoData) {
		this.arduinoData = arduinoData;
		try {
			ArduinoConnection();
		} catch (TooManyListenersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ArduinoConnection() throws TooManyListenersException {

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

			if (PORT_NAME.equals(currPortId.getName())) {
				portId = currPortId;
				break;
			}
		}

		if (portId == null) {

			System.out.println("No se encontro ninguna arduino en el puerto indicado");
			return;
		}

		try {

			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			Output = serialPort.getOutputStream(); // Se prepara a Output //para enviar datos
			Input = serialPort.getInputStream(); // Se prepara input para //recibir datos

			serialPort.addEventListener(this); // Se agrega un Event //Listener
			serialPort.notifyOnDataAvailable(true); // Se indica que se //notifique al usuario cuando sea que halla
													// datos disponibles en //el puerto serie
		} catch (Exception e) {

			System.out.println("No se encontro ninguna arduino en el puerto indicado");
		}

	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String datos;
				datos = RecibirDatos(); // Se invoca la función RecibirDatos()
				arduinoData.serialEvent(datos);
				// Esta función devolverá un valor entero en formato ASCII.

				// haciendo la conversión de ASCII a nuestro alfabeto.
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

	private String RecibirDatos() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Input));
		String line = reader.readLine();

		return line;

	}

	public void enviarDatos(int datos) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Output = serialPort.getOutputStream();
					Output.write(datos);

				} catch (Exception e) {

				}
			}
		}).start();

	}

	public interface ArduinoData {
		public void serialEvent(String puerto);
	}

}
