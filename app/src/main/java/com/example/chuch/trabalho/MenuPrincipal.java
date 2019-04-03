package com.example.chuch.trabalho;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuPrincipal extends AppCompatActivity {

    //  Button btnHistorico, btnCertificado, btnDireitos, btnProxima;
    String id ="";
    String username ="";


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.menu_principal);
        id = getIntent().getStringExtra("id");
        username = getIntent().getStringExtra("username");



        CircleMenu circleMenu = findViewById(R.id.circleMenu);
        final String[] menus ={"Historico","Proxima Doação","Certificado","Direitos do Dador", "Terminar Sessão"};

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.bloods, R.drawable.bloods)
                .addSubMenu(Color.parseColor("#CDCDCD"), R.drawable.bloods)
                .addSubMenu(Color.parseColor("#CDCDCD"), R.drawable.bloods)
                .addSubMenu(Color.parseColor("#CDCDCD"), R.drawable.bloods)
                .addSubMenu(Color.parseColor("#CDCDCD"), R.drawable.bloods)
                .addSubMenu(Color.parseColor("#CDCDCD"), R.drawable.bloods)
                .addSubMenu(Color.parseColor("#CDCDCD"), R.drawable.bloods)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        if(menus[i] == "Historico"){

                            Toast.makeText(MenuPrincipal.this, "Histórico de doações", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MenuPrincipal.this, Historico.class);
                            intent.putExtra("id",id);
                            //Log.d("IDDD: ",id);

                            //:ID.putExtra("username",username);

                            startActivity(intent);

                        } else if (menus[i] == "Proxima Doação") {

                            Toast.makeText(MenuPrincipal.this, "Próxima doação", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MenuPrincipal.this, Proxima.class);

                            startActivity(intent);


                        } else if (menus[i] == "Certificado") {

                            Toast.makeText(MenuPrincipal.this, "Certificado de dador", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MenuPrincipal.this, Certificado.class);
                            intent.putExtra("id",id);

                            startActivity(intent);

                        } else if (menus[i] == "Direitos do Dador") {

                            Toast.makeText(MenuPrincipal.this, "Direitos de dador", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MenuPrincipal.this, Direitos.class);

                            startActivity(intent);


                        } else if (menus[i] == "Terminar Sessão") {

                            Toast.makeText(MenuPrincipal.this, "Terminar Sessão", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }
                });
    }

}
