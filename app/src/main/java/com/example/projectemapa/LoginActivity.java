package com.example.projectemapa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class LoginActivity extends AppCompatActivity {
    Button registrar;
    EditText nomu,contrasenya;
    String strnom,strcontra;
    String url="http://projectemapas.atwebpages.com/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nomu = findViewById(R.id.nomusuari);
        contrasenya = findViewById(R.id.contrasenya);

        Button registrar = findViewById(R.id.registrar);
    registrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i1 = new Intent(getApplicationContext(), RegistrarActivity.class);
            startActivity(i1);
        }
    });
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ferlogin();
            }
        });

    }
    private void  ferlogin() {
        String nom = nomu.getText().toString().trim();
        String contrasenyaa = contrasenya.getText().toString().trim();


        ProgressDialog progressDialog = new ProgressDialog(this);
        if (nom.isEmpty()) {
            nomu.setError("Insereix el teu Nom");
        } else if (contrasenyaa.isEmpty()) {
            contrasenya.setError("Insereix la contrasenya");
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://projectemapas.atwebpages.com/login.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(Info_Marker.this,response,Toast.LENGTH_LONG).show();
                    if (response.equalsIgnoreCase("Login Fet Correctament")) {
                        nomu.setText("");
                        contrasenya.setText("");

                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));


                    } else {


                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Nom", nom);
                    params.put("Contrasenya", contrasenyaa);

                    return params;

                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(request);

        }
    }

}