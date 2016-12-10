package test.magiclistas;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


/**
 * Created by meirinf on 20/12/16.
 */

public class DataManager {
    private static UriHelper URI_HELPER = UriHelper.with(ContentProvider.AUTHORITY);
    private static Uri CARTA_URI = URI_HELPER.getUri(Carta.class);

    static void guardarCartas(ArrayList<Carta> cartas, Context context) {
        cupboard().withContext(context).put(CARTA_URI, Carta.class, cartas);
    }

    static void borrarCartas(Context context) {
        cupboard().withContext(context).delete(CARTA_URI, "_id > ?", "0");
    }

    static CursorLoader getCursorLoader(Context context) {
        return new CursorLoader(context, CARTA_URI, null, null, null, null);
    }

}