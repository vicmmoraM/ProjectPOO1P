package ec.edu.espol;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static Carta otraCarta(Scanner scanner, ArrayList<Carta> baraja) {
        //mucho método...
        System.out.println("Cartas en mano:");
        for (int i = 0; i < baraja.size(); i++) {
            System.out.println((i + 1) + ". " + baraja.get(i));
        }

        System.out.print("Selecciona una carta (1-" + baraja.size() + "): ");
        int seleccion = scanner.nextInt() - 1;

        while (seleccion < 0 || seleccion >= baraja.size()) {
            System.out.print("Selección inválida. Por favor, selecciona una carta válida (1-" + baraja.size() + "): ");
            seleccion = scanner.nextInt() - 1;
        }

        return baraja.get(seleccion);
    }

    public static void lanzarCarta(Carta cartaActual, Carta cartaJugador){
        if(cartaActual instanceof CartaNormal){
            CartaNormal CartaN = (CartaNormal) cartaActual;
            if(cartaJugador.validarCarta(CartaN)){
                System.out.println(cartaJugador);
            }
        } 
        if(cartaActual instanceof CartaComodin){
            CartaComodin CartaC =(CartaComodin) cartaActual;
            if(cartaJugador.validarCarta(CartaC)){
                System.out.println(cartaJugador);
            }
        }
        if(cartaJugador instanceof ComodinEspecial){
            ComodinEspecial CartaE = (ComodinEspecial) cartaJugador;
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
