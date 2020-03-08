package com.example.conecta4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class onlineList extends AppCompatActivity {

    private ListView listaPartidas;
    private ArrayList<String> partidas;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_list);

        listaPartidas = (ListView)findViewById(R.id.listView);


        partidas = new ArrayList<String>();

        partidas.add("Hola");
        partidas.add("adios");
        partidas.add("hasta luego");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, partidas);

        listaPartidas.setAdapter(adapter);
    }


}
