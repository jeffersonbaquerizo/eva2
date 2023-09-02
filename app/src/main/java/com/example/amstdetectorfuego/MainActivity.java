package com.example.amstdetectorfuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class MainActivity extends AppCompatActivity {

    FloatingActionsMenu btnMenu;
    FloatingActionButton btnDatos, btnBateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu = (FloatingActionsMenu) findViewById(R.id.btnMenu);
        btnDatos = (FloatingActionButton) findViewById(R.id.btnDatos);
        btnBateria = (FloatingActionButton) findViewById(R.id.btnBateria);

    }

    public void DatoSensados(View view) {
        Intent intent = new Intent(this, datosSensados.class);
        startActivity(intent);
    }

    public void EstadoBateria(View view) {
        Intent intent = new Intent(this, estadoBateria.class);
        startActivity(intent);
    }

}