package com.example.patrickcummins.videogamepricetracker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by patrickcummins on 8/24/16.
 */
public class SearchFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    public void setGamesList(List gamesList){
        ListView gamesListView = (ListView) getView().findViewById(R.id.games_list);

        TextView gameTextView = (TextView) getView().findViewById(R.id.game_tv);
    }
}
