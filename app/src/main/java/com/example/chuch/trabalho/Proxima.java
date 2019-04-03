package com.example.chuch.trabalho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Proxima extends AppCompatActivity {

    private String url = "http://172.16.184.73/trab/api/historico/";
    String id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_proxima);
    }
}
