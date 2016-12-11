package test.magiclistas;

/**
 * Created by mireia on 10/12/16.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import test.magiclistas.databinding.AdapterCartasBinding;

public class CartasCursorAdapter extends CupboardCursorAdapter<Carta> {

    public CartasCursorAdapter(Context context, Class<Carta> entityClass) {
        super(context, entityClass);
    }
    //inflamos el adapter
    @Override
    public View newView(Context context, Carta model, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        AdapterCartasBinding binding = DataBindingUtil.inflate(inflater, R.layout.adapter_cartas, parent, false);

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Carta model) {

        AdapterCartasBinding binding = DataBindingUtil.getBinding(view);
        //pasamos la info del objeto al layaout
        binding.adapterNombre.setText("Nombre Carta: " + model.getNombre());
        binding.adapterTipo.setText("Tipo: " + model.getTipo());
        binding.adapterColor.setText("Color: " + model.getColor());
        Glide.with(context).load(model.getImagen()).into(binding.adapterCarta);
    }
}