package Arduino;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class ComunicacionMulticast extends Thread {

	MulticastSocket socket;
	static int PUERTO;
	InetAddress grupo;
	String myIp;
	int maxId;
	
	Comunicador comunicador;
	
	//private ArrayList<Personas> per;
	private int turno;
	private boolean conectado;

	public ComunicacionMulticast(Comunicador comunicador) {
		conectado = false;
		this.comunicador = comunicador;
	}

	public void inicializar() {
		turno = 0;
		//per = new ArrayList<Personas>();

		try {
			if (!conectado) {
				this.PUERTO = 5000;
				// 224.0.0.0 to 239.255.255.255
				this.grupo = InetAddress.getByName("224.0.0.0");
				myIp = InetAddress.getLocalHost().getHostAddress();

				socket = new MulticastSocket(PUERTO);

				socket.joinGroup(grupo);

				conectado = true;

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		inicializar();

		while (true) {
			try {
	
				recibir();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void enviar(final String mensaje) {
		new Thread(new Runnable() {

			public void run() {

				DatagramPacket datagramPacket = new DatagramPacket(mensaje.getBytes(), mensaje.length(), grupo, PUERTO);

				try {
					socket.send(datagramPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	public void recibir() throws IOException {
		byte[] capacidadAlmacenamiento = new byte[500];
		DatagramPacket datagramaPacket = new DatagramPacket(capacidadAlmacenamiento, capacidadAlmacenamiento.length);

		socket.receive(datagramaPacket);

		String mensajeRecibido = new String(datagramaPacket.getData()).trim();
		System.out.println(mensajeRecibido);
		validar(mensajeRecibido);

	}

	private void validar(String mensajeRecibido) {
/*
		if (conectado == true) {

			if (mensajeRecibido.contains("Myid:")) {
				
				String[] separa = mensajeRecibido.split(":");
				String uid = separa[1];
				String usuario = separa[2];
				
			}

		}*/
		
		comunicador.Receptor(mensajeRecibido);

		
		
	

	}

	// ---------------------------------------------------------

	int ID;
	boolean Identificado;

	public void autoId() {
		ID = -1;

		enviar("Identifiquense");

		try {
			socket.setSoTimeout(500);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		while (Identificado == false) {

			try {
				recibir();
			} catch (IOException e) {

				Identificado = true;

				try {
					socket.setSoTimeout(0);
				} catch (SocketException e1) {
					e.printStackTrace();
				}

				if (ID == -1) {
					ID = 0;

				}

				System.out.println("My ID ES: " + ID);
				enviar("Ultimo:" + ID);
			}

		}
	}

	public int getID() {
		return ID;
	}

	public int getMAXID() {
		return maxId;
	}

}