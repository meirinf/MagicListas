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

        public Carta (){

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


        public void setNombre(String nombre) {
            nombre = nombre;
        }


        public void setTipo(String tipo) {
            tipo = tipo;
        }

        public void setImagen(String imagen) {
            imagen = imagen;
        }

        public ArrayList<String> getCastList() {
            return castList;
        }


        public void setCastList(ArrayList<String> castList) {
            this.castList = castList;
        }

        @Override
        public String toString() {
            return "Cart {" +
                    "Nom='" + nombre + '\'' +
                    ", Tipus=" +  tipo +
                    ", Image='" + imagen + '\'' + '}';
        }
}
