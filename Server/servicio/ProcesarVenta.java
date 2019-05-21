package servicio;

import java.io.IOException;

import entidades.Venta;

public class ProcesarVenta implements Runnable {
	protected AlmacenVentas almacen;
	protected ILogeador log;

	public ProcesarVenta() {
	}

	public ProcesarVenta(AlmacenVentas almacen, ILogeador log) {
		this.almacen = almacen;
		this.log = log;
	}

	public void procesarTodo() throws IOException {
		IProcesador procesador;
		boolean verificarT = false;
		boolean autorizar = false;
		this.log.info("Se comienza a procesar todo");
		while (true) {
			if (!almacen.estaVacio()) {
				Venta venta = almacen.pop();
				procesador = ProcesadorFactory.getProcesador(venta, this.log);
				verificarT = procesador.verificarTarjeta();
				if (verificarT) {
					autorizar = procesador.autorizar();
				}
				if (verificarT && autorizar) {
					procesador.capturar();
					procesador.finalizar();
					if (venta.getSocket() != null) // verifica que sea online
						RespOnline.ok(venta.getSocket()); // responde el ok
				}
				// venta.getSocket().close();
				/*
				 * if(almacen.getseLlegoAFinalDeArchivo()) { break; }
				 */
			}
		}
		// this.log.info("Finaliza el procesamiento de todo");
	}

	public AlmacenVentas getAlmacen() {
		return almacen;
	}

	public void setAlmacen(AlmacenVentas almacen) {
		this.almacen = almacen;
	}

	public ILogeador getLog() {
		return log;
	}

	public void setLog(ILogeador log) {
		this.log = log;
	}

	public void run() {
		try {
			procesarTodo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
