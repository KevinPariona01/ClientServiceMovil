package com.candwi.solinspeccion.entidad.Basicas;

/**
 * Created by luisr on 07/03/2018.
 */

public class Combo {
    private int id;
    private String name;

    /*Aux_Apoyo*/
    private boolean isChecked;
    private boolean isActivo;


    public boolean isActivo() {
        return isActivo;
    }

    public void setActivo(boolean activo) {
        isActivo = activo;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Combo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Combo() {
        this.id = 0;
        this.name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Combo(int id, String name, boolean isChecked, boolean isActivo) {
        this.id = id;
        this.name = name;
        this.isChecked = isChecked;
        this.isActivo = isActivo;
    }
}
