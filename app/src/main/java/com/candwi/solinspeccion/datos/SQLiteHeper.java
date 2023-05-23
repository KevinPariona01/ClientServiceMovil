package com.candwi.solinspeccion.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.candwi.solinspeccion.entidad.Usuario;
import com.candwi.solinspeccion.entidad.Auxiliares.lista.Usuarios;


public class SQLiteHeper extends SQLiteOpenHelper {
    private static final String TAG = "SQLiteHeper";
    public SQLiteDatabase db;

    public SQLiteHeper(Context ctx) {
        super(ctx, "Silec", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TLogin = "CREATE TABLE " + TablaLogin.TABLA + "(" +
                TablaLogin.N_ID_Usuario + " INT," +
                TablaLogin.C_Usuario + " VARCHAR(50)," +
                TablaLogin.C_Nombre1 + " VARCHAR(50)," +
                TablaLogin.C_Nombre2 + " VARCHAR(50)," +
                TablaLogin.C_ApPaterno + " VARCHAR(50)," +
                TablaLogin.C_ApMaterno + " VARCHAR(50)," +
                TablaLogin.C_PasswordMovil + " VARCHAR (100)," +
                TablaLogin.C_PasswordRecordar + " VARCHAR(100)," +
                TablaLogin.C_NombreCompleto + " VARCHAR(100)," +
                TablaLogin.C_Codigo + " VARCHAR(250)," +
                TablaLogin.B_Recordar + " BOOLEAN)";
        db.execSQL(TLogin);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version_ant, int version_nue) {
        db.execSQL("DROP TABLE IF EXISTS " + TablaLogin.TABLA);
        onCreate(db);
    }

    public SQLiteHeper abrir() throws SQLException {
        db = this.getWritableDatabase();
        return this;

    }

    public void cerrar() {
        db.close();
    }

    /* Usuario */
    public Usuario leerUsuarioRecordado() {
        Usuario usuario = null;
        try {
            abrir();
            Cursor c = this.getReadableDatabase().query(TablaLogin.TABLA, TablaLogin.CAMPOS, TablaLogin.B_Recordar + "=1",
                    null, null, null, null);
            c.moveToFirst();
            if (c.getCount() != 0) {
                usuario = new Usuario();
                usuario = new TablaLogin().getValues(c);
            }
        } catch (Exception e) {

        } finally {
            cerrar();
        }
        return usuario;
    }

    public void actualizaLoginNoRepetir() {
        abrir();
        ContentValues valores = new ContentValues();
        valores.put(TablaLogin.B_Recordar, false);
        this.getWritableDatabase().update(TablaLogin.TABLA, valores,
                null, null);
        cerrar();
    }

    public void actualizaLogin(Usuario usuario) {
        try {
            abrir();
            ContentValues valores = new ContentValues();
            valores.put(TablaLogin.B_Recordar, usuario.getB_Recordar());
            valores.put(TablaLogin.C_PasswordRecordar, usuario.getC_PasswordRecordar());
            this.getWritableDatabase().update(TablaLogin.TABLA, valores,
                    TablaLogin.N_ID_Usuario + " = " + usuario.getN_ID_Usuario(), null);
        } catch (Exception ex) {
            Log.e("actualizaLogin ", "" + ex.getMessage().toString());
        } finally {
            cerrar();
        }
    }

    public Usuarios leerUsuario(String Filtro) {

        Usuario entidad = null;
        Usuarios entidades = new Usuarios();
        try {
            abrir();
            Cursor c = this.getReadableDatabase().query(TablaLogin.TABLA, TablaLogin.CAMPOS, Filtro,
                    null, null, null, null);
            c.moveToFirst();
            while (c.getCount() > c.getPosition()) {
                entidad = new Usuario();
                entidad = new TablaLogin().getValues(c);
                entidades.add(entidad);
                c.moveToNext();
            }
        } catch (Exception e) {
        } finally {
            cerrar();
        }

        return entidades;
    }

    public void insertarLogin(Usuario usuario) {
        try {
            abrir();
            ContentValues valores = new ContentValues();
            valores = new TablaLogin().setValues(usuario);
            float i = this.getWritableDatabase().insert(TablaLogin.TABLA, null, valores);
            String a = "";
        } catch (Exception ex) {
            Log.e("insertarLogin ", "" + ex.getMessage().toString());
        } finally {
            cerrar();
        }
    }

    public Usuario leerLoginByUserPass(Usuario usuario) {
        Usuario usuarioResult = null;
        try {
            abrir();
            Cursor c = this.getReadableDatabase().query(TablaLogin.TABLA, TablaLogin.CAMPOS,
                    TablaLogin.C_Usuario + " ='" + usuario.getC_Usuario() + "' And " + TablaLogin.C_PasswordMovil + " ='" + usuario.getC_PasswordMovil() + "'",
                    null, null, null, null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                usuarioResult = new Usuario();
                usuarioResult = new TablaLogin().getValues(c);
            }
        } catch (Exception ex) {
            Log.e("leerLoginByUserPass ", "" + ex.getMessage().toString());
        } finally {
            cerrar();
        }
        return usuarioResult;
    }

    public void eliminarUsuario() {
        try {
            abrir();
            this.getWritableDatabase().delete(TablaLogin.TABLA, null, null);
            cerrar();
        } catch (Exception ex) {
            Log.e("eliminarUsuario ", "" + ex.getMessage().toString());
        } finally {
            cerrar();
        }
    }

    public boolean ExisteUsuario(String username) {
        boolean rpta = false;
        try {
            abrir();
            Cursor c = this.getReadableDatabase().query(TablaLogin.TABLA, TablaLogin.CAMPOS,
                    TablaLogin.C_Usuario + "='" + username + "'", null, null, null, null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                Usuario usuarioResult = new Usuario();
                usuarioResult = new TablaLogin().getValues(c);
                if (usuarioResult != null) {
                    rpta = true;
                }
            }
        } catch (Exception ex) {
            Log.d("ExisteUsuario ", "" + ex.getMessage().toString());
        } finally {
            cerrar();
        }
        return rpta;
    }


}
