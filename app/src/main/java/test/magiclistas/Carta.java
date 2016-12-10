package test.magiclistas;

import java.io.Serializable;

/**
 * Created by mireia on 30/10/16.
 */
public class Carta implements Serializable {

    String nombre;
    String tipo;
    String rareza;
    String color;
    String imagen;
    String texto;

    // private ArrayList<String> castList;

    public Carta(){            }

    // Getters

    public String getRareza() {
        return rareza;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public String getColor() {
        return color;
    }

    public String getTexto() {
        return texto;
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

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    // ToString

    @Override
    public String toString() {
        return "Carta{"+"titulo="+nombre+"}";
        // ", Tipus=" +  tipo +
        // ", Image='" + imagen + '\'' + '}';
    }
}
