package com.example.patrickcummins.videogamepricetracker;

import android.content.Context;
import android.net.ConnectivityManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels.IsThereAnyDealTitleQuery;

/**
 * Created by patrickcummins on 8/23/16.
 */

public class IsThereAnyDealApiHelper {
    private static final String APIKEY = "";
    static final String BASE_URL = "https://api.isthereanydeal.com/";

    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private static IsThereAnyDealService dealService = retrofit.create(IsThereAnyDealService.class);
    private static Context context;
    private static OnResponseFinished onResponseFinished;

    public IsThereAnyDealApiHelper(Context context, OnResponseFinished onResponseFinished) {
        this.context = context;
        this.onResponseFinished = onResponseFinished;


    }

    public interface OnResponseFinished{
        public void onPlainRecieved(String plain);
    }

    public static void getPlain(String title) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkIno = connectivityManager.getActiveNetworkInfo();
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
                    Toast.makeText(context, "SHITS FUCKED YO", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
