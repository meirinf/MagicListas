package test.magiclistas;

import java.util.ArrayList;

/**
 * Created by mireia on 30/10/16.
 */

import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;


public class ApiCartas {
    private  String url= "https://api.magicthegathering.io/v1/cards?pageSize=100";

    public ArrayList<Carta> getCartas() {

        ArrayList<Carta> lista = new ArrayList<>();
        try {
            String JsonResponse = HttpUtils.get(url);
            JSONObject json = new JSONObject(JsonResponse);
            JSONArray jsoncartas = json.getJSONArray("cards");
            String titulo;
            for (int i = 0; i < jsoncartas.length(); ++i) {
                JSONObject object = jsoncartas.getJSONObject(i);
                titulo = object.getString("name");
                Carta carta=new Carta(titulo);
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
