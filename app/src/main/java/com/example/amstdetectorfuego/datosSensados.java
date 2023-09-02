package com.example.amstdetectorfuego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class datosSensados extends AppCompatActivity {

    DatabaseReference db_reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_sensados);

        db_reference = FirebaseDatabase.getInstance().getReference().child("data");
        leerRegistros();
    }

    public void leerRegistros(){
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mostrarRegistrosPorPantalla(snapshot);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }

    public void mostrarRegistrosPorPantalla(DataSnapshot snapshot){
        LinearLayout contFecha = (LinearLayout) findViewById(R.id.ContenedorTime);
        LinearLayout contBateria = (LinearLayout) findViewById(R.id.ContenedorBat);
        LinearLayout contIntensidad = (LinearLayout) findViewById(R.id.ContenedorInt);
        LinearLayout contAlarma = (LinearLayout) findViewById(R.id.ContenedorAlarm);

        String intensidadVal = snapshot.child("intensidad").getValue().toString();
        String alarmaVal = snapshot.child("alarma").getValue().toString();
        String bateria = snapshot.child("bateria").getValue().toString();
        String dateTime = snapshot.child("fecha").getValue().toString().substring(11);

        Log.d("TAG", "Valor de bateria: " + bateria);
        Log.d("TAG", "Valor de alarma Raw: " + alarmaVal);

        String alarma = alarmaVal.replace("1", "Fuego");
        alarma = alarma.replace("0", "");

        Log.d("TAG", "Valor de alarma: " + alarma);

        TextView intensidad = new TextView(getApplicationContext());
        intensidad.setTextColor(getResources().getColor(R.color.black));
        intensidad.setText(intensidadVal);
        contIntensidad.addView(intensidad);

        TextView fecha = new TextView(getApplicationContext());
        fecha.setTextColor(getResources().getColor(R.color.black));
        fecha.setText(dateTime);
        contFecha.addView(fecha);

        TextView batery = new TextView(getApplicationContext());
        batery.setTextColor(getResources().getColor(R.color.black));
        batery.setText(bateria);
        contBateria.addView(batery);

        TextView alarmatxt = new TextView(getApplicationContext());
        alarmatxt.setTextColor(getResources().getColor(R.color.black));
        alarmatxt.setText(alarma);
        contAlarma.addView(alarmatxt);
    }
}