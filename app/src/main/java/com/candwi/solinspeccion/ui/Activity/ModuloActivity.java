package com.candwi.solinspeccion.ui.Activity;

import android.os.Bundle;

import com.candwi.solinspeccion.R;
import com.candwi.solinspeccion.entidad.Usuario;
import com.candwi.solinspeccion.libreria.Constantes;

public class ModuloActivity extends DrawerActivity{

    private Usuario mUsuario;
    private String mSinDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo);
        mUsuario = (Usuario) getIntent().getSerializableExtra(Constantes.INTENT_USUARIO);
        mSinDatos = getIntent().getStringExtra(Constantes.SIN_DATOS);

    }
}