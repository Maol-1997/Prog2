package entidades;

import java.net.Socket;

public class Venta {
	protected String descripcion;
	protected float monto;
	protected TarjetaCredito tarjeta;
	protected Socket socket;

	public Venta() {
	}

	public Venta(String descripcion, float monto, TarjetaCredito tarjeta, Socket socket) {
		this.descripcion = descripcion;
		this.monto = monto;
		this.tarjeta = tarjeta;
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Venta(String descripcion, float monto, TarjetaCredito tarjeta) {
		this.descripcion = descripcion;
		this.monto = monto;
		this.tarjeta = tarjeta;
		this.socket = null;
	}

	@Override
	public String toString() {
		return "Venta{" + "descripcion='" + descripcion + '\'' + ", monto=" + monto + ", tarjeta=" + tarjeta + '}';
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public TarjetaCredito getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaCredito tarjeta) {
		this.tarjeta = tarjeta;
	}
}
