package com.example.patrickcummins.videogamepricetracker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patrickcummins.videogamepricetracker.Helpers.DatabaseHelper;
import com.example.patrickcummins.videogamepricetracker.Helpers.GiantBombHelper;
import com.example.patrickcummins.videogamepricetracker.Helpers.IsThereAnyDealApiHelper;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.GameSearchResult;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.Result;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class GameViewActivity extends AppCompatActivity implements IsThereAnyDealApiHelper.IsThereAnyDealOnResponseFinished, GiantBombHelper.GiantBombOnResponseFinished {
    private TextView title, releaseDate, lowestPrice;
    private ImageView image;
    private Result currentGame;
    private IsThereAnyDealApiHelper apiHelper;
    private DatabaseHelper dataBaseHelper;
    private GiantBombHelper giantBombHelper;
    private Button saveGame;
    public static final String GAME_ID = "id";
    public static final String RESOLVE_INTENT = "resolveIntent";
    public static final String MAIN_INTENT = "main";
    public static final String SEARCH_INTENT = "search";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
        apiHelper= new IsThereAnyDealApiHelper(GameViewActivity.this, this);
        dataBaseHelper = DatabaseHelper.getInstance(GameViewActivity.this);
        giantBombHelper = new GiantBombHelper(GameViewActivity.this, this);

        setViews();
        resolveIntent();



    }

    private void setOnClicks() {
        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.insertGameTableRow(currentGame);
            }
        });
    }

    private void fillViews() {
        title.setText(currentGame.getName());
        releaseDate.setText(currentGame.getOriginalReleaseDate().toString());
        Picasso picasso = new Picasso.Builder(GameViewActivity.this).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        }).build();
        try {
            picasso.load(currentGame.getImage().getSmall_url()).into(image, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("GameView", "Image Success");
                }

                @Override
                public void onError() {
                    Log.d("GameView", "Image Error");

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        apiHelper.getPlain("terraria");
    }

    private void resolveIntent() {
        Intent intent = getIntent();
        String fromIntent = intent.getStringExtra(RESOLVE_INTENT);
        if (fromIntent.equals(MAIN_INTENT)){
            saveGame.setVisibility(View.GONE);
            giantBombHelper.getGame(intent.getIntExtra(GAME_ID, -1));


        }else if (fromIntent.equals(SEARCH_INTENT)) {
            currentGame = (Result) intent.getSerializableExtra(SearchFragment.CurrentResultExtra);
            setOnClicks();
            fillViews();
        }

    }

    private void setViews() {
        title = (TextView) findViewById(R.id.game_title);
        releaseDate = (TextView) findViewById(R.id.release_date);
        lowestPrice = (TextView) findViewById(R.id.lowest_price);
        image = (ImageView) findViewById(R.id.game_image);
        saveGame = (Button) findViewById(R.id.save_game_button);
    }


    @Override
    public void onPlainRecieved(String plain) {
        apiHelper.getGameLowestPrice(plain);

    }

    @Override
    public void onGameRecieved() {

    }

    @Override
    public void OnGamesRecieved(GameSearchResult gameSearchResult) {

    }

    @Override
    public void OnSpecificGameRecieved(Result result) {
        currentGame =result;
        setOnClicks();
        fillViews();
    }
}

