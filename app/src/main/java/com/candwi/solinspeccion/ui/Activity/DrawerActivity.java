package com.candwi.solinspeccion.ui.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.candwi.solinspeccion.R;
import com.candwi.solinspeccion.entidad.Usuario;
import com.candwi.solinspeccion.libreria.Constantes;
import com.candwi.solinspeccion.ui.Custom.GeneralDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class DrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        GeneralDialogFragment.OnDialogFragmentClickListener {

    protected LinearLayout fullLayout;
    protected FrameLayout actContent;
    private Usuario mUsuario;
    private TextView tvUsuario, tvNombresCompletos;
    private GeneralDialogFragment mCustomPd;
    NavigationView navigationView;


    private OnDialogFragmentClickListenerDrawerActivity listener;

    public void setListener(OnDialogFragmentClickListenerDrawerActivity listener) {
        this.listener = listener;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void setContentView(final int layoutResID) {
        fullLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_drawer, null);
        actContent = (FrameLayout) fullLayout.findViewById(R.id.llact_content);
        Toolbar toolbar = (Toolbar) fullLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getLayoutInflater().inflate(layoutResID, actContent, true);
        super.setContentView(fullLayout);

        mUsuario = new Usuario();
        mUsuario = (Usuario) getIntent().getSerializableExtra(Constantes.INTENT_USUARIO);

        String SinDatos = getIntent().getStringExtra(Constantes.SIN_DATOS);

        DrawerLayout drawer = (DrawerLayout) fullLayout.findViewById(R.id.lldrawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        tvUsuario = headerView.findViewById(R.id.tvUsuarioMenuH);
        tvNombresCompletos = headerView.findViewById(R.id.tvNombresCompletosMenuH);
        navigationView.setNavigationItemSelectedListener(this);
        tvUsuario.setText(mUsuario.getC_Usuario());
        tvNombresCompletos.setText(mUsuario.getC_NombreCompleto());

    }


    private void AlertaCerrarSesion() {
        CrearMensajeGeneral("CerrarSesion", "CerrarSesion", "¿Está seguro que desea cerrar sesión?", "SI", "NO", 0, null, false);
    }

    private void AlertaRestaurar() {
        CrearMensajeGeneral("Restaurar", "Restaurar", "¿Está seguro que desea restaurar el teléfono?\n Se perderán todos los datos", "SI", "NO", 0, null, false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.lldrawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertaCerrarSesion();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        try {
            int id = item.getItemId();

            switch (id) {
                case R.id.nav_modulo:
                    System.out.println("Accion 1");
                    break;
                case R.id.nav_CerrarSesion:
                    AlertaCerrarSesion();
                    break;
            }
            DrawerLayout auxdrawer = (DrawerLayout) findViewById(R.id.lldrawer_layout);
            auxdrawer.closeDrawer(GravityCompat.START);
        } catch (Exception ex) {
            String msj = ex.getMessage();
        }
        return true;
    }


    protected void snack(String mensaje) {
        Snackbar snackbar = Snackbar
                .make(tvUsuario, mensaje, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.RED);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        snackbar.show();
    }

    protected void manejadorErrorVolley(VolleyError error, String TAG, View v) {
        String msj = "Ocurrio un error al descargar.";
        if (error instanceof NoConnectionError) {
            msj = "Habilite  el WiFi o active los datos del teléfono.";
        } else if (error instanceof TimeoutError) {
            msj = "El tiempo transcurrido ha superado el límite permitido.";
        } else if (error instanceof ServerError) {
            msj = "Error del servidor";
        } else if (error instanceof NetworkError) {
            msj = "No tiene conexión a internet. Por favor verifique si conexión.";
        } else if (error instanceof ParseError) {
            msj = "La respuesta del servidor no puede ser analizada";
        }
        Log.d(TAG, "onErrorResponse");
        snackBaseError(msj, v);
    }


    @Override
    public void onOkClicked(GeneralDialogFragment dialog, int recurso_btn, Object obj) {
        String tag = dialog.getTag();
        if (tag.equals("Restaurar") || tag.equals("CerrarSesion")) {
            switch (tag) {
                case "Restaurar":
                    ResturarAplicacion();
                    break;
            }
            CerrarSesion();
        } else {
            listener.onOkDrawerClicked(dialog, recurso_btn, obj);
        }

    }

    @Override
    public void onCancelClicked(GeneralDialogFragment dialog) {
        String tag = dialog.getTag();
        if (tag.equals("Restaurar") || tag.equals("CerrarSesion")) {
            dialog.dismiss();
        } else {
            listener.onCancelDrawerClicked(dialog);
        }
    }

    public interface OnDialogFragmentClickListenerDrawerActivity {
        public void onOkDrawerClicked(GeneralDialogFragment dialog, int recurso_btn, Object object);

        public void onCancelDrawerClicked(GeneralDialogFragment dialog);
    }
}
