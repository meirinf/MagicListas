package test.magiclistas;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.bumptech.glide.Glide;
import java.util.List;
import test.magiclistas.Objetos.Carta;
import test.magiclistas.databinding.AdapterCartasBinding;


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

        AdapterCartasBinding binding;
        binding = null;

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.adapter_cartas,parent,false);
        } else binding = DataBindingUtil.getBinding(convertView);

        /*// Unim el codi en les Views del Layout
        TextView nombreCarta = (TextView) convertView.findViewById(R.id.adapterNombre);
        TextView tipo = (TextView) convertView.findViewById(R.id.adapterTipo);
        TextView color = (TextView) convertView.findViewById(R.id.adapterColor);
        ImageView imagenCarta = (ImageView) convertView.findViewById(R.id.adapterCarta);
        */

        // Fiquem les dades dels objectes (provinents del JSON) en el layout

        binding.adapterNombre.setText("Nombre Carta: "+carta.getNombre());
        binding.adapterTipo.setText("Tipo: "+carta.getTipo());
        binding.adapterColor.setText("Color: "+carta.getColor());

        //Imagen Carta
        Glide.with(getContext()).
                load(carta.getImagen()).
                into(binding.adapterCarta);

        // Retornem la View replena per a mostrarla
        return binding.getRoot();
    }
}
