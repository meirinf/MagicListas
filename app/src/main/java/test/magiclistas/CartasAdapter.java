package test.magiclistas;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.bumptech.glide.Glide;
import java.util.List;

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

        //Obtenemos el objeto Carta en la posicion seleccionada
        Carta carta = getItem(position);

        AdapterCartasBinding binding = null;

        //Miramos si el view se esta usando si no lo cargamos
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.adapter_cartas,parent,false);
        } else{
            binding = DataBindingUtil.getBinding(convertView);
        }

        /*
        TextView nombreCarta = (TextView) convertView.findViewById(R.id.adapterNombre);
        TextView tipo = (TextView) convertView.findViewById(R.id.adapterTipo);
        TextView color = (TextView) convertView.findViewById(R.id.adapterColor);
        ImageView imagenCarta = (ImageView) convertView.findViewById(R.id.adapterCarta);
        */


        //Metemos los dato de objeto que vienen del json en el layaout

        binding.adapterNombre.setText("Nombre Carta: "+carta.getNombre());
        binding.adapterTipo.setText("Tipo: "+carta.getTipo());
        binding.adapterColor.setText("Color: "+carta.getColor());

        //Imagen Carta
        Glide.with(getContext()).
                load(carta.getImagen()).
                into(binding.adapterCarta);

        // devuelve la vista
        return binding.getRoot();
    }
}
