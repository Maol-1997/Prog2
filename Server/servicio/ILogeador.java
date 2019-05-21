package servicio;

import java.util.UUID;

public interface ILogeador {
    public void info(String mensaje);
    public void error(String mensaje);
    public UUID crearSesion();
    public void bloqueInfo(UUID sesion, String mensaje);
    public void bloqueError(UUID sesion, String mensaje);
    public void publicarSesion(UUID sesion);
}
