package com.candwi.solinspeccion.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.candwi.solinspeccion.R;
import com.candwi.solinspeccion.datos.SQLiteHeper;
import com.candwi.solinspeccion.ui.Custom.GeneralDialogFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

public class BaseActivity extends AppCompatActivity {
    private SQLiteHeper mSqLiteHeper = new SQLiteHeper(this);

    public <T extends Serializable> GeneralDialogFragment CrearMensajeGeneral(String tag, String tipo, String mensaje, String btnSI, String btnNO, int recurso_btn, T obj, boolean cancelable) {
        GeneralDialogFragment generalDialogFragment =
                GeneralDialogFragment.newInstance(tipo, mensaje, btnSI, btnNO, recurso_btn, obj, cancelable);
        generalDialogFragment.show(getFragmentManager(), tag);
        return generalDialogFragment;
    }

    public void snackBase(String mensaje, View view, int color) {

        Snackbar snackbar = Snackbar
                .make(view, mensaje, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void snackBase(String mensaje, View view) {
        snackBase(mensaje, view, R.color.colorPrimary);
    }

    public void snackBaseDone(String mensaje, View view) {
        snackBase(mensaje, view, R.color.colorDone);
    }

    public void snackBaseError(String mensaje, View view) {
        snackBase(mensaje, view, R.color.colorError);
    }

    public void toastBase(String mensaje, int duracion) {
        Toast.makeText(this, mensaje, duracion).show();
    }

    public BitmapDescriptor bitmapDescriptorFromVector2(Context context, @DrawableRes int vectorDrawableResourceId) {
        Drawable background = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public void CerrarSesion() {
        Intent intent = new Intent(getApplicationContext()
                , LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void ResturarAplicacion() {
        mSqLiteHeper.eliminarUsuario();
    }
}
