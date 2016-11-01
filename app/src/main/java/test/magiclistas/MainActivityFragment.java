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
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import org.jetbrains.annotations.Nullable;

public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {}

    //Agregamos el menu en el fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView cartas = (ListView) view.findViewById(R.id.Cartas);

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

        cartas.setAdapter(adapter);

        return view;
    }

    //Creamos el inflate del menu
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    //Configuramos la opción refresh del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Refresh) {
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        RefreshAsyncTask refreshAsyncTask = new RefreshAsyncTask();
        refreshAsyncTask.execute();
    }

    class RefreshAsyncTask extends AsyncTask<Void, Void, ArrayList<Carta>> {

        @Override
        protected ArrayList<Carta>doInBackground(Void... voids) {
            ApiCartas api = new ApiCartas();
            ArrayList<Carta> cards = api.getCartas();
            return cards;
        }

        @Override
        protected void onPostExecute(ArrayList<Carta> cards) {

            adapter.clear();
            for (int i = 0; i < cards.size(); ++i) {
                adapter.add(cards.get(i).getNombre());
            }

            Toast.makeText(getContext(), "Se han cargado " + cards.size() + " cartas.", Toast.LENGTH_SHORT).show();

        }
    }

}
