package com.bandari.naveen.samplemd.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bandari.naveen.samplemd.Favorites;
import com.bandari.naveen.samplemd.R;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Favourites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView noFavorites = (ImageView) findViewById(R.id.noFavorites);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        if(Favorites.getInstance().getFavorites().size() > 0){
            noFavorites.setVisibility(View.GONE);
            FavouritesActivity.ContentAdapter adapter = new FavouritesActivity.ContentAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else {
            noFavorites.setVisibility(View.VISIBLE);
            Snackbar sb = Snackbar.make(recyclerView, "Oops..!! There are no Favorites. \nPlease mark as favourites from list of places.", Snackbar.LENGTH_LONG);
            sb.setAction("ADD", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FavouritesActivity.this.finish();
                }
            });
            sb.setActionTextColor(ResourcesCompat.getColor(getResources(),R.color.colorAccent, null));
            sb.setDuration(10000);
            sb.show();
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.favourite_item_list, parent, false));
            name = (TextView) itemView.findViewById(R.id.fav_list_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }
    /**
     * Adapter to dis   play recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<FavouritesActivity.ViewHolder> {
        // Set numbers of List in RecyclerView.
        private ArrayList<String> mFavourites = new ArrayList<>();

        public ContentAdapter(Context context) {
            mFavourites = Favorites.getInstance().getFavorites();
        }

        @Override
        public FavouritesActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FavouritesActivity.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final FavouritesActivity.ViewHolder holder, int position) {
            holder.name.setText(mFavourites.get(position));
        }

        @Override
        public int getItemCount() {
            return mFavourites.size();
        }
    }

}
