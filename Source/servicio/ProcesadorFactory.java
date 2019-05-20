package servicio;

import entidades.Venta;

public class ProcesadorFactory {
    public static IProcesador getProcesador(Venta venta, ILogeador log) {
        if(venta.getTarjeta().getTipo().equals("VISA")) {
            return new VisaProcesador(venta, log);
        }
        if(venta.getTarjeta().getTipo().equals("MC")) {
            return new MasterCardProcesador(venta, log);
        }
    return null;
    }
}
