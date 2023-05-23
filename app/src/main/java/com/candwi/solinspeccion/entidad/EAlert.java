package com.candwi.solinspeccion.entidad;

import java.io.Serializable;

public class EAlert implements Serializable {
    private String titulo;
    private String mensaje;
    private int n_tipo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
