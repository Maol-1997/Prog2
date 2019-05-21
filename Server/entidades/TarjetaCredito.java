package entidades;

import servicio.LargoIncorrectoDeTarjeta;

public class TarjetaCredito {
    protected String persona;
    protected String cc;
    protected String cvv;
    protected String tipo;

    public TarjetaCredito() {}

    public TarjetaCredito(String persona, String cc, String cvv, String tipo) {
        this.persona = persona;
        this.cc = cc;
        this.cvv = cvv;
        this.tipo = tipo;
    }

    public String getCCEnmascarada() {
        int tarjetaLargo = this.cc.length();
        @SuppressWarnings("unused")
		String mascara = "X";
        String enmascarado = "XXXXXXXXXXXX"+this.cc.substring(tarjetaLargo-4);
        return enmascarado;
    }

    @Override
    public String toString() {
        return "TarjetaCredito{" +
                "persona='" + persona + '\'' +
                ", cc='" + this.getCCEnmascarada() + '\'' +
                ", cvv='" + cvv + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public void chequearLargo() throws LargoIncorrectoDeTarjeta{
    	if(cc.length() > 16 || cc.length() < 15)
    		throw new LargoIncorrectoDeTarjeta();
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
