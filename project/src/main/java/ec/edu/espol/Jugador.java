package ec.edu.espol;
import java.util.ArrayList;
public class Jugador {
    private String nombre;
    private ArrayList<Carta> baraja;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ArrayList<Carta> getBaraja() {
        return baraja;
    }
    public void setBaraja(ArrayList<Carta> baraja) {
        this.baraja = baraja;
    }
}
