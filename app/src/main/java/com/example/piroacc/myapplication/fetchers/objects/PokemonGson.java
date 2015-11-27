package com.example.piroacc.myapplication.fetchers.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PiroACC on 2015-11-14.
 */
public class PokemonGson {

    @SerializedName("idpokemon")
    private int idpokemon;
    @SerializedName("name")
    private String name;

    public String getName(){
        return name;
    }

}
