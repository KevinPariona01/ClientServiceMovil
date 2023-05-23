package com.candwi.solinspeccion.entidad;

import java.io.Serializable;

public class BaseEntidad implements Serializable {
    private int id_pl_estructura;
    private int id_actividad;
    private String c_nombre;
    private String c_observacion;
    private Integer n_fotospendientes;
    private int n_estado;
    private String c_latitud;
    private String c_longitud;
    private float n_presicion;
    private float n_altitud;
    private String d_fecha;
    private Integer n_idseg_usuario;
    private boolean b_envio;
    private String c_mensajeenvio;
    private String c_mensajeFotos;

    public BaseEntidad(String c_nombre) {
        this.c_nombre = c_nombre;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public int getId_pl_estructura() {
        return id_pl_estructura;
    }

    public void setId_pl_estructura(int id_pl_estructura) {
        this.id_pl_estructura = id_pl_estructura;
    }



    public String getC_nombre() {
        return c_nombre;
    }

    public void setC_nombre(String c_nombre) {
        this.c_nombre = c_nombre;
    }

    public String getC_observacion() {
        return c_observacion;
    }

    public void setC_observacion(String c_observacion) {
        this.c_observacion = c_observacion;
    }

    public Integer getN_fotospendientes() {
        return n_fotospendientes;
    }

    public void setN_fotospendientes(Integer n_fotospendientes) {
        this.n_fotospendientes = n_fotospendientes;
    }

    public int getN_estado() {
        return n_estado;
    }

    public void setN_estado(int n_estado) {
        this.n_estado = n_estado;
    }

    public String getC_latitud() {
        return c_latitud;
    }

    public void setC_latitud(String c_latitud) {
        this.c_latitud = c_latitud;
    }

    public String getC_longitud() {
        return c_longitud;
    }

    public void setC_longitud(String c_longitud) {
        this.c_longitud = c_longitud;
    }

    public float getN_presicion() {
        return n_presicion;
    }

    public void setN_presicion(float n_presicion) {
        this.n_presicion = n_presicion;
    }

    public float getN_altitud() {
        return n_altitud;
    }

    public void setN_altitud(float n_altitud) {
        this.n_altitud = n_altitud;
    }

    public String getD_fecha() {
        return d_fecha;
    }

    public void setD_fecha(String d_fecha) {
        this.d_fecha = d_fecha;
    }

    public Integer getN_idseg_usuario() {
        return n_idseg_usuario;
    }

    public void setN_idseg_usuario(Integer n_idseg_usuario) {
        this.n_idseg_usuario = n_idseg_usuario;
    }

    public boolean isB_envio() {
        return b_envio;
    }

    public void setB_envio(boolean b_envio) {
        this.b_envio = b_envio;
    }

    public String getC_mensajeenvio() {
        return c_mensajeenvio;
    }

    public void setC_mensajeenvio(String c_mensajeenvio) {
        this.c_mensajeenvio = c_mensajeenvio;
    }

    public String getC_mensajeFotos() {
        return c_mensajeFotos;
    }

    public void setC_mensajeFotos(String c_mensajeFotos) {
        this.c_mensajeFotos = c_mensajeFotos;
    }

}
