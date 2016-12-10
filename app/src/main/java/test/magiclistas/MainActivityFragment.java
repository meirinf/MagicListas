package test.magiclistas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.alexvasilkov.events.Events;

import test.magiclistas.databinding.FragmentMainBinding;

public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private CartasCursorAdapter adapter;
    private ProgressDialog dialog;

    public MainActivityFragment() {}

    @Override
    public void onStart() {
        super.onStart();
        Events.register(this);
        refresh();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //Agregamos el menu en el fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentMainBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        View view = binding.getRoot();

        // Dialog
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");

        adapter = new CartasCursorAdapter(getContext(), Carta.class);
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

        getLoaderManager().initLoader(0, null, this);

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
        else if (item.getItemId() == R.id.deleteDB){
            DataManager.borrarCartas(getContext());
        }
        else if (item.getItemId() == R.id.Config) {
            Intent a = new Intent(getContext(), ConfigActivity.class);
            startActivity(a);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Events.Subscribe("start-downloading-data")
    public void preRefresh() {
        dialog.show();
    }

    @Events.Subscribe("finish-downloading-data")
    public void afterRefresh() {
        dialog.dismiss();
    }

    private void refresh() {
        ActualizarCartasTask task = new ActualizarCartasTask(getActivity().getApplicationContext());
        task.execute();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
