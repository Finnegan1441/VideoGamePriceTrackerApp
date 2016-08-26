package com.example.patrickcummins.videogamepricetracker;

/**
 * Created by patrickcummins on 8/23/16.
 */

import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.GameSearchResult;
import com.example.patrickcummins.videogamepricetracker.Models.IGDBModels.Game;
import com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels.IsThereAnyDealTitleQuery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("v02/game/plain/?")
    Call<IsThereAnyDealTitleQuery> getPlain(@Query("key") String key,
                                            @Query("title") String title);

    @GET("v01/game/prices/?")
    Call<IsThereAnyDealTitleQuery> getPrices(@Query("key") String key,
                                             @Query("plains") String plains);


    @GET("search/?")
    Call<GameSearchResult> getGamesList(@Query("api_key") String api_key,
                                        @Query("format") String format,
                                        @Query("limit") String limit,
                                        @Query("resource_type") String resource_type,
                                        @Query("query") String query);

    @GET("games/?")
    Call<GameSearchResult> getGame(@Query("api_key") String api_key,
                                   @Query("format") String format,
                                   @Query("limit") String limit,
                                   @Query("filter") String id);


}
