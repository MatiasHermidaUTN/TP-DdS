package ar.edu.utn.frba.dds.models.validador;

public class Resultado {
    private boolean valor;
    private String mensajeError;

    public boolean isValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Resultado(boolean valor, String mensajeError) {
        this.valor = valor;
        this.mensajeError = mensajeError;
    }
}
