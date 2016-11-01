package test.magiclistas.API;

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
            String imagen;
            String texto;

            for (int i = 0; i < jsonNombre.length(); ++i) {

                JSONObject object = jsonNombre.getJSONObject(i);

                nombreCarta = object.getString("name");
                tipoCarta = object.getString("rarity");
                imagen = object.getString("imageUrl");
                texto = object.getString("text");

                Carta carta = new Carta(nombreCarta, tipoCarta, color, imagen, texto);
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
