package test.magiclistas;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static java.security.AccessController.getContext;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Referencias
        TextView texto = (TextView) findViewById(R.id.details_texto);
        TextView nombreCarta = (TextView) findViewById(R.id.details_nombreCarta);
        TextView rareza = (TextView) findViewById(R.id.details_Rareza);
        TextView tipo =(TextView) findViewById(R.id.details_Tipo);
        TextView color = (TextView) findViewById(R.id.details_Color);
        ImageView imagen = (ImageView) findViewById(R.id.details_imagenCarta);

        // Recogemos el intent y cargamos la carta que hemos pasado desde el MainActivity
        Intent intent = this.getIntent();

        if (intent != null) {

            Carta carta = (Carta) intent.getSerializableExtra("carta");

            if (carta != null) {

                texto.setText("Pequeña Descripción: "+carta.getTexto());
                nombreCarta.setText("Nombre Carta: "+carta.getNombre());
                rareza.setText("Rareza: "+carta.getRareza());
                tipo.setText("Tipo: "+carta.getTipo());
                color.setText("Color: "+carta.getColor());

                Glide.with(this).
                        load(carta.getImagen()).
                        into(imagen);

                getSupportActionBar().setTitle(carta.getNombre());
            }
        }
        else{
            Toast.makeText(this, "Error al cargar la carta", Toast.LENGTH_SHORT).show();
        }
    }

}
