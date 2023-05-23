package com.candwi.solinspeccion.ui.Custom;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.candwi.solinspeccion.R;

import java.io.Serializable;

/**
 * Created by luisr on 07/03/2018.
 */

public class GeneralDialogFragment extends BaseDialogFragment<GeneralDialogFragment.OnDialogFragmentClickListener> {
    private TextView tvMsg;

    public interface OnDialogFragmentClickListener {
        public void onOkClicked(GeneralDialogFragment dialog, int recurso_btn, Object object);

        public void onCancelClicked(GeneralDialogFragment dialog);
    }

    public static <T extends Serializable> GeneralDialogFragment newInstance(String tipo, String message,
                                                                             String btnSI, String btnNO,
                                                                             int recurso_btn, T obj,
                                                                             boolean cancelable) {
        GeneralDialogFragment frag = new GeneralDialogFragment();
        frag.setCancelable(cancelable);
        Bundle args = new Bundle();
        args.putString("tipo", tipo);
        args.putString("msg", message);
        args.putString("btnSI", btnSI);
        args.putString("btnNO", btnNO);
        args.putInt("recurso_btn", recurso_btn);
        args.putSerializable("obj", obj);
        frag.setArguments(args);
        return frag;
    }

    public TextView getTvMsg() {
        return tvMsg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String tipo = getArguments().getString("tipo");
        String msg = getArguments().getString("msg");
        String C_btnSI = getArguments().getString("btnSI");
        String C_btnNO = getArguments().getString("btnNO");
        final int recurso_btn = getArguments().getInt("recurso_btn");
        final Object obj = getArguments().getSerializable("obj");

        Dialog dialog = new Dialog(getActivity(), R.style.CustomDialog);
        if (!tipo.equals("Cargando")) {
            dialog.setContentView(R.layout.dialog_general);
            dialog.setCanceledOnTouchOutside(false);
            Button btnSI = dialog.findViewById(R.id.btn_dialogSi);
            Button btnNO = dialog.findViewById(R.id.btn_dialogNo);
            ImageView imageView = dialog.findViewById(R.id.icon_Dialog);
            tvMsg = dialog.findViewById(R.id.msg_dialog);
            TextView titulo_dialog = dialog.findViewById(R.id.titulo_dialog);
            tvMsg.setText(msg);

            switch (tipo) {
                case "Exito":
                    titulo_dialog.setText("Éxito");
                    btnNO.setVisibility(View.GONE);
                    btnSI.setText(C_btnSI);
                    break;
                case "Alerta":
                    imageView.setImageResource(R.drawable.ic_alert);
                    titulo_dialog.setText("Alerta");
                    btnSI.setText(C_btnSI);
                    btnSI.setBackgroundColor(getResources().getColor(R.color.colorAlert));
                    btnNO.setText(C_btnNO);
                    break;
                case "Error":
                    imageView.setImageResource(R.drawable.ic_error);
                    titulo_dialog.setText("Error");
                    btnSI.setBackgroundColor(getResources().getColor(R.color.colorError));
                    btnNO.setVisibility(View.GONE);
                    btnSI.setText(C_btnSI);
                    break;
                case "CerrarSesion":
                    imageView.setImageResource(R.drawable.ic_alert);
                    titulo_dialog.setText("Cerrar Sesión");
                    btnSI.setText(C_btnSI);
                    btnSI.setBackgroundColor(getResources().getColor(R.color.colorAlert));
                    btnNO.setText(C_btnNO);
                    break;
                case "Peligro":
                    imageView.setImageResource(R.drawable.ic_alerta_peligro);
                    titulo_dialog.setText("Peligro");
                    btnSI.setText(C_btnSI);
                    btnSI.setBackgroundColor(getResources().getColor(R.color.ms_material_grey_400));
                    btnNO.setText(C_btnNO);
                    break;
                case "Restaurar":
                    imageView.setImageResource(R.drawable.ic_alerta_peligro);
                    titulo_dialog.setText("Restaurar");
                    btnSI.setText(C_btnSI);
                    btnSI.setBackgroundColor(getResources().getColor(R.color.ms_material_grey_400));
                    btnNO.setText(C_btnNO);
                    btnNO.setBackgroundColor(getResources().getColor(R.color.colorError));
                    break;
            }


            btnSI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivityInstance().onOkClicked(GeneralDialogFragment.this, recurso_btn, obj);
                }
            });

            btnNO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivityInstance().onCancelClicked(GeneralDialogFragment.this);
                }
            });
        } else {
            dialog.setContentView(R.layout.dialog_loading);
            tvMsg = dialog.findViewById(R.id.tvTituloDialogLoading);
            tvMsg.setText(msg);
        }
        return dialog;
    }
}
