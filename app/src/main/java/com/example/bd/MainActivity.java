package com.example.bd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarInformacio();
    }

    public void cargarInformacio() {
        String url = "https://rickandmortyapi.com/api/character";

        StringRequest myRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    recibirInformacion(new JSONObject(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "ERROR EN EL SERVIDOR", Toast.LENGTH_LONG).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR EN EL SERVIDOR", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(myRequest);
    }

    public void recibirInformacion(JSONObject respuesta) {
        try {
            String nombre = respuesta.getJSONArray("Results").getJSONObject(0).getString("name: ");
            Toast.makeText(getApplicationContext(), nombre, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "ERROR EN EL SERVIDOR", Toast.LENGTH_LONG).show();
        }
    }

}