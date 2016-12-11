package test.magiclistas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.alexvasilkov.events.Events;

import java.util.ArrayList;

/**
 * Created by mireia on 10/12/16.
 */
class ActualizarCartasTask extends AsyncTask<Void, Void, Void> {
    private Context context;

    //Actualiza la petición de cartas
    ActualizarCartasTask(Context context) {
        this.context = context;
    }

    //devuelbe un menjase cuando se inicia
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Events.post("start-downloading-data");
    }

    @Override
    protected Void doInBackground(Void... voids) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        //Asignamos el nombre en pref_general
        String rarity = preferences.getString("categoriaCarta", " ");
        String colors = preferences.getString("colorCarta", " ");

        //Cargamos las cartas
        ArrayList cards;
        cards = ApiCartas.getCardsTypes(rarity, colors);

        Log.d("DEBUG", cards != null ? cards.toString() : null);

        DataManager.borrarCartas(context);
        DataManager.guardarCartas(cards, context);

        return null;
    }

    //devuelbe un mensaje cuando se ha ejecutado
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Events.post("finish-downloading-data");
    }
}