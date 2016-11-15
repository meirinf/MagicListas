package test.magiclistas;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import test.magiclistas.API.ApiCartas;
import test.magiclistas.Configracion.ConfigActivity;

public class MainActivityFragment extends Fragment {

    private List<Carta> items;
    private CartasAdapter adapter;
    private ListView cartas;
    private boolean ok = true;

    public MainActivityFragment() {
    }

    //Agregamos el menu en el fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        cartas = (ListView) view.findViewById(R.id.Cartas);
        items = new ArrayList<>();
        adapter = new CartasAdapter(getContext(), 0, items);
        cartas.setAdapter(adapter);

        // Al pulsar en una posicion del listView se ejecuta el onClick
        cartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent details = new Intent(getContext(), DetailsActivity.class);
                details.putExtra("carta", items.get(position));
                startActivity(details);
            }
        });

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
        } else if (item.getItemId() == R.id.Config) {
            Intent a = new Intent(getContext(), ConfigActivity.class);
            startActivity(a);
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
        protected ArrayList<Carta> doInBackground(Void... voids) {

            ApiCartas api = new ApiCartas();
            ArrayList<Carta> cards = null;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String rarity  = preferences.getString("Rarity", "Rare");
            String color = preferences.getString("Colors","Red");




            if (rarity.equals("Rarity")||color.equals("Colors")) {
                               cards = api.getAllCards();
                           } else {
                                cards = api.getCardsTypes(rarity,color);
                           }
            // Despues de actualizar los datos movemos el listView hacia arriba
            // cartas.smoothScrollToPosition(0);
            Log.d("DEBUG", cards != null ? cards.toString() : null);





            return cards;
        }



        //CLase que sirve para aplicar los ajustes
        @Override
        protected void onPostExecute(ArrayList<Carta> cards) {
            super.onPostExecute(cards);
            adapter.clear();
            for (int i = 0; i < cards.size(); ++i) {
                adapter.add(cards.get(i));
            }
            adapter.notifyDataSetChanged();

        }
    }

}
