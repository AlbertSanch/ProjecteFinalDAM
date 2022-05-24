package com.example.projectemapa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {

    EditText tvnom,tvcognom,tvnomu,tvcontrasenya,insert;
    Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        tvnom =findViewById(R.id.nom);
        tvcognom=findViewById(R.id.cognom);
        tvnomu=findViewById(R.id.nomusuari);
        tvcontrasenya=findViewById(R.id.contrasenya);

        registrar=findViewById(R.id.registrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrardades();
            }
        });
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i1);
            }
        });
    }
    private void  registrardades() {
        String nom = tvnom.getText().toString().trim();
        String cognom = tvcognom.getText().toString().trim();
        String nomu = tvnomu.getText().toString().trim();
        String contrasenya = tvcontrasenya.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        if (nom.isEmpty()) {
            tvnom.setError("Insereix el teu Nom");
        } else if (cognom.isEmpty()) {
            tvcognom.setError("Insereix el Cognom");
        } else if (nomu.isEmpty()) {
            tvnomu.setError("Insereix nom Usuari");
        } else if (contrasenya.isEmpty()) {
            tvcontrasenya.setError("Insereix la Contrasenya");
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://projectemapas.atwebpages.com/RegistrarProjecte.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(Info_Marker.this,response,Toast.LENGTH_LONG).show();
                    if (response.equalsIgnoreCase("Usuari creat Correctament")) {
                        Toast.makeText(RegistrarActivity.this, "Dades Ingresades", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();


                    } else {


                        Toast.makeText(RegistrarActivity.this, response, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrarActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("NomUsuari", nom);
                    params.put("CognomUsuari", cognom);
                    params.put("Nom", nomu);
                    params.put("Contrasenya", contrasenya);

                    return params;

                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(RegistrarActivity.this);
            requestQueue.add(request);

        }
    }
}