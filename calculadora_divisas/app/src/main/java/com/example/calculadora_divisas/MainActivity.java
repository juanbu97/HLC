package com.example.calculadora_divisas;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    Spinner spinner;
    Spinner spinner2;
    TextView conversion;
    EditText inputMonedas;
    String base;
    String cambio;
    //https://api.exchangeratesapi.io/latest?base=EUR&symbols=USD


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        conversion = (TextView) findViewById(R.id.conversion);
        inputMonedas = (EditText) findViewById(R.id.inputModenas);

    }

    public void calcular(View v){
        cambio = spinner2.getSelectedItem().toString();
        base = spinner.getSelectedItem().toString();
        String URL = "https://api.exchangeratesapi.io/latest?base="+base+"&symbols="+cambio;

        queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    double resultadoConversion;
                    String resultadoConversionString;
                    JSONObject obj = new JSONObject(response.toString());
                    String stringJson = obj.getJSONObject("rates").getString(cambio);

                    resultadoConversion = Double.parseDouble(stringJson) * Double.parseDouble(inputMonedas.getText().toString());
                    resultadoConversionString = Double.toString(resultadoConversion);

                    conversion.setText(resultadoConversionString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //textView.setText(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });

        queue.add(request);
    }
}