package ec.edu.espol;
import java.util.ArrayList;
import java.util.List;
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

    private static Carta otraCarta(Scanner scanner, ArrayList<Carta> baraja) {
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

    public static Carta LanzarCarta(Carta cartaActual, Carta cartaJugador, ArrayList<Carta> manoJugador) {
        Scanner scanner = new Scanner(System.in);
        boolean cartaValida = false;

        while (!cartaValida) {
            //Validación de CartaNormal
            if (cartaActual instanceof CartaNormal && cartaJugador instanceof CartaNormal) {
                CartaNormal cartaNormal = (CartaNormal) cartaActual;

                if (cartaJugador.validarCarta(cartaNormal)) {
                    System.out.println("Carta normal válida: " + cartaJugador);
                    cartaValida = true;
                    cartaActual = cartaNormal;
                    return cartaNormal;
                } 

                else {
                    System.out.println("Carta normal inválida. Por favor, selecciona otra carta:");
                    cartaJugador = otraCarta(scanner, manoJugador);
                    return cartaActual;
                }
            } 
            //Validando que sea Carta Comodin :D
            else if (cartaActual instanceof CartaComodin && cartaJugador instanceof CartaComodin) {
                CartaComodin cartaC = (CartaComodin) cartaActual;
                if (cartaJugador.validarCarta(cartaC)) {
                    System.out.println("Carta comodín válida: " + cartaJugador);
                    cartaValida = true;
                    return cartaC;
                } 
                else {
                    System.out.println("Carta comodín inválida. Por favor, selecciona otra carta:");
                    cartaJugador = otraCarta(scanner, manoJugador);
                    return cartaActual;
                }
            } 
            else if (cartaJugador instanceof ComodinEspecial) {
                ComodinEspecial cartaE = (ComodinEspecial) cartaJugador;
                System.out.println("Comodín especial: " + cartaE);
                cartaValida = true; // No se requiere validación para comodines especiales
                return cartaE;
            } 
            else {
                System.out.println("Tipo de carta no compatible con la carta actual. Por favor, selecciona otra carta:");
                cartaJugador = otraCarta(scanner, manoJugador);
                return cartaActual;
            }
        }
        return cartaActual;
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
