package com.example.patrickcummins.videogamepricetracker;

/**
 * Created by patrickcummins on 8/23/16.
 */
import com.example.patrickcummins.videogamepricetracker.Models.IGDBModels.Game;
import com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels.IsThereAnyDealTitleQuery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IsThereAnyDealService {

    @GET("v02/game/plain/?")
    Call<IsThereAnyDealTitleQuery> getPlain(@Query("key") String key, @Query("title") String title);


    @Headers({
            "X-Mashape-Key: 3P7A7rIC6Hmsha9qRsKRQhtfFyzdp1jzLDFjsnKqCuqCJMkQhF"
    })
    @GET("games/?")
    Call<List<Game>> getGamesList(@Query("fields") String fields, @Query("limit") String limit, @Query("order") String order, @Query("search") String search);



}
