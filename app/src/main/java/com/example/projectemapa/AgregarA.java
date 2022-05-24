package com.example.projectemapa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AgregarA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
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