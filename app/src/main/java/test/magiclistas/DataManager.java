package test.magiclistas;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import java.util.ArrayList;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import test.magiclistas.API.ContentProvider;
import test.magiclistas.Objetos.Carta;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by meirinf on 20/12/16.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(ContentProvider.AUTHORITY);
    private static Uri CARTA_URI = URI_HELPER.getUri(Carta.class);

    static void guardarCarta(ArrayList<Carta> movies, Context context) {
        cupboard().withContext(context).put(CARTA_URI, Carta.class, movies);
    }

    static void borrarCarta(Context context) {
        cupboard().withContext(context).delete(CARTA_URI, "_id > ?", "0");
    }

    static CursorLoader getCursorLoader(Context context) {
        return new CursorLoader(context, CARTA_URI, null, null, null, null);
    }

}