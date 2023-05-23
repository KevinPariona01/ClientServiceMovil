package com.candwi.solinspeccion.entidad;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Usuario implements Serializable {
    @SerializedName("n_id_usuario")
    private Integer n_ID_Usuario;

    @SerializedName("n_idpro_proyecto")
    private Integer n_idpro_proyecto;

    @SerializedName("c_usuario")
    private String  c_Usuario;

    @SerializedName("c_nombre1")
    private String  c_Nombre1;

    @SerializedName("c_nombre2")
    private String  c_Nombre2;

    @SerializedName("c_appaterno")
    private String  c_ApPaterno;

    @SerializedName("c_apmaterno")
    private String  c_ApMaterno;

    @SerializedName("c_passwordmovil")
    private String c_PasswordMovil;
    private String c_PasswordRecordar;

    @SerializedName("c_nombrecompleto")
    private String  c_NombreCompleto;
    private Boolean b_Recordar;
    private String c_codigo;


    public String getC_codigo() {
        return c_codigo;
    }

    public void setC_codigo(String c_codigo) {
        this.c_codigo = c_codigo;
    }



    public Integer getN_ID_Usuario() {
        return n_ID_Usuario;
    }

    public void setN_ID_Usuario(Integer n_ID_Usuario) {
        this.n_ID_Usuario = n_ID_Usuario;
    }

    public String getC_Usuario() {
        return c_Usuario;
    }

    public void setC_Usuario(String c_Usuario) {
        this.c_Usuario = c_Usuario;
    }

    public String getC_Nombre1() {
        return c_Nombre1;
    }

    public void setC_Nombre1(String c_Nombre1) {
        this.c_Nombre1 = c_Nombre1;
    }

    public String getC_Nombre2() {
        return c_Nombre2;
    }

    public void setC_Nombre2(String c_Nombre2) {
        this.c_Nombre2 = c_Nombre2;
    }

    public String getC_ApPaterno() {
        return c_ApPaterno;
    }

    public void setC_ApPaterno(String c_ApPaterno) {
        this.c_ApPaterno = c_ApPaterno;
    }

    public String getC_ApMaterno() {
        return c_ApMaterno;
    }

    public void setC_ApMaterno(String c_ApMaterno) {
        this.c_ApMaterno = c_ApMaterno;
    }

    public String getC_PasswordMovil() {
        return c_PasswordMovil;
    }

    public void setC_PasswordMovil(String c_PasswordMovil) {
        this.c_PasswordMovil = c_PasswordMovil;
    }

    public String getC_PasswordRecordar() {
        return c_PasswordRecordar;
    }

    public void setC_PasswordRecordar(String c_PasswordRecordar) {
        this.c_PasswordRecordar = c_PasswordRecordar;
    }

    public String getC_NombreCompleto() {
        return c_NombreCompleto;
    }

    public void setC_NombreCompleto(String c_NombreCompleto) {
        this.c_NombreCompleto = c_NombreCompleto;
    }

    public Boolean getB_Recordar() {
        return b_Recordar;
    }

    public void setB_Recordar(Boolean b_Recordar) {
        this.b_Recordar = b_Recordar;
    }

    public Integer getN_idpro_proyecto() {
        return n_idpro_proyecto;
    }

    public void setN_idpro_proyecto(Integer n_idpro_proyecto) {
        this.n_idpro_proyecto = n_idpro_proyecto;
    }
}
