package com.example.patrickcummins.videogamepricetracker;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrickcummins on 8/25/16.
 */
public class GameSearchRecyclerAdapter extends RecyclerView.Adapter<GameSearchRecyclerAdapter.MyViewHolder> {
    private List<Result> data;
    private Context context;
    private OnGameSearchRecyclerClickListener listener;
    public GameSearchRecyclerAdapter(List<Result> data, OnGameSearchRecyclerClickListener listener) {
        this.listener = listener;

        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<Result>();
        }
    }

    public interface OnGameSearchRecyclerClickListener {
        void onItemClick(Result currentResult);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView gameImage;
        public TextView gameTitleTV;


        public MyViewHolder(View itemView) {
            super(itemView);
            gameImage = (ImageView) itemView.findViewById(R.id.game_image);
            gameTitleTV = (TextView) itemView.findViewById(R.id.game_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(data.get(getLayoutPosition()));
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listItemLayout = inflater.inflate(R.layout.item_game_search_recycler, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItemLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result currentResult = data.get(position);
        TextView title = holder.gameTitleTV;
        ImageView image = holder.gameImage;
        title.setText(currentResult.getName());
        Picasso picasso = new Picasso.Builder(holder.gameImage.getContext()).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        }).build();
        try{
        picasso.load(currentResult.getImage().getIcon_url()).into(image, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("Adapter", "Success");

            }

            @Override
            public void onError() {
                Log.d("Adapter", "No Success");

            }
        });}
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
