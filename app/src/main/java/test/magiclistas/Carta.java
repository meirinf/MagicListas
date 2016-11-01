package test.magiclistas;

import java.util.ArrayList;

/**
 * Created by mireia on 30/10/16.
 */
public class Carta {

    String nombre;
    String tipo;
    String color;
    String imagen;

    private ArrayList<String> castList;

    public Carta(String nombre, String tipo, String color, String imagen){
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
        this.imagen = imagen;
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

    public String getColor() {
        return color;
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

    public void setColor(String color) {
        this.color = color;
    }

    // ToString

    @Override
    public String toString() {
        return "Carta{"+"titulo="+nombre+"}";
        // ", Tipus=" +  tipo +
        // ", Image='" + imagen + '\'' + '}';
    }
}
