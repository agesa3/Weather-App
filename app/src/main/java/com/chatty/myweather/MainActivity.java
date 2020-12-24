package com.chatty.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnGetCityId, btnGetWeatherByCityId, btnGetWeatherByCityName;
    EditText edtGetInputData;
    ListView listDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetCityId = findViewById(R.id.btn_cityId);
        btnGetWeatherByCityId = findViewById(R.id.btn_weatherByCityId);
        btnGetWeatherByCityName = findViewById(R.id.btn_cityWeatherByName);
        edtGetInputData = findViewById(R.id.edt_InputData);
        listDisplay = findViewById(R.id.listView_report);

        final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);


        btnGetCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weatherDataService.getCityID(edtGetInputData.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, "City ID of " + cityID, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        btnGetWeatherByCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForecastByID(edtGetInputData.getText().toString(), new WeatherDataService.ForecastByIdResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModel);
                        listDisplay.setAdapter(arrayAdapter);
                    }
                });
            }
        });
        btnGetWeatherByCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForecastByName(edtGetInputData.getText().toString(), new WeatherDataService.GetCityForecastByName() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Error 2", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModel);
                        listDisplay.setAdapter(arrayAdapter);
                    }
                });
            }
        });
    }
}