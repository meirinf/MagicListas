package test.magiclistas.API;


import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import test.magiclistas.BuildConfig;
import test.magiclistas.Objetos.Carta;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class ContentProvider extends CupboardContentProvider{

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Carta.class);
    }

    public ContentProvider() {
        super(AUTHORITY, 1);
    }



}
