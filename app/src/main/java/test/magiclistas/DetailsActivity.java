
package test.magiclistas;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import test.magiclistas.Objetos.Carta;
import test.magiclistas.databinding.ContentDetailsBinding;


public class DetailsActivity extends AppCompatActivity {

    private ContentDetailsBinding binding;

    public DetailsActivity() {

    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.activity_details, container, false);
        View view = binding.getRoot();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Recogemos el intent y cargamos la carta que hemos pasado desde el MainActivity
        Intent intent = this.getIntent();

        if (intent != null) {

            Carta carta = (Carta) intent.getSerializableExtra("carta");

            if (carta != null) {

                Log.d("Card", carta.toString());

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

                getSupportActionBar().setTitle(carta.getNombre());

            }
        }
        return view;
    }
}