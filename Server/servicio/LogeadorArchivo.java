package servicio;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LogeadorArchivo implements ILogeador {

    protected Map<UUID,StringBuilder> sesiones;
    protected String logUbicacion;
    BufferedWriter bw;

    public LogeadorArchivo(String logUbicacion) {
        this.logUbicacion = logUbicacion;
        this.sesiones = new HashMap<UUID, StringBuilder>();
        try {
            this.bw = new BufferedWriter(new FileWriter(this.logUbicacion));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info(String mensaje) {
        try {
            this.bw.write(this.getFechaAhora()+" - INFO - " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void error(String mensaje) {
        try {
            this.bw.write(this.getFechaAhora()+" - ERROR - " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UUID crearSesion() {
        StringBuilder sb = new StringBuilder();
        UUID sesion = UUID.randomUUID();
        this.sesiones.put(sesion, sb);
        return sesion;
    }

    public void bloqueInfo(UUID sesion, String mensaje) {
        StringBuilder sb = this.sesiones.get(sesion);
        sb.append(this.getFechaAhora()+" - INFO - "+mensaje+"\n");
    }

    public void bloqueError(UUID sesion, String mensaje) {
        StringBuilder sb = this.sesiones.get(sesion);
        sb.append(this.getFechaAhora()+" - ERROR - "+mensaje+"\n");

    }

    public void publicarSesion(UUID sesion) {
        StringBuilder sb = this.sesiones.get(sesion);
        try {
            this.bw.write(sb.toString());
            this.bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sesiones.remove(sesion);
    }

    private String getFechaAhora() {
        String patron = "YYYY-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        String fecha = sdf.format(new Date());
        return fecha;
    }

}