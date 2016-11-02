package test.magiclistas.API;

import android.widget.Toast;

import java.util.ArrayList;

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

    private  String url = "https://api.magicthegathering.io/v1/cards?pageSize=100";

    public ArrayList<Carta> getCartas() {

        ArrayList<Carta> lista = new ArrayList<>();

        try {
            String JsonResponse = HttpUtils.get(url);
            JSONObject json = new JSONObject(JsonResponse);
            JSONArray jsonNombre = json.getJSONArray("cards");

            String nombreCarta;
            String tipoCarta;
            String color = "";
            String imagen = null;
            String texto = null;

            for (int i = 0; i < jsonNombre.length(); ++i) {

                JSONObject object = jsonNombre.getJSONObject(i);
                // JSONArray ja_data = object.getJSONArray("data");
                // int length = object .length();

                nombreCarta = object.getString("name");
                tipoCarta = object.getString("rarity");

                if (object.has("imageUrl")) {
                    imagen = object.getString("imageUrl");
                }

                if (object.has("text")) {
                    texto = object.getString("text");
                };

                /*for(int it = 0; it < length; it++) {
                    JSONArray ja = object.getJSONArray("colors");
                    int len = ja.length();
                    for(int j=0; j<len; j++)
                    {
                        JSONObject jeson = ja.getJSONObject(j);
                       color =jeson.getString("name");
                    }
                }*/

                // Si tiene color
                String [] colors=null;

                if (object.has("colors")) {

                    int totalColors = object.getJSONArray("colors").length();
                    colors = new String[totalColors];

                    for (int j = 0; j <colors.length ; j++) {
                        colors[j] = object.getJSONArray("colors").get(j).toString();
                    }
                }

                Carta carta = new Carta(nombreCarta, tipoCarta, colors, imagen, texto);
                lista.add(carta);
            }

        }

        catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
