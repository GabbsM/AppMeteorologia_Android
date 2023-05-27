package com.example.meteoprueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView ciudadTextView;
    private TextView temperaturaTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ciudadTextView = findViewById(R.id.id_ciudad);
        temperaturaTextView = findViewById(R.id.id_temp);

        // Hacer la solicitud a la API de OpenWeatherMap
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q=BARCELONA&appid=fd1658c0cc521830891301606b421aac";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Obtener los datos de la respuesta JSON
                            String ciudad = response.getString("name");
                            JSONObject main = response.getJSONObject("main");
                            double temperaturaKelvin = main.getDouble("temp");

                            // Convertir la temperatura a Celsius
                            double temperaturaCelsius = temperaturaKelvin - 273.15;

                            // Mostrar los datos en los TextView
                            ciudadTextView.setText(ciudad);
                            temperaturaTextView.setText(String.format("%.1f °C", temperaturaCelsius));
                            Log.d("MainActivity", "Ciudad: " + ciudad);
                            Log.d("MainActivity", "Temperatura: " + temperaturaCelsius);
                            // Después de mostrar la temperatura actual
// ...
// Obtener la lista de predicciones del tiempo (ejemplo)
                            List<ForecastItem> forecastItems = new ArrayList<>();
                            forecastItems.add(new ForecastItem("Lunes", "Soleado"));
                            forecastItems.add(new ForecastItem("Martes", "Nublado"));
                            forecastItems.add(new ForecastItem("Miércoles", "Lluvioso"));
                            forecastItems.add(new ForecastItem("Jueves", "Nublado"));
                            forecastItems.add(new ForecastItem("Viernes", "Soleado"));

// Configurar el RecyclerView y el adaptador
                            RecyclerView recyclerView = findViewById(R.id.recyclerView);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            ForecastAdapter forecastAdapter = new ForecastAdapter(forecastItems);
                            recyclerView.setAdapter(forecastAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar el error de la solicitud
                error.printStackTrace();
            }
        });

        // Agregar la solicitud a la cola
        queue.add(jsonObjectRequest);
    }

}