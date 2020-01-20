package com.example.weathercity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathercity.adapter.ForecastAdapter;
import com.example.weathercity.model.ModelForecast;
import com.example.weathercity.retrofit.RetrofitClient;
import com.example.weathercity.retrofit.RetrofitService;

import butterknife.BindView;
import butterknife.ButterKnife;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForecastActivity extends AppCompatActivity {

    @BindView(R.id.rvForecasts)
    public RecyclerView recyclerView;

    private ForecastAdapter mAdapter;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    private RetrofitService retrofitService = retrofit.create(RetrofitService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        String nameCity = bundle.getString("cityName");

        bindAdapter();

        init(nameCity);
    }

    private void init(final String CITYNAME) {

        final Call<ModelForecast> call = retrofitService.getWeatherForecast(CITYNAME, "metric", "b975fe54861beebc1d76b81a3c38730b");

        call.enqueue(new Callback<ModelForecast>() {
            @Override
            public void onResponse(Call<ModelForecast> call, Response<ModelForecast> response) {
                mAdapter.addWeatherForecastModel(response.body());

            }

            @Override
            public void onFailure(Call<ModelForecast> call, Throwable t) {
                Toast.makeText(ForecastActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void bindAdapter() {
        mAdapter = new ForecastAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }
}
