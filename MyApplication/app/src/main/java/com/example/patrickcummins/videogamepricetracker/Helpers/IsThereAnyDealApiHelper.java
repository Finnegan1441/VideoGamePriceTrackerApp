package com.example.patrickcummins.videogamepricetracker.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.patrickcummins.videogamepricetracker.APIService;
import com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels.Data;
import com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels.IsThereAnyDealTitleQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by patrickcummins on 8/23/16.
 */

public class IsThereAnyDealApiHelper {
    private static final String APIKEY = "";
    static final String BASE_URL = "https://api.isthereanydeal.com/";

    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private static APIService dealService = retrofit.create(APIService.class);
    private static Context context;
    private static IsThereAnyDealOnResponseFinished onResponseFinished;
    private  static ConnectivityManager connectivityManager;
    private static NetworkInfo networkIno;

    public IsThereAnyDealApiHelper(Context context, IsThereAnyDealOnResponseFinished onResponseFinished) {
        this.context = context;
        this.onResponseFinished = onResponseFinished;
        connectivityManager =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkIno = connectivityManager.getActiveNetworkInfo();


    }

    public interface IsThereAnyDealOnResponseFinished{
        void onPlainRecieved(String plain);
        void onGameRecieved();
    }
    public static void getGameLowestPrice(String plain){
        if (networkIno != null && networkIno.isConnected()) {

            Call<IsThereAnyDealTitleQuery> call = dealService.getPlain(APIKEY, "terraria");

            call.enqueue(new Callback<IsThereAnyDealTitleQuery>() {
                @Override
                public void onResponse(Call<IsThereAnyDealTitleQuery> call, Response<IsThereAnyDealTitleQuery> response) {
                    try {
                        double lowestPrice = -1;
                        Data data = response.body().getData();
                        int dataLength = data.size();



                        for (int i = 0; i < dataLength; i++) {



                        }
                        onResponseFinished.onGameRecieved();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<IsThereAnyDealTitleQuery> call, Throwable t) {

                }
            });

        }

    }
    public static void getPlain(String title) {


        if (networkIno != null && networkIno.isConnected()) {

            Call<IsThereAnyDealTitleQuery> call = dealService.getPlain(APIKEY, title);

            call.enqueue(new Callback<IsThereAnyDealTitleQuery>() {
                @Override
                public void onResponse(Call<IsThereAnyDealTitleQuery> call, Response<IsThereAnyDealTitleQuery> response) {
                    try {
                        onResponseFinished.onPlainRecieved(response.body().getData().getPlain());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<IsThereAnyDealTitleQuery> call, Throwable t) {

                }
            });

        }
    }
}
