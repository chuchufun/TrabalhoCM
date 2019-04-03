package com.example.chuch.trabalho;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class LoginActivity extends AppCompatActivity {
    String URLL, URLLL;
    Button btn_login;
    EditText et_username, et_password;
    private static String URL_DADOS = "http://172.16.184.74/trab/api/id/";
    private static String URL_LOGIN = "http://172.16.184.74/trab/api/login/";
    private Context mContext;
    String id;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = getApplicationContext();
        btn_login = findViewById(R.id.btn_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

         id="";

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                verDados();
            }
        });
    }

    public void verDados(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        URLLL = URL_DADOS + et_username.getText().toString();
        Log.d("URL:", URLLL);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLLL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                         id = jsonObject.getString("id");
                         username = jsonObject.getString("username");
                        //Log.d("LOGID:", id);
                        //Log.d("LOGUSERNAME", username);

                    } catch (JSONException ex) {
                        ex.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);



    }

    public void login() {
        URLL = URL_LOGIN + et_username.getText().toString()+";"+et_password.getText().toString();
        Log.d("LINK", URLL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if(response.trim().equals("success")){
                            Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                            intent.putExtra("id",id);
                            intent.putExtra("username", username);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Credenciais erradas. Tente novamente", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error:" +error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("UserName", et_username.getText().toString());
                params.put("Password", et_password.getText().toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }





}
