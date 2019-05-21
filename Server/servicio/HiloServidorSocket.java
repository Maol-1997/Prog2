package servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import entidades.TarjetaCredito;
import entidades.Venta;

public class HiloServidorSocket implements Runnable {
	private Socket socketCliente;
	private ILogeador log;
	private AlmacenVentas almacen;

	public void run() {
		try {
			BufferedReader entrada;
			String linea;
			// pedir nuevo socket infinitamente
			while ((entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()))) != null) {
				Thread.sleep(100);
				// --------------------------------
				while ((linea = entrada.readLine()) != null) {
					log.info("Recibido de internet " + linea);
					String[] datos = linea.split(",");
					TarjetaCredito tarjeta = new TarjetaCredito(datos[0], datos[1], datos[2], datos[3]);
					Venta venta = new Venta(datos[4], Float.valueOf(datos[5]), tarjeta, socketCliente);
					almacen.push(venta);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HiloServidorSocket(Socket socketCliente, ILogeador log, AlmacenVentas almacen) {
		this.socketCliente = socketCliente;
		this.log = log;
		this.almacen = almacen;
	}
}
