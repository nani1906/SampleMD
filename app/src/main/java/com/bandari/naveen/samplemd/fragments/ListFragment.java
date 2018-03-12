package com.bandari.naveen.samplemd.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bandari.naveen.samplemd.Favorites;
import com.bandari.naveen.samplemd.R;
import com.bandari.naveen.samplemd.activities.DetailActivity;
import com.bandari.naveen.samplemd.adapters.Place;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    Context mContext;
    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public ImageView favourite;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            favourite = (ImageView) itemView.findViewById(R.id.list_favorite);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    context.startActivity(intent);
                }
            });
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;
        private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final Drawable[] mPlaceAvators;
        private final ArrayList<Place> placesList = new ArrayList<>();
        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.places);
            mPlaceDesc = resources.getStringArray(R.array.place_desc);
            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            mPlaceAvators = new Drawable[a.length()];
            for (int i = 0; i < mPlaceAvators.length; i++) {
                mPlaceAvators[i] = a.getDrawable(i);
            }
            a.recycle();
            for (int i = 0; i < mPlaces.length; i++){
                Place place = new Place();
                place.setName(mPlaces[i]);
                place.setDesciption(mPlaceDesc[i]);
                place.setAvatar(mPlaceAvators[i]);
                placesList.add(place);
            }


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Place mPlaceObject = placesList.get(position);
            Favorites favorites = Favorites.getInstance();
            if(favorites.getFavorites().contains(mPlaceObject.getName())) {
                mPlaceObject.isFavourite = true;
            }
            holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
            holder.name.setText(mPlaceObject.getName());
            if(mPlaceObject.isFavourite){
                holder.favourite.setImageResource(R.drawable.ic_favorite_black_24dp);
            }else {
                holder.favourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
            holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
            holder.favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("ON CLICK::: ","================"+mPlaceObject.isFavourite);
                    if(mPlaceObject.isFavourite) {
                        Log.e("in isFavorites::: ","================"+mPlaceObject.isFavourite);
                        mPlaceObject.isFavourite = false;
                        Favorites.getInstance().removeFavorite(mPlaceObject.getName());
                        holder.favourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    }else {
                        Log.e("ELSE isFavorites::: ","================"+mPlaceObject.isFavourite);
                        mPlaceObject.isFavourite = true;
                        Favorites.getInstance().addFavorite(mPlaceObject.getName());
                        holder.favourite.setImageResource(R.drawable.ic_favorite_black_24dp);
                    }
                    Log.e("ON CLICK:::AFTER","================"+mPlaceObject.isFavourite);
                    Log.e("list of Favorites::: ","================"+Favorites.getInstance().getFavorites().toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return placesList.size();
        }
    }
}
