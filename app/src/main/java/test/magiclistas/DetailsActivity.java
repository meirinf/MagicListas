
package test.magiclistas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.databinding.DataBindingUtil;

import test.magiclistas.databinding.ContentDetailsBinding;


import com.bumptech.glide.Glide;


public class DetailsActivity extends AppCompatActivity {

    private ContentDetailsBinding binding;

    public DetailsActivity() {

    }
    protected View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.content_details, container, false);
        View view = binding.getRoot();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Recogemos el intent y cargamos la carta que hemos pasado desde el MainActivity
        Intent intent = getIntent();

        if (intent != null) {

            Carta carta = (Carta) intent.getSerializableExtra("carta");

            if (carta != null) {
                Log.d("carta", carta.toString());

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
        return view;
    }
}