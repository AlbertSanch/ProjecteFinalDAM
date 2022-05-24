package com.example.projectemapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AgregarAmicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_amics);
        ImageButton agregarbtt = findViewById(R.id.agregarAmic);
        agregarbtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=new Intent(getApplicationContext(),AgregarA.class);
                startActivity(g);
            }
        });
        ImageButton eliminarbtt = findViewById(R.id.eliminarAmic);
        eliminarbtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=new Intent(getApplicationContext(),EliminarA.class);
                startActivity(g);
            }
        });
    }
    public boolean tornarenrere(View view) {
        boolean click = false;
        int id = view.getId();
        // Show different message when click different view component.
        if (id == R.id.crear) {
            click = true;
            return click;
        } else if (id == R.id.enrere) {
            click = false;
            finish();
        }
        return click;}
}