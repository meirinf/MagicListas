package test.magiclistas;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mireia on 30/10/16.
 */

public class ApiCartas {

    private static String url = "https://api.magicthegathering.io/v1/cards?pageSize=100";
    private static ArrayList<Carta> carta = new ArrayList<>();

    //Esta clase se encarga de bajar la información de la Api
    public static ArrayList<Carta> getCardsTypes(String rar, String color) {
        //Aqui pasa los parametros de configuración rareza y color
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
            //se cargan todos los parametros elejidos
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


