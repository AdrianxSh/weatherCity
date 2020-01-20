package com.example.weathercity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weathercity.ForecastActivity;
import com.example.weathercity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.town) TextView view_city;
    @BindView(R.id.temp) TextView view_temp;
    @BindView(R.id.desc) TextView view_desc;

    @BindView(R.id.weather_image) ImageView view_weather;
    @BindView(R.id.search_edit) EditText search;
    String rotateSearch;
    @BindView(R.id.floating_search) FloatingActionButton search_floating;
    @BindView(R.id.weather_layout) LinearLayout forecastWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        search_floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide keyboard
                InputMethodManager im = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(getCurrentFocus().getRootView().getWindowToken(), 0);

                api_key(String.valueOf(search.getText()));
            }
        });

        forecastWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
                intent.putExtra("cityName", String.valueOf(search.getText()));
                startActivity(intent);
            }
        });

//        if(savedInstanceState !=null) {
//            rotateSearch =savedInstanceState.getString("city");
//            search.setText(String.valueOf(rotateSearch));
//        }

    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("city",String.valueOf(search.getText()));
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    private void api_key(final String City) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" + City + "&appid=b975fe54861beebc1d76b81a3c38730b&units=metric")
                .get().build();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Response response = client.newCall(request).execute();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String responseData = response.body().string();
                    try {
                        JSONObject json = new JSONObject(responseData);
                        JSONArray array = json.getJSONArray("weather");

                        JSONObject object = array.getJSONObject(0);

                        String description = object.getString("description");
                        String icons = object.getString("icon");

                        JSONObject templ = json.getJSONObject("main");
                        Double temperature = templ.getDouble("temp");


                        setText(view_city, City);

                        String temps = Math.round(temperature) + "°C";

                        setText(view_temp, temps);
                        setText(view_desc, description);
                        setImage(view_weather, icons);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setText(final TextView text, final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });

    }

    private void setImage(final ImageView imageView, final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (value) {
                    case "01d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "01n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "02d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "02n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "03d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "03n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "04d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "04n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "09d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "09n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "10d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "10n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "11d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "11n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "13d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    case "13n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.wheather));
                }
            }
        });
    }
}