package com.example.piroacc.myapplication.fetchers;

        import android.os.AsyncTask;
        import android.util.Log;

        import com.example.piroacc.myapplication.fetchers.objects.PokemonEntity;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.json.JSONTokener;

        import java.io.BufferedInputStream;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.StringWriter;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by PiroACC on 2015-11-17.
 */
public class DataFetcher extends AsyncTask<Void,Void,String>{

    private final static String LOG_TAG = "LOGGER MOWI";
    @Override
    protected String doInBackground(Void... params) {
        getPokemons();
        return null;
    }
    public List<PokemonEntity> getPokemons() {
        List<PokemonEntity> result = new ArrayList<PokemonEntity>();
        String content = request();
        Log.d(LOG_TAG, "Result content:" + content);
        if (content != null) {
            try {
                JSONTokener tokener = new JSONTokener(content);
                JSONArray array = (JSONArray) tokener.nextValue();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String idpokemon = obj.getString("idpokemon");
                    int idpokemonint = Integer.parseInt(idpokemon);
                    String name = obj.getString("name");
                    result.add(new PokemonEntity(idpokemonint, name));
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        Log.i(LOG_TAG, "getPokemons: " + result);
        return result;
    }
    public String request() {
        URL url = null;
        try {
            url = new URL("http://192.168.0.11:8080/test/rest/members");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
