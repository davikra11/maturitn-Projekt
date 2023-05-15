package com.example.weatheapp;

import android.os.Bundle;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import kotlin.text.Charsets;

public class SearchActivity extends AppCompatActivity {
    TextView search_temp, search_name, search_desc, search_min, search_max, search_p, search_v, search_h, search_feels, search_forecast1, search_forecast2, search_forecast3, search_forecast4, search_date1, search_date2, search_date3, search_date4;
    ImageView search_condition1, search_condition2, search_condition3, search_condition4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //remove ActionBar with name of Application
        getSupportActionBar().hide();

        search_name = (TextView) findViewById(R.id.search_name);
        search_temp = (TextView) findViewById(R.id.search_temp);
        search_desc = (TextView) findViewById(R.id.search_desc);

        search_min = (TextView) findViewById(R.id.search_min);
        search_max = (TextView) findViewById(R.id.search_max);

        search_feels = (TextView) findViewById(R.id.weather_feels);
        search_p = (TextView) findViewById(R.id.weather_p);
        search_v = (TextView) findViewById(R.id.weather_c);
        search_h = (TextView) findViewById(R.id.weather_h);

        search_forecast1 = (TextView) findViewById(R.id.weather_forecast1);
        search_forecast2 = (TextView) findViewById(R.id.weather_forecast2);
        search_forecast3 = (TextView) findViewById(R.id.weather_forecast3);
        search_forecast4 = (TextView) findViewById(R.id.weather_forecast4);

        search_condition1 = (ImageView) findViewById(R.id.search_condition1);
        search_condition2 = (ImageView) findViewById(R.id.search_condition2);
        search_condition3 = (ImageView) findViewById(R.id.search_condition3);
        search_condition4 = (ImageView) findViewById(R.id.search_condition4);

        search_date1 = (TextView) findViewById(R.id.search_date1);
        search_date2 = (TextView) findViewById(R.id.search_date2);
        search_date3 = (TextView) findViewById(R.id.search_date3);
        search_date4 = (TextView) findViewById(R.id.search_date4);



