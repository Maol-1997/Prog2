package servicio;

import java.io.IOException;

public interface IProcesador {
    public boolean verificarTarjeta() throws IOException;
    public boolean autorizar() throws IOException;
    public boolean capturar();
    public boolean finalizar();
}
