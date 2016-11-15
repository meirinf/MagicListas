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

    private static String url = "https://api.magicthegathering.io/v1/cards?pageSize=100";

   public ArrayList<Carta> getAllCards() {
        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }



    public ArrayList<Carta> getCardsTypes(String color,String rar) {
        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendQueryParameter("rarity", rar)
                .appendQueryParameter("color", color)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    @Nullable
    private ArrayList<Carta> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Carta> processJson(String jsonResponse) {
        ArrayList<Carta> cards = new ArrayList<>();
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
                if(object.has("rarity"))
                    card.setRareza(object.getString("rarity"));
                if(object.has("colors")){
                    card.setColor(object.getString("colors"));
                }
                if (object.has("text")) {
                    card.setTexto(object.getString("text"));
                };
                if (object.has("imageUrl")) {
                    card.setImagen(object.getString("imageUrl"));
                }
                carta.add(card);

            }

            return carta;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cards;
    }


}


