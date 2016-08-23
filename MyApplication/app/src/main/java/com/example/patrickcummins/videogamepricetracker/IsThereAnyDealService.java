package com.example.patrickcummins.videogamepricetracker;

/**
 * Created by patrickcummins on 8/23/16.
 */
import com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels.IsThereAnyDealTitleQuery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IsThereAnyDealService {

    @GET("v02/game/plain/?")
    Call<IsThereAnyDealTitleQuery> getPlain(@Query("key") String key, @Query("title") String title);


}
