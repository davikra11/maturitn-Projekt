package com.example.weatheapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    TextView t1_desc, t1_temp, t1_city, t2_desc, t2_temp, t2_city, t3_temp, t3_city, t3_desc, t4_temp, t4_city, t4_desc;
    ImageView SecondButton, FirstButton, ThirdButton, FourthButton;
    Button searchB;
    EditText et;
    TextView tv1, tv2, tv3, tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //finding components by id
        t1_temp = (TextView) findViewById(R.id.t1_temp);
        t1_city = (TextView) findViewById(R.id.t1_city);
        t1_desc = (TextView) findViewById(R.id.t1_desc);
        SecondButton = (ImageView) findViewById(R.id.background);

        t2_city = (TextView) findViewById(R.id.t2_city);
        t2_temp = (TextView) findViewById(R.id.t2_temp);
        t2_desc = (TextView) findViewById(R.id.t2_desc);
        FirstButton = (ImageView) findViewById(R.id.background2);

        t3_city = (TextView) findViewById(R.id.t3_city);
        t3_temp = (TextView) findViewById(R.id.t3_temp);
        t3_desc = (TextView) findViewById(R.id.t3_desc);
        ThirdButton = (ImageView) findViewById(R.id.background3);

        t4_temp = (TextView) findViewById(R.id.t4_temp);
        t4_city = (TextView) findViewById(R.id.t4_city);
        t4_desc = (TextView) findViewById(R.id.t4_desc);
        FourthButton = (ImageView) findViewById(R.id.background4);

        searchB = (Button) findViewById(R.id.searchB);
        et = findViewById(R.id.et);
        tv1 = findViewById(R.id.t1_city);
        tv2 = findViewById(R.id.t2_city);
        tv3 = findViewById(R.id.t3_city);
        tv4 = findViewById(R.id.t4_city);

        find_weather1("Prague");
        find_weather2("Bratislava");
        find_weather3("Los Angeles");
        find_weather4("Sydney");


        //Clickable buttons
        SecondButton.setClickable(true);
        SecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSecond();

            }
        });
        FirstButton.setClickable(true);
        FirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFirst();
            }

        });

        ThirdButton.setClickable(true);
        ThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openThird();
            }
        });

        FourthButton.setClickable(true);
        FourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFourth();
            }
        });


        //search button
        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSearch();
            }
        });


    }

    //Opening each class


    public void openFirst() {

        String tvCity = tv1.getText().toString();
        Intent intent = new Intent(MainActivity.this, FirstWeather.class);
        intent.putExtra("name", tvCity);
        startActivity(intent);
    }

    public void openSecond() {
        String tvCity = tv2.getText().toString();
        Intent intent = new Intent(MainActivity.this, SecondWeather.class);
        intent.putExtra("name", tvCity);
        startActivity(intent);
    }

    public void openThird() {
        String tvCity = tv3.getText().toString();
        Intent intent = new Intent(MainActivity.this, ThirdWeather.class);
        intent.putExtra("name", tvCity);
        startActivity(intent);
    }

    public void openFourth() {
        String tvCity = tv4.getText().toString();
        Intent intent = new Intent(MainActivity.this, FourthWeather.class);
        intent.putExtra("name", tvCity);
        startActivity(intent);
    }

    public void openSearch() {
        String etCity = et.getText().toString();
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra("cityName", etCity);
        startActivity(intent);
    }


    //Activities to show weather of each City

    public void find_weather1(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=76119ec523d907edd08a9aa18803923a";
        Intent intent = new Intent(MainActivity.this, FirstWeather.class);
        intent.putExtra("name", city);


        JsonObjectRequest Prague = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String city = response.getString("name");
                    String weat = object.getString("description");

                    System.out.println(city);

                    double temp_int = Double.parseDouble(temp);
                    double centi = (temp_int - 273.15);
                    centi = Math.round(centi);
                    int i = (int) centi;
                    t2_temp.setText(String.valueOf(i) + "°C");
                    t2_desc.setText(weat);
                    t2_city.setText(city);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(Prague);
    }

    public void find_weather2(String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=76119ec523d907edd08a9aa18803923a";
        Intent intent = new Intent(MainActivity.this, SecondWeather.class);
        intent.putExtra("name", city);


        JsonObjectRequest Milano = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String city = response.getString("name");
                    String weat = object.getString("description");


                    double temp_int = Double.parseDouble(temp);
                    double centi = (temp_int - 273.15);
                    centi = Math.round(centi);
                    int i = (int) centi;
                    t1_temp.setText(String.valueOf(i) + "°C");
                    t1_city.setText(city);
                    t1_desc.setText(weat);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(Milano);
    }

    public void find_weather3(String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=76119ec523d907edd08a9aa18803923a";
        Intent intent = new Intent(MainActivity.this, ThirdWeather.class);
        intent.putExtra("name", city);


        JsonObjectRequest Miami = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String city = response.getString("name");
                    String weat = object.getString("description");


                    double temp_int = Double.parseDouble(temp);
                    double centi = (temp_int - 273.15);
                    centi = Math.round(centi);
                    int i = (int) centi;
                    t3_temp.setText(String.valueOf(i) + "°C");
                    t3_city.setText(city);
                    t3_desc.setText(weat);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(Miami);
    }

    public void find_weather4(String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=76119ec523d907edd08a9aa18803923a";
        Intent intent = new Intent(MainActivity.this, FourthWeather.class);
        intent.putExtra("name", city);


        JsonObjectRequest Madrid = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String city = response.getString("name");
                    String weat = object.getString("description");


                    double temp_int = Double.parseDouble(temp);
                    double centi = (temp_int - 273.15);
                    centi = Math.round(centi);
                    int i = (int) centi;
                    t4_temp.setText(String.valueOf(i) + "°C");
                    t4_city.setText(city);
                    t4_desc.setText(weat);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(Madrid);
    }

    /*public void map (){
        String url = "https://tile.openweathermap.org/map/temp_new/0/0/0.png?appid=76119ec523d907edd08a9aa18803923a";
        JsonObjectRequest Map = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(Map);
        }
        A
     */
}