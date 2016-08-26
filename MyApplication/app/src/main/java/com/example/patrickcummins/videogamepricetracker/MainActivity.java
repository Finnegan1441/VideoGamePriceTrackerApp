package com.example.patrickcummins.videogamepricetracker;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patrickcummins.videogamepricetracker.Helpers.DatabaseHelper;
import com.example.patrickcummins.videogamepricetracker.Helpers.GiantBombHelper;
import com.example.patrickcummins.videogamepricetracker.Helpers.IsThereAnyDealApiHelper;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.GameSearchResult;
import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.Result;
import com.example.patrickcummins.videogamepricetracker.Models.IGDBModels.GamesList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IsThereAnyDealApiHelper.IsThereAnyDealOnResponseFinished, GiantBombHelper.GiantBombOnResponseFinished{
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private ArrayList<String> drawerList = new ArrayList<>();
    private Toolbar toolbar;
    private DatabaseHelper databaseHelper;
    private GiantBombHelper giantBombHelper;
    private final String GAME_ID_EXTRA = "gameID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        databaseHelper = DatabaseHelper.getInstance(MainActivity.this);
        giantBombHelper= new GiantBombHelper(MainActivity.this, this);

        setDrawerList();

        setViews();
        setUpNavigationDrawer();
        setUpFragment();


    }

    private void setUpFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SearchFragment fragment = new SearchFragment();


        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void setDrawerList() {


        ArrayList<String> gameList = databaseHelper.getAllSavedGames();
        for (int i = 0; i < gameList.size(); i++) {
            drawerList.add(gameList.get(i));
        }
    }

    private void setViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

    }


    private void setUpNavigationDrawer() {
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, drawerList));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mActivityTitle = (String) getSupportActionBar().getTitle();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

    }

    @Override
    public void onPlainRecieved(String plain) {
        Toast.makeText(MainActivity.this, plain, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGameRecieved() {

    }

    @Override
    public void OnGamesRecieved(GameSearchResult gameSearchResult) {

    }

    @Override
    public void OnSpecificGameRecieved(Result result) {


        Intent intent = new Intent(MainActivity.this, GameViewActivity.class);
        intent.putExtra(SearchFragment.CurrentResultExtra, result);
        startActivity(intent);

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        int id = databaseHelper.getGameId(position);
        Intent intent =  new Intent(MainActivity.this, GameViewActivity.class);
        intent.putExtra(GameViewActivity.RESOLVE_INTENT, GameViewActivity.MAIN_INTENT);
        intent.putExtra(GameViewActivity.GAME_ID, id);
        startActivity(intent);

    }
}
