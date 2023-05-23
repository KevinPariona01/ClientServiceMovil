package com.candwi.solinspeccion.libreria;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.candwi.solinspeccion.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by luisr on 07/03/2018.
 */

public class General extends Application {
    private static char[] hexDigits = "0123456789abcdef".toCharArray();



    public String fechaActual() {
        Calendar c = Calendar.getInstance();

        //  SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String Fecha = df3.format(c.getTime());

        //Fecha = Fecha.replace(" ", "X");
        Fecha = Fecha.replace(".", "");
        Fecha = Fecha.toUpperCase();
        return Fecha;
    }

    public static String md5(InputStream is) throws IOException {
        String md5 = "";

        try {
            byte[] bytes = new byte[4096];
            int read = 0;
            MessageDigest digest = MessageDigest.getInstance("MD5");

            while ((read = is.read(bytes)) != -1) {
                digest.update(bytes, 0, read);
            }

            byte[] messageDigest = digest.digest();

            StringBuilder sb = new StringBuilder(32);

            for (byte b : messageDigest) {
                sb.append(hexDigits[(b >> 4) & 0x0f]);
                sb.append(hexDigits[b & 0x0f]);
            }

            md5 = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return md5;
    }
}