        find_weather();
        search_forecast();
        weatherChange();
    }

    public void find_weather(){


        String city = getIntent().getStringExtra("cityName");
        try {
                String query = URLEncoder.encode(city, Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=76119ec523d907edd08a9aa18803923a";


        JsonObjectRequest Weather = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String city = response.getString("name");
                    String weat = object.getString("description");

                    JSONObject countr = response.getJSONObject("sys");
                    String count = countr.getString("country");
                    search_v.setText(count);

                    double temp_int = Double.parseDouble(temp);
                    double centi = (temp_int - 273.15) ;
                    centi = Math.round(centi);
                    int i = (int)centi;

                    search_temp.setText(String.valueOf(i) + "°C");
                    search_desc.setText(weat);
                    search_name.setText(city);

                    String temp_mi = String.valueOf(main_object.getDouble("temp_min"));
                    double temp_min = Double.parseDouble(temp_mi);
                    double centim = (temp_min - 273.15) ;
                    centim = Math.round(centim);
                    int a = (int)centim;

                    search_min.setText(String.valueOf(a) + "°C");

                    String temp_ma = String.valueOf(main_object.getDouble("temp_max"));
                    double temp_max = Double.parseDouble(temp_ma);
                    double centima = (temp_max - 273.15) ;
                    centima = Math.round(centima);
                    int b = (int)centima;

                    search_max.setText(String.valueOf(b) + "°C");
                    String temp_fe = String.valueOf(main_object.getDouble("feels_like"));
                    double temp_fee = Double.parseDouble(temp_fe);
                    double centimat = (temp_fee - 273.15);
                    centimat = Math.round(centimat);
                    int c = (int) centimat;

                    search_feels.setText(String.valueOf(c) + "°C");


                    String hum = String.valueOf(main_object.getDouble("humidity"));
                    double humi = Double.parseDouble(hum);
                    double humidity = (humi);
                    humidity = Math.round(humidity);
                    int d = (int) humidity;
                    search_h.setText(String.valueOf(d));


                    String pre = String.valueOf(main_object.getDouble("pressure"));
                    double pres = Double.parseDouble(pre);
                    double pressure = (pres);
                    pressure = Math.round(pressure);
                    int f = (int) pressure;
                    search_p.setText(String.valueOf(f));



                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(Weather);
    }

    private String getWeatherImage1(String description){
        String url = "";
        switch (description){

            case "Clear":{
                url = "sun.png";
                search_condition1.setImageResource(R.drawable.sun);
                break;
            }

            case "Sun":{
                url = "sun.png";
                search_condition1.setImageResource(R.drawable.sun);
                break;
            }
            case "Rain":{
                url = "rain.png";
                search_condition1.setImageResource(R.drawable.rain);
                break;
            }
            case "Clouds":{
                url ="broken_clouds.png";
                search_condition1.setImageResource(R.drawable.broken_clouds);
                break;
            }

            case "Drizzle":{
                url = "shower_rain.png";
                search_condition1.setImageResource(R.drawable.shower_rain);
                break;
            }
            case "Snow":{
                url = "show.png";
                search_condition1.setImageResource(R.drawable.snow);
                ;
                break;
            }
            case "Thunderstorm":{
                url = "thunderstorm.png";
                search_condition1.setImageResource(R.drawable.thunderstorm);
                break;
            }


        }
        return url;
    }
    private String getWeatherImage2(String description){
        String url = "";
        switch (description){

            case "Clear":{
                url = "sun.png";
                search_condition2.setImageResource(R.drawable.sun);
                break;
            }

            case "Sun":{
                url = "sun.png";
                search_condition2.setImageResource(R.drawable.sun);
                break;
            }
            case "Rain":{
                url = "rain.png";
                search_condition2.setImageResource(R.drawable.rain);
                break;
            }
            case "Clouds":{
                url ="broken_clouds.png";
                search_condition2.setImageResource(R.drawable.broken_clouds);
                break;
            }

            case "Drizzle":{
                url = "shower_rain.png";
                search_condition2.setImageResource(R.drawable.shower_rain);
                break;
            }
            case "Snow":{
                url = "show.png";
                search_condition2.setImageResource(R.drawable.snow);
                ;
                break;
            }
            case "Thunderstorm":{
                url = "thunderstorm.png";
                search_condition2.setImageResource(R.drawable.thunderstorm);
                break;
            }


        }
        return url;
    }
    private String getWeatherImage3(String description){
        String url = "";
        switch (description){

            case "Clear":{
                url = "sun.png";
                search_condition3.setImageResource(R.drawable.sun);
                break;
            }

            case "Sun":{
                url = "sun.png";
                search_condition3.setImageResource(R.drawable.sun);
                break;
            }
            case "Rain":{
                url = "rain.png";
                search_condition3.setImageResource(R.drawable.rain);
                break;
            }
            case "Clouds":{
                url ="broken_clouds.png";
                search_condition3.setImageResource(R.drawable.broken_clouds);
                break;
            }

            case "Drizzle":{
                url = "shower_rain.png";
                search_condition3.setImageResource(R.drawable.shower_rain);
                break;
            }
            case "Snow":{
                url = "show.png";
                search_condition3.setImageResource(R.drawable.snow);
                ;
                break;
            }
            case "Thunderstorm":{
                url = "thunderstorm.png";
                search_condition3.setImageResource(R.drawable.thunderstorm);
                break;
            }


        }
        return url;
    }
    private String getWeatherImage4(String description){
        String url = "";
        switch (description){

            case "Clear":{
                url = "sun.png";
                search_condition4.setImageResource(R.drawable.sun);
                break;
            }

            case "Sun":{
                url = "sun.png";
                search_condition4.setImageResource(R.drawable.sun);
                break;
            }
            case "Rain":{
                url = "rain.png";
                search_condition4.setImageResource(R.drawable.rain);
                break;
            }
            case "Clouds":{
                url ="broken_clouds.png";
                search_condition4.setImageResource(R.drawable.broken_clouds);
                break;
            }

            case "Drizzle":{
                url = "shower_rain.png";
                search_condition4.setImageResource(R.drawable.shower_rain);
                break;
            }
            case "Snow":{
                url = "show.png";
                search_condition4.setImageResource(R.drawable.snow);
                ;
                break;
            }
            case "Thunderstorm":{
                url = "thunderstorm.png";
                search_condition4.setImageResource(R.drawable.thunderstorm);
                break;
            }


        }
        return url;
    }

        public String getUserDate (String userDate){
            return userDate.substring(5,10);
        }

        public void search_forecast(){

        String city = getIntent().getStringExtra("cityName");
        try {
            String query = URLEncoder.encode(city, Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=76119ec523d907edd08a9aa18803923a";

        JsonObjectRequest Search_forecast = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("list");

                    for(int i = 4; i < 5; i++){
                        JSONObject list = jsonArray.getJSONObject(i);
                        String date = list.getString("dt_txt");
                        String dateForUser = getUserDate(date);
                        search_date1.setText(dateForUser);
                        JSONObject jsonListArray = list.getJSONObject("main");
                        for(int j = 0; j < jsonListArray.length(); j++) {
                            String temp1 = String.valueOf(jsonListArray.getDouble("temp"));
                            double temp_int = Double.parseDouble(temp1);
                            double centi = (temp_int - 273.15);
                            centi = Math.round(centi);
                            int k = (int) centi;
                            search_forecast1.setText(String.valueOf(k) + "°C");
                            break;
                        }
                    }
                    for(int i = 10; i < 11; i++){
                        JSONObject list = jsonArray.getJSONObject(i);
                        String date = list.getString("dt_txt");
                        String dateForUser = getUserDate(date);
                        search_date2.setText(dateForUser);
                        JSONObject jsonListArray = list.getJSONObject("main");
                        for(int j = 0; j < jsonListArray.length(); j++) {
                            String temp1 = String.valueOf(jsonListArray.getDouble("temp"));
                            double temp_int = Double.parseDouble(temp1);
                            double centi = (temp_int - 273.15);
                            centi = Math.round(centi);
                            int k = (int) centi;
                            search_forecast2.setText(String.valueOf(k) + "°C");
                            break;
                        }
                    }
                    for(int i = 22; i < 23; i++) {
                        JSONObject list = jsonArray.getJSONObject(i);
                        String date = list.getString("dt_txt");
                        String dateForUser = getUserDate(date);
                        search_date3.setText(dateForUser);
                        JSONObject jsonListArray = list.getJSONObject("main");
                        for (int j = 0; j < jsonListArray.length(); j++) {
                            String temp1 = String.valueOf(jsonListArray.getDouble("temp"));
                            double temp_int = Double.parseDouble(temp1);
                            double centi = (temp_int - 273.15);
                            centi = Math.round(centi);
                            int k = (int) centi;
                            search_forecast3.setText(String.valueOf(k) + "°C");
                            break;
                        }
                    }
                    for(int i = 27; i < 28; i++) {
                        JSONObject list = jsonArray.getJSONObject(i);
                        String date = list.getString("dt_txt");
                        String dateForUser = getUserDate(date);
                        search_date4.setText(dateForUser);
                        JSONObject jsonListArray = list.getJSONObject("main");
                        for (int j = 0; j < jsonListArray.length(); j++) {
                            String temp1 = String.valueOf(jsonListArray.getDouble("temp"));
                            double temp_int = Double.parseDouble(temp1);
                            double centi = (temp_int - 273.15);
                            centi = Math.round(centi);
                            int k = (int) centi;
                            search_forecast4.setText(String.valueOf(k) + "°C");
                            break;
                        }
                    }




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
        queue.add(Search_forecast);
    }



    public void weatherChange(){
        String city = getIntent().getStringExtra("cityName");
        try {
            String query = URLEncoder.encode(city, Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=76119ec523d907edd08a9aa18803923a";

        JsonObjectRequest first_condition = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("list");

                    for(int i = 4; i < 5; i++){
                        JSONObject list = jsonArray.getJSONObject(i);
                        JSONArray weatherArray = list.getJSONArray("weather");
                        for(int j = 0; j < weatherArray.length(); j++){
                            JSONObject weather = weatherArray.getJSONObject(j);
                            String weat = weather.getString("main");
                            getWeatherImage1(weat);
                            break;
                        }
                    }
                    for(int i = 10; i < 11; i++){
                        JSONObject list = jsonArray.getJSONObject(i);
                        JSONArray weatherArray = list.getJSONArray("weather");
                        for(int j = 0; j < weatherArray.length(); j++){
                            JSONObject weather = weatherArray.getJSONObject(j);
                            String weat = weather.getString("main");
                            getWeatherImage2(weat);
                            break;
                        }
                    }
                    for(int i = 16; i < 17; i++){
                        JSONObject list = jsonArray.getJSONObject(i);
                        JSONArray weatherArray = list.getJSONArray("weather");
                        for(int j = 0; j < weatherArray.length(); j++){
                            JSONObject weather = weatherArray.getJSONObject(j);
                            String weat = weather.getString("main");
                            getWeatherImage3(weat);
                            break;
                        }
                    }
                    for(int i = 23; i < 24; i++){
                        JSONObject list = jsonArray.getJSONObject(i);
                        JSONArray weatherArray = list.getJSONArray("weather");
                        for(int j = 0; j < weatherArray.length(); j++){
                            JSONObject weather = weatherArray.getJSONObject(j);
                            String weat = weather.getString("main");
                            getWeatherImage4(weat);
                            break;
                        }
                    }




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
        queue.add(first_condition);
    }
}