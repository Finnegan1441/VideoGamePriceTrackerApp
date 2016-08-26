package com.example.patrickcummins.videogamepricetracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patrickcummins.videogamepricetracker.Helpers.GiantBombHelper;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.GameSearchResult;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.Result;
import com.example.patrickcummins.videogamepricetracker.Models.IGDBModels.Game;

import java.util.List;

/**
 * Created by patrickcummins on 8/24/16.
 */
public class SearchFragment extends android.support.v4.app.Fragment implements GiantBombHelper.GiantBombOnResponseFinished, GameSearchRecyclerAdapter.OnGameSearchRecyclerClickListener {
    private EditText editText;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    public static final String CurrentResultExtra = "CurrentResultExtra";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        editText = (EditText) getView().findViewById(R.id.game_search_edit_text);
        recyclerView = (RecyclerView) getView().findViewById(R.id.games_list);
        rvLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(rvLayoutManager);
        final GiantBombHelper giantBombHelper = new GiantBombHelper(getContext(), SearchFragment.this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                giantBombHelper.searchForGame(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void OnGamesRecieved(GameSearchResult gameSearchResult) {
        TextView gameTextView = (TextView) getView().findViewById(R.id.game_tv);


        gameTextView.setText(gameSearchResult.getResults().get(0).getName());
        rvAdapter = new GameSearchRecyclerAdapter(gameSearchResult.getResults(), this);

        recyclerView.setAdapter(rvAdapter);

    }

    @Override
    public void OnSpecificGameRecieved(Result result) {

    }

    @Override
    public void onItemClick(Result currentResult) {
        Intent intent = new Intent(getContext(), GameViewActivity.class);
        intent.putExtra(GameViewActivity.RESOLVE_INTENT, GameViewActivity.SEARCH_INTENT);
        intent.putExtra(CurrentResultExtra, currentResult);
        startActivity(intent);
    }
}
