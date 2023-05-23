package com.candwi.solinspeccion.ui.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.candwi.solinspeccion.R;
import com.candwi.solinspeccion.datos.SQLiteHeper;
import com.candwi.solinspeccion.entidad.Auxiliares.UsuarioRequest;
import com.candwi.solinspeccion.entidad.Basicas.MensajeGeneric;
import com.candwi.solinspeccion.entidad.Usuario;
import com.candwi.solinspeccion.libreria.Constantes;
import com.candwi.solinspeccion.libreria.Encriptamiento;
import com.candwi.solinspeccion.libreria.General;
import com.candwi.solinspeccion.entidad.Auxiliares.lista.Usuarios;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    private SQLiteHeper mSqlHelper = new SQLiteHeper(this);

    private Toolbar toolbarLogin;
    General mGen = new General();
    Usuario mUsuario = new Usuario();
    String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private UserLoginTask mAuthTask = null;
    private EditText mEmailView;
    private EditText mPasswordView;
    private ProgressBar pbload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbarLogin = findViewById(R.id.toolbar);
        toolbarLogin.setTitle("Sol.Redes");
        setSupportActionBar(toolbarLogin);
        //  getSupportActionBar().setDisplayShowTitleEnabled(false);
        pbload = findViewById(R.id.pbload);
        pbload.setVisibility(View.GONE);
        mEmailView = findViewById(R.id.edtuser);
        mUsuario = null;
        mPasswordView = findViewById(R.id.edtpassword);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        setUsuario();
        CheckearPermisos();
        if (mSqlHelper.leerUsuario("").size() == 0) {
            downloadUsuarios();
        }
    }

    private boolean CheckearPermisos() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), Constantes.PERMISSIONS_REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == Constantes.PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            }
            downloadUsuarios();
            return;
        }
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, MensajeGeneric<Usuario>> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }


        @Override
        protected MensajeGeneric<Usuario> doInBackground(Void... params) {
            MensajeGeneric<Usuario> rpta = new MensajeGeneric<>();
            Usuarios usuarios = mSqlHelper.leerUsuario("");
            if (usuarios.size() > 0) {
                if (mSqlHelper.ExisteUsuario(mEmailView.getText().toString())) {
                    Usuario usuario = ValidarContrasenia();
                    if (usuario != null) {
                        rpta.setFlag(true);
                        rpta.setObj(usuario);

                        Boolean flag = true;
                        usuario.setB_Recordar(flag);
                        usuario.setC_PasswordRecordar(mPasswordView.getText().toString());
                        mSqlHelper.actualizaLoginNoRepetir();
                        mSqlHelper.actualizaLogin(usuario);
                    } else {
                        rpta.setFlag(false);
                        rpta.setMsj(getString(R.string.error_incorrect_password));
                        rpta.setRecurso(R.string.error_incorrect_password);
                    }
                } else {
                    rpta.setFlag(false);
                    rpta.setMsj(getString(R.string.error_usuario_no_existe));
                    rpta.setRecurso(R.string.error_usuario_no_existe);
                }
            } else {
                rpta.setFlag(false);
                rpta.setMsj(getString(R.string.error_descarga_usuarios));
                rpta.setRecurso(R.string.error_descarga_usuarios);

            }

            return rpta;
        }

        private Usuario ValidarContrasenia() {
            String User = mEmailView.getText().toString();
            String Pass = mPasswordView.getText().toString();
            Usuario usuario = new Usuario();
            try {
                int Id = 0;
                Pass = new Encriptamiento().md5_1(Pass);
                usuario.setC_Usuario(User);
                usuario.setC_PasswordMovil(Pass);
                usuario = mSqlHelper.leerLoginByUserPass(usuario);
            } catch (Exception e) {
                snackBase(getResources().getString(R.string.Error) + e.getMessage(), mPasswordView, R.color.colorError);
            }
            return usuario;
        }

        @Override
        protected void onPostExecute(MensajeGeneric<Usuario> rpta) {
            mAuthTask = null;

            if (!rpta.isFlag()) {
                switch (rpta.getRecurso()) {
                    case R.string.error_descarga_usuarios:
                        snackBase(rpta.getMsj(), mEmailView, R.color.colorError);
                        break;
                    case R.string.error_incorrect_password:
                        mPasswordView.setText("");
                        mPasswordView.setError(rpta.getMsj());
                        mPasswordView.requestFocus();
                        break;
                    case R.string.error_usuario_no_existe:
                        mEmailView.setText("");
                        mEmailView.setError(rpta.getMsj());
                        mEmailView.requestFocus();
                        break;
                }
            } else {
                Intent intent;

                intent = new Intent(LoginActivity.this, ModuloActivity.class);// ProyectoActivity.class);
                intent.putExtra(Constantes.SIN_DATOS, "Sin Datos");

                intent.putExtra(Constantes.INTENT_USUARIO, rpta.getObj());
                startActivity(intent);
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_descarga) {
            pbload.setVisibility(View.VISIBLE);
            downloadUsuarios();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void downloadUsuarios() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constantes.SERVICE_LISTUSUARIO;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String data) {
                        Log.d(TAG, "onResponse");
                        Gson g = new Gson();
                        UsuarioRequest response = g.fromJson(data, UsuarioRequest.class);
                        mSqlHelper.eliminarUsuario();
                        //mSqlHelper.eliminarProyecto("");
                        Usuario usuario = null;
                        int i = 0;
                        while (response.getUsuarios().size() > i) {
                            usuario = new Usuario();
                            usuario = response.getUsuarios().get(i);
                            usuario.setC_Nombre2("");
                            usuario.setB_Recordar(false);
                            usuario.setC_PasswordRecordar("");
                            String c_codigo = String.valueOf(usuario.getN_ID_Usuario()) + "_" + mGen.fechaActual()
                                    .replace("-", "")
                                    .replace("/", "")
                                    .replace(" ", "")
                                    .replace(".", "")
                                    .replace(":", "")
                                    .replace("PM", "")
                                    .replace("AM", "");

                            usuario.setC_codigo(c_codigo);
                            mSqlHelper.insertarLogin(usuario);
                            i++;
                        }

                        //mSqlHelper.insertarAllProyecto(response.getProyectos());

                        snackBase("La descarga se realizó correctamente", mPasswordView, R.color.colorDone);
                        pbload.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pbload.setVisibility(View.GONE);
                manejadorErrorVolley(error);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);


    }

    private void manejadorErrorVolley(VolleyError error) {
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
        snackBase(msj, mPasswordView, R.color.colorError);
    }


    private void setUsuario() {
        boolean chkBool;
        Usuario usuario = mSqlHelper.leerUsuarioRecordado();
        if (usuario != null) {
            mEmailView.setText(usuario.getC_Usuario());
            chkBool = usuario.getB_Recordar();
            mPasswordView.setText(usuario.getC_PasswordRecordar());
        }
    }
}

