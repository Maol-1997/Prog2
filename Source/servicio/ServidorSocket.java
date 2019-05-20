package servicio;

import java.io.IOException;
import java.net.*;

public class ServidorSocket implements Runnable{
	private ILogeador log;
	private AlmacenVentas almacen;
	
	public void run() {
		ServerSocket serverSocket;
		Socket socketCliente;
		try {
			serverSocket = new ServerSocket(6666);
			while((socketCliente = serverSocket.accept()) != null) {
				HiloServidorSocket h = new HiloServidorSocket(socketCliente, log, almacen);
				Thread t = new Thread(h, "");
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ServidorSocket(ILogeador log, AlmacenVentas almacen) {
		this.log = log;
		this.almacen = almacen;
	}
}
