package com.example.piroacc.myapplication.fetchers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.fetchers.objects.PokemonEntity;
import com.example.piroacc.myapplication.fetchers.objects.PokemonGson;
import com.example.piroacc.myapplication.fetchers.objects.SearchResponseGson;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PiroACC on 2015-11-17.
 */
public class GsonParser extends AsyncTask<Void, Void, String> {

    private final static String LOG_TAG = "LOGGER MOWI";

    @Override
    protected String doInBackground(Void... params) {
        getPokemons();
        return null;
    }

    public PokemonGson[] getPokemons() {
        Reader content = request();
        Log.d(LOG_TAG, "Result content:" + content);
        Gson gson = new Gson();
        PokemonGson[] result =  gson.fromJson(content, PokemonGson[].class);
        Log.i(LOG_TAG, "getPokemons: " + result);
        for(PokemonGson pok:result){
            Log.i(LOG_TAG, pok.getName());
        }
        return result;
    }

    public Reader request() {
        URL url = null;
        try {
            url = new URL("http://192.168.0.11:8080/test/rest/members");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            return new InputStreamReader(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
