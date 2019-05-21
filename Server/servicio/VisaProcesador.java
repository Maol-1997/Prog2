package servicio;

import entidades.Venta;

import java.io.IOException;
import java.util.UUID;

public class VisaProcesador implements IProcesador {

	protected Venta venta;
	protected ILogeador log;
	protected UUID sesion;

	public VisaProcesador() {
	}

	public VisaProcesador(Venta venta, ILogeador log) {
		this.venta = venta;
		this.log = log;
		this.sesion = log.crearSesion();
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public boolean verificarTarjeta() throws IOException {
		boolean retorno = true;
		this.log.bloqueInfo(this.sesion, "Arrancando el proceso de verificar la tarjeta de credito VISA");
		this.log.bloqueInfo(this.sesion, "Verificando tarjeta: " + this.venta.getTarjeta().getCCEnmascarada());
		int numError = (int) (Math.random() * 10);
		if (numError == 1) {
			this.log.bloqueError(this.sesion, "Hubo un error en la verificación de la tarjeta VISA");
			this.log.bloqueInfo(this.sesion, "Finalizando proceso de venta");
			this.log.publicarSesion(this.sesion);
			if(this.venta.getSocket() != null) // verifica que sea online
				RespOnline.veriferror(this.venta.getSocket(),"Hubo un error en la verificación de la tarjeta VISA");
			retorno = false;
		} else {
			this.log.bloqueInfo(this.sesion, "Finalizada la verificación de la tarjeta VISA");
		}
		return retorno;
	}

	public boolean autorizar() throws IOException {
		boolean retorno = true;
		this.log.bloqueInfo(this.sesion, "Arrancando el proceso de autorizar la transacción VISA");
		this.log.bloqueInfo(this.sesion, "Verificando monto: " + this.venta.getMonto());
		int numError = (int) (Math.random() * 10);
		if (numError == 1) {
			this.log.bloqueError(this.sesion, "Hubo un error en la autorización de la tarjeta VISA");
			this.log.bloqueInfo(this.sesion, "Finalizando proceso de venta");
			this.log.publicarSesion(this.sesion);
			if(this.venta.getSocket() != null) // verifica que sea online
				RespOnline.auterror(this.venta.getSocket(),"Hubo un error en la autorización de la tarjeta VISA");
			retorno = false;
		} else {
			this.log.bloqueInfo(this.sesion, "Finalizado el proceso de autorización de la tarjeta VISA");
		}
		return retorno;
	}

	public boolean capturar() {
		this.log.bloqueInfo(this.sesion, "Procesada la captura de la tarjeta VISA");
		return true;
	}

	public boolean finalizar() {
		this.log.bloqueInfo(this.sesion, "Finalizado el proceso de venta de la tarjeta VISA");
		this.log.publicarSesion(this.sesion);
		return true;
	}
}
