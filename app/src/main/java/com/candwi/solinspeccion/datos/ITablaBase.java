package com.candwi.solinspeccion.datos;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by luisr on 07/03/2018.
 */

public interface ITablaBase <T> {
       public ContentValues setValues(T entidad);
    public T getValues(Cursor c);
}

