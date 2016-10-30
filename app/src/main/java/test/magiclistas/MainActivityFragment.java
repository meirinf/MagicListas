package test.magiclistas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    public MainActivityFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ListView Cartas = (ListView) view.findViewById(R.id.Cartas);

        //Creamos probisionalmente un Array de Strings
        String[] data = {
                "Mir de plata",
                "Acelerador del laberinto",
                "Afán",
                "Animista salvaje",
                "Azotacielos",
                "Baron sangriento de vizkopa",
                "Behemot del laberinto."
        };
        //Añadimos los Strings a una array dinamica
        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.layoutcartasrow,
                R.id.Cartas,
                items
        );
        Cartas.setAdapter(adapter);
        return view;
    }
    //creamos el inflate
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

        }

    }
