package com.example.amstdetectorfuego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class estadoBateria extends AppCompatActivity {

    DatabaseReference db_reference;

    LinearLayout linearLayoutbatteryCont;
    LinearLayout linearLayoutbatteryLoad;

    int high = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_bateria);

        linearLayoutbatteryCont = findViewById(R.id.idBatteryCont);
        linearLayoutbatteryLoad = findViewById(R.id.idBatteryLoad);

        high = 1000;
        Log.d("TAG", "Altura: " + high);

        db_reference = FirebaseDatabase.getInstance().getReference().child("data");

        loadData();
    }
    void loadData(){
        db_reference
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                        // Declarar una variable para almacenar el último hijo
                        DataSnapshot lastChild = null;

                        // Iterar a través de los hijos para encontrar el último
                        for (DataSnapshot child : children) {
                            lastChild = child;
                        }

                        // Aquí puedes usar lastChild para acceder al último hijo
                        if (lastChild != null) {
                            // Por ejemplo, para obtener el valor del último hijo:
                            String bateria = lastChild.child("bateria").getValue().toString();

                            Log.d("TAG", "Valor de bateria: " + bateria); // Verifica el valor de bateria

                            float numBateria = Float.parseFloat(bateria);

                            float highLoad = (float) (high * (numBateria / 100.0));

                            Log.d("TAG", "Valor de highLoad: " + highLoad); // Verifica el valor de highLoad


                            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayoutbatteryLoad.getLayoutParams();

                            int alturaRedondeada = Math.round(highLoad);

                            Log.d("TAG", "Altura redondeada: " + alturaRedondeada); // Verifica la altura redondeada

                            params.height = alturaRedondeada;

                            linearLayoutbatteryLoad.setLayoutParams(params);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        System.out.println(error.toException());
                    }
                });
    }
}
