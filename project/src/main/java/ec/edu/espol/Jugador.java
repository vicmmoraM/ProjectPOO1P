package ec.edu.espol;
import java.util.ArrayList;

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

    public void barajaInicial(ArrayList<Carta> cartaBaraja){
        for(int i = 0; i < 7; i++){
            barajaJugador.add(cartaBaraja.remove(cartaBaraja.size()-1));
        }
    }

    public static void LanzarCarta(Carta cartaActual, Carta CartaJugador){
        if(cartaActual instanceof CartaNormal){
            CartaNormal CartaN = (CartaNormal) cartaActual;
            if(CartaJugador.validarCarta(CartaN)){
                System.out.println(CartaJugador);
            }
        } 
        if(cartaActual instanceof CartaComodin){
            CartaComodin CartaC =(CartaComodin) cartaActual;
            if(CartaJugador.validarCarta(CartaC)){
                System.out.println(CartaJugador);
            }
        }
        if(CartaJugador instanceof ComodinEspecial){
            ComodinEspecial CartaE = (ComodinEspecial) CartaJugador;
            System.out.println(CartaE);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Nombre: ").append(nombre).append("\n");
        for(Carta c : barajaJugador){ //se recorre cada elemento de la lista para agregarlo al stringbuilder
            s.append(c).append("");
        }
        return s.toString(); //se convierte el stringbuilder en string 
    }
    
}
