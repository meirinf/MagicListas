package test.magiclistas;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
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

    public MainActivityFragment() {}

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
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
        }
        else if(item.getItemId() == R.id.Config){
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
            ArrayList<Carta> cards = api.getCartas();
            return cards;
        }

        //CLase que sirve para aplicar los ajustes
        @Override
        protected void onPostExecute(ArrayList<Carta> cards) {

            adapter.clear();

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());

            String filtroComun = pref.getString("categoriaCarta", "");
            String filtroColor = pref.getString("colorCarta", "");


            //Miro que las cartas cumplan con los ajustes
            for (int i = 0; i < cards.size(); ++i) {
                if(filtroComun.equalsIgnoreCase("Empty")){
                    filtroComun = "";
                }
                if (filtroColor.equalsIgnoreCase("")||filtroColor.isEmpty()) {
                    adapter.add(cards.get(i));
                }
                //Esto se encarga de que el valor introducido en color exista y no pete si no existe
                if(filtroColor.equalsIgnoreCase("White")||filtroColor.equalsIgnoreCase("Red")||filtroColor.equalsIgnoreCase("Black")||filtroColor.equalsIgnoreCase("Blue")||filtroColor.equalsIgnoreCase("Green")){
                    configColor(cards.get(i), filtroColor);
                }else if(!filtroColor.isEmpty()){
                    filtroColor="";
                    adapter.add(cards.get(i));
                    ok = false;
                }

                if (!filtroColor.isEmpty() && !filtroComun.isEmpty()) {
                    configGeneralTodo(cards.get(i), filtroColor,filtroComun);
                }
                else {
                    configGeneral(cards.get(i),filtroComun);
                }

            }

            // Despues de actualizar los datos movemos el listView hacia arriba
            cartas.smoothScrollToPosition(0);

            //Mostraremos el warning
            if(ok = true){Toast.makeText(getContext(), "Se han cargado " + cards.size() + " cartas.", Toast.LENGTH_SHORT).show();}
            if(ok = false){ Toast.makeText(getContext(), "El color introducido no exite", Toast.LENGTH_SHORT).show();}

        }

        public void configGeneral(Carta cards, String filtroComun) {
            if (cards.getTipo().equals(filtroComun)) {
                adapter.add(cards);
            }
        }

        public void configColor(Carta cards, String filtroColor) {
                for (int j = 0; j < cards.getColor().length; j++) {
                    if (cards.getColor()[j].equals(filtroColor)) {
                        adapter.add(cards);
                    }
                }
            }

        public void configGeneralTodo(Carta cards, String filtroColor, String filtroComun) {
            if (cards.getColor() != null) {
                for (int j = 0; j < cards.getColor().length; j++) {
                    if (cards.getColor()[j].equalsIgnoreCase(filtroColor)) {
                        if (cards.getTipo().equalsIgnoreCase(filtroComun)) {
                            adapter.add(cards);
                        }
                    }
                }
            }


        }
    }
}
