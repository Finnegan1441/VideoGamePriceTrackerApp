package com.example.patrickcummins.videogamepricetracker.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.patrickcummins.videogamepricetracker.APIService;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.GameSearchResult;
import com.example.patrickcummins.videogamepricetracker.Models.IGDBModels.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by patrickcummins on 8/23/16.
 */
public class GiantBombHelper {

    private static final String APIKEY = "";
    private static final String BASE_URL = "http://www.giantbomb.com/api/";
    private static final String FORMAT = "json";


    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private static APIService apiService = retrofit.create(APIService.class);
    private static Context context;
    private static GiantBombOnResponseFinished onResponsesFinished;


    public GiantBombHelper(Context context, GiantBombOnResponseFinished onResponseFinished) {
        this.context = context;
        this.onResponsesFinished = onResponseFinished;


    }

    public interface GiantBombOnResponseFinished {
        public void OnGamesRecieved(GameSearchResult gameSearchResult);

    }

    public static void getGame(String title) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkIno = connectivityManager.getActiveNetworkInfo();
        if (networkIno != null && networkIno.isConnected()) {
            Call<GameSearchResult> call = apiService.getGamesList(APIKEY,FORMAT, "10", "game", title);
//            call.enqueue(new Callback<Game>() {
//                @Override
//                public void onResponse(Call<Game> call, Response<Game> response) {
//                    try {
//
//                        onResponseFinished.OnGamesRecieved(response.body().getReleaseDates());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Game> call, Throwable t) {
//                    Toast.makeText(context, "SHITS FUCKED YO 2.0", Toast.LENGTH_SHORT).show();
//                }
//            });
            call.enqueue(new Callback<GameSearchResult>() {
                @Override
                public void onResponse(Call<GameSearchResult> call, Response<GameSearchResult> response) {
                    try {
                        GameSearchResult gameSearchResult = response.body();
                       onResponsesFinished.OnGamesRecieved(gameSearchResult);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GameSearchResult> call, Throwable t) {
                }
            });
        }
    }
}
