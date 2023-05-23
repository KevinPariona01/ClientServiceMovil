package com.candwi.solinspeccion.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.candwi.solinspeccion.R;
import com.candwi.solinspeccion.datos.SQLiteHeper;

public class LogActivity extends AppCompatActivity {
    private SQLiteHeper mSqlHelper = new SQLiteHeper(this);
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        enlazarControles();
        configurarCabecera();
    }

    private void enlazarControles(){
        mRecyclerView = findViewById(R.id.recycleView);
    }

    private void configurarCabecera(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Log de envios");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}