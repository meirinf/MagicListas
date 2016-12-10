package test.magiclistas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
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

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import nl.littlerobots.cupboard.tools.provider.UriHelper;
import test.magiclistas.API.ApiCartas;
import test.magiclistas.API.ContentProvider;
import test.magiclistas.Configracion.ConfigActivity;
import test.magiclistas.Objetos.Carta;
import test.magiclistas.databinding.FragmentMainBinding;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class MainActivityFragment extends Fragment {

    private List<Carta> items;
    private CartasAdapter adapter;
    private ListView cartas;
    private boolean ok = true;

    public MainActivityFragment() {
    }


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


    //Agregamos el menu en el fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final FragmentMainBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);

        View view = binding.getRoot();
        items = new ArrayList<>();
        adapter = new CartasAdapter(getContext(),
                R.layout.adapter_cartas,
                items
        );

        binding.Cartas.setAdapter(adapter);


        // Al pulsar en una posicion se ejecuta el onClick
        binding.Cartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Carta card = (Carta) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getContext(), details.class);
                intent.putExtra("card", card);
                startActivity(intent);
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
        ActualizarCartas task = new ActualizarCartas();
        task.execute();
    }


    public class ActualizarCartas extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            ApiCartas api = new ApiCartas();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

            //Asignamos el nombre en pref_general
            String rarity = preferences.getString("categoriaCarta", " ");
            String colors = preferences.getString("colorCarta", " ");

            ArrayList<Carta> cards;
            cards = api.getCardsTypes(rarity, colors);

            Log.d("DEBUG", cards != null ? cards.toString() : null);

            UriHelper helper = UriHelper.with(ContentProvider.AUTHORITY);
            Uri cardUri = helper.getUri(Carta.class);
            cupboard().withContext(getContext()).put(cardUri, Carta.class, cards);

            //return cards;
            //}

       /* //CLase que sirve para aplicar los ajustes
        @Override
        protected void onPostExecute(ArrayList<Carta> cards) {
            super.onPostExecute(cards);
            adapter.clear();
            for (int i = 0; i < cards.size(); ++i) {
                adapter.add(cards.get(i));
            }
            adapter.notifyDataSetChanged();

        }*/
            return null;
        }
    }
}
