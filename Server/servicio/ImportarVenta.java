package servicio;

import entidades.TarjetaCredito;
import entidades.Venta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImportarVenta implements Runnable{

    protected String archivo;
    protected ILogeador log;
    protected AlmacenVentas almacen;

    public ImportarVenta() {}

    public ImportarVenta(String archivo, ILogeador log, AlmacenVentas almacen) {
        this.archivo = archivo;
        this.log = log;
        this.almacen = almacen;
    }

    @SuppressWarnings("resource")
	public void importarVentas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                log.info("Recuperado del archivo "+linea);
                String[] datos = linea.split(",");
                TarjetaCredito tarjeta = new TarjetaCredito(datos[0],datos[1],datos[2],datos[3]);
                try {
                	tarjeta.chequearLargo();
                } catch (LargoIncorrectoDeTarjeta l) {
                	continue;
                }
                Venta venta = new Venta(datos[4], Float.valueOf(datos[5]), tarjeta);
                almacen.push(venta);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("asd");
        almacen.setseLlegoAFinalDeArchivo(true);
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public ILogeador getLog() {
        return log;
    }

    public void setLog(ILogeador log) {
        this.log = log;
    }

	public void run() {
		importarVentas();
	}
}