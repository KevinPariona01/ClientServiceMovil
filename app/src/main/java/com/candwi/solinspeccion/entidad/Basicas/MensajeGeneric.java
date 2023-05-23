package com.candwi.solinspeccion.entidad.Basicas;

/**
 * Created by luisr on 07/03/2018.
 */

public class MensajeGeneric<T> {
    private T obj;
    private String msj;
    private boolean flag;
    private int recurso;

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}