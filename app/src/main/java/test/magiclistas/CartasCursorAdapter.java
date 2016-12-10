package test.magiclistas;

/**
 * Created by mireia on 10/12/16.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.magiclistas.Objetos.Carta;
import test.magiclistas.Objetos.CupboardCursorAdapter;
import test.magiclistas.databinding.AdapterCartasBinding;

public class CartasCursorAdapter extends CupboardCursorAdapter<Carta> {

    public CartasCursorAdapter(Context context, Class<Carta> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Carta model, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        AdapterCartasBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, parent, false);

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Carta model) {

        AdapterCartasBinding binding = DataBindingUtil.getBinding(view);

        binding.adapterNombre.setText("Nombre Carta: " + model.getNombre());
        binding.adapterTipo.setText("Tipo: " + model.getTipo());
        binding.adapterColor.setText("Color: " + model.getColor());
    }
}