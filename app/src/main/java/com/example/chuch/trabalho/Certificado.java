package com.example.chuch.trabalho;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Certificado extends AppCompatActivity {

    String id = "";
    private String url = "http://172.16.184.73/trab/api/certificados/";
    TextView grupores, idres, nomeres, utres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_certificado);

        grupores = (TextView) findViewById(R.id.grupores);
        idres = (TextView) findViewById(R.id.idres);
        nomeres = (TextView) findViewById(R.id.nomeres);
        utres = (TextView) findViewById(R.id.utres);

        getData();


    }

    private void getData (){

        id = getIntent().getStringExtra("id");
        Log.d("ID: ",id);

        String urll = url+id;
        Log.d("URLL: ",urll);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, urll, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                    try {
                        ((TextView) findViewById(R.id.grupores)).setText(response.getString("tiposangue"));
                        ((TextView) findViewById(R.id.idres)).setText(response.getString("id"));
                        ((TextView) findViewById(R.id.nomeres)).setText(response.getString("nome"));
                        ((TextView) findViewById(R.id.utres)).setText(response.getString("nmrsaude"));



                    } catch (JSONException ex) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsObjRequest);
    }

}
