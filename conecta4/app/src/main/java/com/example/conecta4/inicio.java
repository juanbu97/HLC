package com.example.conecta4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class inicio extends Activity {

    Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        iniciar = (Button)findViewById(R.id.singlePlayer);
    }

    public void singlePlayer(View v){

        startActivity(new Intent(this, MainActivity.class));
    }

    public void online(View v){
        startActivity(new Intent(this, onlineList.class));
    }


}
