package test.magiclistas;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexvasilkov.events.Events;
import com.bumptech.glide.Glide;

import test.magiclistas.databinding.FragmentDetailsBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class detailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    public detailsFragment() {}

    @Override
    public void onStart() {
        super.onStart();
        //conectamos el evento
        Events.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details, container, false);
        View view = binding.getRoot();


        // Recogemos el intent y cargamos la carta que hemos pasado desde el MainActivity
        Intent intent =getActivity().getIntent();

        if (intent != null) {

            Carta carta = (Carta) intent.getSerializableExtra("card");

            if (carta != null) {
                updateUi(carta);
            }
        }
        return view;
    }

    //Creamos el evento
    @Events.Subscribe("card-selected")
    private void cartaSelecionada(Carta card){
        updateUi(card);
    }

    private void updateUi(Carta carta ){

        //llamamos a la id con el binding del content details
        binding.detailsTexto.setText("Pequeña Descripción: " + carta.getTexto());
        binding.detailsNombreCarta.setText("Nombre Carta: " + carta.getNombre());
        binding.detailsRareza.setText("Rareza: " + carta.getRareza());
        binding.detailsTipo.setText("Tipo: " + carta.getTipo());
        binding.detailsColor.setText("Color: " + carta.getColor());

        //Creamos la imagen
        Glide.with(this)
                .load(carta.getImagen()).
                into(binding.detailsImagenCarta);

    }

}
