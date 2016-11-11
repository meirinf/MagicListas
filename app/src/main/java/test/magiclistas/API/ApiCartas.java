package test.magiclistas.API;

import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;
import android.util.Log;
import android.support.annotation.Nullable;
/**
 * Created by mireia on 30/10/16.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import test.magiclistas.API.HttpUtils;
import test.magiclistas.Carta;


public class ApiCartas {

    private static String url = "https://api.magicthegathering.io/v1/cards";
    //https://api.magicthegathering.io/v1/cards?pageSize=100
    public ArrayList<Carta> getAllCards(){
        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        try {
            String JsonResponse = HttpUtils.get(url);
            ArrayList<Carta> carta = new ArrayList<>();

            JSONObject data = new JSONObject(JsonResponse);
            JSONArray jsonCartas = data.getJSONArray("cards");

            for (int i = 0; i <jsonCartas.length() ; i++) {
                Carta card = new Carta();
                JSONObject object = jsonCartas.getJSONObject(i);
                card.setNombre(object.getString("name"));
                card.setTipo(object.getString("type"));
                card.setRareza(object.getString("rarity"));
                if(object.has("color")){
                    card.setColor(object.getString("color"));
                }
                card.setImagen(object.getString("imageUrl"));
                carta.add(card);

            }

            return carta;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    String getCardsTypes(String pais) {
        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendPath("name")
                .appendPath("type")
                .appendPath("rarity")
                .appendPath("color")
                .appendPath("imageUrl")
                .build();
        String url = builtUri.toString();

        try {
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


