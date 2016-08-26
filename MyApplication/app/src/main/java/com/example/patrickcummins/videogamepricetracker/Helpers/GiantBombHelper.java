package com.example.patrickcummins.videogamepricetracker.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.patrickcummins.videogamepricetracker.APIService;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.GameSearchResult;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.Result;
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
    private static ConnectivityManager connectivityManager;
    private static NetworkInfo networkIno;


    public GiantBombHelper(Context context, GiantBombOnResponseFinished onResponseFinished) {
        this.context = context;
        this.onResponsesFinished = onResponseFinished;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkIno = connectivityManager.getActiveNetworkInfo();
    }

    public interface GiantBombOnResponseFinished {
        void OnGamesRecieved(GameSearchResult gameSearchResult);
        void OnSpecificGameRecieved(Result result);

    }
    public static void getGame(int id){
        if (networkIno != null && networkIno.isConnected()) {
            Call<GameSearchResult> call = apiService.getGame(APIKEY,FORMAT, "1","id:"+id);
            call.enqueue(new Callback<GameSearchResult>() {
                @Override
                public void onResponse(Call<GameSearchResult> call, Response<GameSearchResult> response) {
                    GameSearchResult gameSearchResult = response.body();
                    onResponsesFinished.OnSpecificGameRecieved(gameSearchResult.getResults().get(0));
                }

                @Override
                public void onFailure(Call<GameSearchResult> call, Throwable t) {

                }
            });

        }


    }

    public static void searchForGame(String title) {

        if (networkIno != null && networkIno.isConnected()) {
            Call<GameSearchResult> call = apiService.getGamesList(APIKEY,FORMAT, "10", "game", title);
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
