package test.magiclistas;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class ContentProvider extends CupboardContentProvider {
    // The content provider authority is used for building Uri's for the provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Carta.class);
    }

    public ContentProvider() {
        super(AUTHORITY, 1);
    }
}