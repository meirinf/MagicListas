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
    ArrayList<Carta> carta = new ArrayList<>();

    public ArrayList<Carta> getCardsTypes(String rar,String color) {
        Uri builtUri = Uri.parse(url)

                .buildUpon()
                .appendQueryParameter("rarity", rar)
                .appendQueryParameter("colors", color)
                .build();
        String urls = builtUri.toString();
        try {
            String JsonResponse = HttpUtils.get(urls);

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return carta;
    }


}


