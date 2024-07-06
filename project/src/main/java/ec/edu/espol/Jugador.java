package ec.edu.espol;
import java.util.ArrayList;
public class Jugador {
    private String nombre;
    private ArrayList<Carta> baraja;

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
    public ArrayList<Carta> getBaraja() {
        return baraja;
    }
    public void setBaraja(ArrayList<Carta> baraja) {
        this.baraja = baraja;
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
