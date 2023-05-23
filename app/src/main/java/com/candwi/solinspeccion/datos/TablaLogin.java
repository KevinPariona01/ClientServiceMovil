package com.candwi.solinspeccion.datos;

import android.content.ContentValues;
import android.database.Cursor;

import com.candwi.solinspeccion.entidad.Usuario;


public class TablaLogin implements ITablaBase<Usuario> {
    public static String TABLA = "LOGIN";
    public static String N_ID_Usuario = "N_ID_Usuario";
    public static String C_Usuario = "C_Usuario";
    public static String C_Nombre1 = "C_Nombre1";
    public static String C_Nombre2 = "C_Nombre2";
    public static String C_ApPaterno = "C_ApPaterno";
    public static String C_ApMaterno = "C_ApMaterno";
    public static String C_PasswordMovil = "C_PasswordMovil";
    public static String C_PasswordRecordar = "C_PasswordRecordar";
    public static String C_NombreCompleto = "C_NombreCompleto";
    public static String B_Recordar = "B_Recordar";
    public static String C_Codigo = "C_Codigo";




    public static String CAMPOS[] = {N_ID_Usuario, C_Usuario, C_Nombre1, C_Nombre2, C_ApPaterno, C_ApMaterno, C_PasswordMovil, C_PasswordRecordar, C_NombreCompleto, B_Recordar,C_Codigo};

    @Override
    public ContentValues setValues(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put(N_ID_Usuario, usuario.getN_ID_Usuario());
        valores.put(C_Usuario, usuario.getC_Usuario());
        valores.put(C_Nombre1, usuario.getC_Nombre1());
        valores.put(C_Nombre2, usuario.getC_Nombre2());
        valores.put(C_ApPaterno, usuario.getC_ApPaterno());
        valores.put(C_ApMaterno, usuario.getC_ApMaterno());
        valores.put(C_PasswordMovil, usuario.getC_PasswordMovil());
        valores.put(C_PasswordRecordar, usuario.getC_PasswordRecordar());
        valores.put(C_NombreCompleto, usuario.getC_NombreCompleto());
        valores.put(B_Recordar, usuario.getB_Recordar());
        valores.put(C_Codigo, usuario.getC_codigo());

        return valores;
    }

    public Usuario getValues(Cursor c) {
        Usuario usuario = new Usuario();
        try {

            usuario.setN_ID_Usuario(c.getInt(c.getColumnIndex(N_ID_Usuario)));
            usuario.setC_codigo(c.getString(c.getColumnIndex(C_Codigo)));
            usuario.setC_Usuario(c.getString(c.getColumnIndex(C_Usuario)));
            usuario.setC_Nombre1(c.getString(c.getColumnIndex(C_Nombre1)));
            usuario.setC_Nombre2(c.getString(c.getColumnIndex(C_Nombre2)));
            usuario.setC_ApPaterno(c.getString(c.getColumnIndex(C_ApPaterno)));
            usuario.setC_ApMaterno(c.getString(c.getColumnIndex(C_ApMaterno)));
            usuario.setC_PasswordMovil(c.getString(c.getColumnIndex(C_PasswordMovil)));
            usuario.setC_PasswordRecordar(c.getString(c.getColumnIndex(C_PasswordRecordar)));
            usuario.setC_NombreCompleto(c.getString(c.getColumnIndex(C_NombreCompleto)));
            usuario.setB_Recordar(c.getString(c.getColumnIndex(B_Recordar)).equals("1") ? true : false);
        } catch (Exception e) {
            usuario = null;
        }
        return usuario;
    }
}
