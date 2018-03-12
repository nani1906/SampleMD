package com.bandari.naveen.samplemd;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by suresh on 8/3/18.
 */

public class Favorites {
    private static Favorites instance;
//    private String[] favorites;
    private ArrayList<String> favorites;

    public ArrayList<String> getFavorites() {
        if(favorites == null) {
            favorites = new ArrayList<>();
        }
        return favorites;
    }

    public void setFavorites(ArrayList favorites) {
        this.favorites = favorites;
    }

    private Favorites(){
    }
    public static Favorites getInstance(){
        if(instance == null)
            instance = new Favorites();
        return instance;
    }

    public void removeFavorite(String favourite){
        ArrayList<String> newFavorites = new ArrayList<>();
        for(String fav: favorites){
            if(!fav.equalsIgnoreCase(favourite)){
                newFavorites.add(fav);
            }
        }
        Log.e("IN Removing favorites","=============="+favorites.size());
        this.favorites = newFavorites;
    }

    public void addFavorite(String favourite){
        this.favorites.add(favourite);
        Log.e("IN Adding favorites","=============="+favorites.size());
    }
}