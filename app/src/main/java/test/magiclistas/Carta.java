package test.magiclistas;

/**
 * Created by mireia on 30/10/16.
 */
import android.text.TextUtils;
import java.util.ArrayList;

public class Carta {

    String nombre;
    String tipo;
    String imagen;

    private ArrayList<String> castList;

    public Carta(String nombre, String tipo){
        this.nombre = nombre;
        this.tipo = tipo;
    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public ArrayList<String> getCastList() {
        return castList;
    }


    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setCastList(ArrayList<String> castList) {
        this.castList = castList;
    }

    // ToString

    @Override
    public String toString() {
        return "Carta{"+"titulo="+nombre+"}";
        // ", Tipus=" +  tipo +
        // ", Image='" + imagen + '\'' + '}';
    }
}
