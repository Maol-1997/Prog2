package principal;

import servicio.*;

public class Arranque {
    public static void main(String[] args) {
        Arranque a = new Arranque();
        a.arrancar();
    }

    public void arrancar() {
        ILogeador log = new LogeadorConsola();
        //ILogeador log = new LogeadorArchivo("log.txt");
        log.info("Arrancó la aplicación");
        AlmacenVentas almacen = new AlmacenVentas(log);
        String datos = "ventas.txt";
        ImportarVenta imp = new ImportarVenta(datos, log, almacen);
        //imp.importarVentas(almacen);
        Thread t1 = new Thread(imp, "Hilo importador de ventas");
        t1.start();
        ProcesarVenta procesar = new ProcesarVenta(almacen,log);
        //procesar.procesarTodo();
        Thread t2 = new Thread(procesar, "Hilo procesador de ventas");
        t2.start();
        ServidorSocket serverSocket = new ServidorSocket(log, almacen);
        Thread threadServidorSocket = new Thread(serverSocket, "Hilo servidor socket");
        threadServidorSocket.start();
        log.info("Terminó la aplicación");
    }
}