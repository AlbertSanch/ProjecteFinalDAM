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

public class Info_Marker extends AppCompatActivity {

    Intent intent;
    Intent intent2;
    Bundle extra ;
    Boolean miValor;
    Double lang;
    Double longitud;
    EditText tvtitol,tvtipus,tvhora,tvparticipants,insert;
    Button crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = this.getIntent();
        extra = intent.getExtras();
        miValor= extra.getBoolean("valor");
        lang= extra.getDouble("valor");
        longitud= extra.getDouble("valor");
        setContentView(R.layout.activity_info_marker);
        tvtitol=findViewById(R.id.titolactiv);
        tvtipus=findViewById(R.id.tipus);
        tvhora=findViewById(R.id.hora);
        tvparticipants=findViewById(R.id.participants);


        crear=findViewById(R.id.crear);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertardades();
            }
        });



    }
    private void  insertardades(){
        String titol=tvtitol.getText().toString().trim();
        String tipus=tvtipus.getText().toString().trim();
        String hora=tvhora.getText().toString().trim();
        String participants=tvparticipants.getText().toString().trim();

        ProgressDialog progressDialog=new ProgressDialog(this);
        if (titol.isEmpty()){
            tvtitol.setError("Insereix el titol");
        }
        else if (tipus.isEmpty()){
            tvtipus.setError("Insereix el tipus");
        }
        else if (hora.isEmpty()){
            tvhora.setError("Insereix la hora");
        }
        else if (participants.isEmpty()){
            tvparticipants.setError("Insereix el numero de participants");
        }
        else{
            progressDialog.show();
                StringRequest request=new StringRequest(Request.Method.POST, "http://projectemapas.atwebpages.com/ConnexioProjecte.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(Info_Marker.this,response,Toast.LENGTH_LONG).show();
                        if (response.equalsIgnoreCase("Activitat Creada Correctament")) {
                            Toast.makeText(Info_Marker.this, "Dades Ingresades", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();


                        } else {


                            Toast.makeText(Info_Marker.this, response, Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Info_Marker.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String ,String>params=new HashMap<String,String>();
                        params.put("TitolActivitat",titol);
                        params.put("TipusActivitat",tipus);
                        params.put("Hora",hora);
                        params.put("NParticipants",participants);

                        return params;

                    }
                };
            RequestQueue requestQueue= Volley.newRequestQueue(Info_Marker.this);
            requestQueue.add(request);

        }

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
        return click;
    }
}