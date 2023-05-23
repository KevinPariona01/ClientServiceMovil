package com.candwi.solinspeccion.ui.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.candwi.solinspeccion.entidad.Basicas.Combo;
import com.candwi.solinspeccion.entidad.Auxiliares.lista.Basicas.Combos;

public class SpinnerAdapter extends ArrayAdapter<Combo> {


    LayoutInflater inflator;
    private Context context;
    private Combos values;

    public SpinnerAdapter(Context context, int textViewResourceId, Combos values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
        this.inflator= LayoutInflater.from(context);
    }

    public int getPosition(int idCombo) {
        int position=0;
        for(int i=0;i<values.size();i++){
            if(values.get(i).getId()==idCombo){
                position = i;
            }
        }
        return position;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Combo getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
