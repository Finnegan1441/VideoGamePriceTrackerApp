package com.example.patrickcummins.videogamepricetracker.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.patrickcummins.videogamepricetracker.IsThereAnyDealService;
import com.example.patrickcummins.videogamepricetracker.Models.IGDBModels.Game;
import com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels.IsThereAnyDealTitleQuery;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by patrickcummins on 8/23/16.
 */
public class IGDBHelper {
    private static final String APIKEY = "3P7A7rIC6Hmsha9qRsKRQhtfFyzdp1jzLDFjsnKqCuqCJMkQhF";
    static final String BASE_URL = "https://api.isthereanydeal.com/";
    static final String BASE_URL2 = "https://igdbcom-internet-game-database-v1.p.mashape.com/";

    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private static IsThereAnyDealService dealService = retrofit.create(IsThereAnyDealService.class);
    private static Context context;
    private static IGDBOnResponseFinished onResponseFinished;


    public IGDBHelper(Context context) {
        this.context = context;


    }

    public interface IGDBOnResponseFinished {
        public void OnGamesRecieved(List gamesList);

    }

    public static void getGame(String title) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkIno = connectivityManager.getActiveNetworkInfo();
        if (networkIno != null && networkIno.isConnected()) {
            Call<Game> call = dealService.getGamesList("id%2Cname%2Curl%2Csummary%2Crelease_dates%2", "10", "release_dates.date%3Adesc", title);
            call.enqueue(new Callback<Game>() {
                @Override
                public void onResponse(Call<Game> call, Response<Game> response) {
                    try {

                        onResponseFinished.OnGamesRecieved(response.body().getReleaseDates());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Game> call, Throwable t) {
                    Toast.makeText(context, "SHITS FUCKED YO 2.0", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
