package test.magiclistas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by mireia on 1/11/16.
 */

public class CartasAdapter extends ArrayAdapter<Carta> {

    public CartasAdapter(Context context, int resource, List<Carta> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      // Obtenim l'objecte en la possició corresponent
        Carta carta = getItem(position);

      // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        if (convertView == null) {
          LayoutInflater inflater = LayoutInflater.from(getContext());
          convertView = inflater.inflate(R.layout.adapter_cartas, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView nombreCarta = (TextView) convertView.findViewById(R.id.adapterNombre);
        TextView tipo = (TextView) convertView.findViewById(R.id.adapterRareza);
        TextView color = (TextView) convertView.findViewById(R.id.adapterColor);
        ImageView imagenCarta = (ImageView) convertView.findViewById(R.id.adapterCarta);

         // Fiquem les dades dels objectes (provinents del JSON) en el layout

        nombreCarta.setText(carta.getNombre());
        tipo.setText(carta.getTipo());

        String auxColor = "";

        if (carta.getColor() != null) {
            for (int i = 0; i <carta.getColor().length ; i++) {
                auxColor += " "+carta.getColor()[i];
            }
        }
        color.setText(auxColor);

        Glide.with(getContext()).
                    load(carta.getImagen()).
                    into(imagenCarta);

        // Retornem la View replena per a mostrarla
        return convertView;
    }
}
