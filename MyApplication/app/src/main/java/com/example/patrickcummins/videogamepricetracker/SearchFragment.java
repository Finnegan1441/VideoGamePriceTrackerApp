package com.example.patrickcummins.videogamepricetracker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.patrickcummins.videogamepricetracker.Helpers.GiantBombHelper;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.GameSearchResult;
import com.example.patrickcummins.videogamepricetracker.Models.IGDBModels.Game;

import java.util.List;

/**
 * Created by patrickcummins on 8/24/16.
 */
public class SearchFragment extends android.support.v4.app.Fragment implements GiantBombHelper.GiantBombOnResponseFinished{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        EditText editText = (EditText) getView().findViewById(R.id.game_search_edit_text);
        final GiantBombHelper giantBombHelper = new GiantBombHelper(getContext(), SearchFragment.this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                giantBombHelper.getGame(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setGamesList(List<Game> gamesList){
        ListView gamesListView = (ListView) getView().findViewById(R.id.games_list);

        TextView gameTextView = (TextView) getView().findViewById(R.id.game_tv);

        gameTextView.setText(gamesList.get(0).getName());
    }

    @Override
    public void OnGamesRecieved(GameSearchResult gameSearchResult) {
        TextView gameTextView = (TextView) getView().findViewById(R.id.game_tv);

        gameTextView.setText(gameSearchResult.getResults().get(0).getName());
    }
}
