package test.magiclistas;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }

    //List view con todas las cartas
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ListView Cartas = (ListView) view.findViewById(R.id.Cartas);
        //Creamos probisionalmente un Array de Strings
        String[] data = {
                                "Mir de plata",
                                "Mir de hierro",
                                "Afán",
                                "Azotacielos",
                                "Bahemot del laberinto",
                               };
        //Añadimos los Strings a una array dinamica 
        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.layoutcartasrow,
                items
        );
        Cartas.setAdapter(adapter);
        return view;
    }
}
