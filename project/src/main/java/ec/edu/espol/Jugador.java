package ec.edu.espol;
import java.util.ArrayList;
import java.util.Random;

public class Jugador {
    private String nombre;
    private ArrayList<Carta> barajaJugador;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.barajaJugador = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ArrayList<Carta> getBarajaJugador() {
        return barajaJugador;
    }
    public void setBarajaJugador(ArrayList<Carta> barajaJugador) {
        this.barajaJugador = barajaJugador;
    }

    public void barajaInicial(ArrayList<Carta> carta){
        Random r = new Random();
        for(int i = 0; i < 7; i++){
            int indice = r.nextInt(carta.size() + 1) - 1;
            barajaJugador.add(carta.get(indice));
            carta.remove(indice);
        }
    }
    
}
