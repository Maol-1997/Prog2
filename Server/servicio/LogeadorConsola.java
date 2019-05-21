package servicio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LogeadorConsola implements ILogeador{

    protected Map<UUID,StringBuilder> sesiones;

    public LogeadorConsola() {
        this.sesiones = new HashMap<UUID, StringBuilder>();
    }

    public void info(String mensaje) {
        System.out.println(this.getFechaAhora()+" - INFO - " + mensaje);
    }

    public void error(String mensaje) {
        System.out.println(this.getFechaAhora()+" - ERROR - " + mensaje);
    }

    public UUID crearSesion() {
        StringBuilder sb = new StringBuilder();
        UUID sesion = UUID.randomUUID();
        this.sesiones.put(sesion, sb);
        return sesion;
    }

    public void bloqueInfo(UUID sesion, String mensaje) {
        StringBuilder sb = this.sesiones.get(sesion);
        if(sb == null) {
        	System.out.println("asdddd");
        }
        sb.append(this.getFechaAhora()+" - INFO - "+mensaje+"\n");
    }

    public void bloqueError(UUID sesion, String mensaje) {
        StringBuilder sb = this.sesiones.get(sesion);
        sb.append(this.getFechaAhora()+" - ERROR - "+mensaje+"\n");

    }

    public void publicarSesion(UUID sesion) {
        StringBuilder sb = this.sesiones.get(sesion);
        System.out.println(sb.toString());
        this.sesiones.remove(sesion);
    }

    private String getFechaAhora() {
        String patron = "YYYY-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        String fecha = sdf.format(new Date());
        return fecha;
    }
}
