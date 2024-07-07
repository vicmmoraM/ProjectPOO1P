package ec.edu.espol;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    private static Carta otraCarta(Scanner scanner, List<Carta> manoJugador) {
        System.out.println("Cartas en mano:");
        for (int i = 0; i < manoJugador.size(); i++) {
            System.out.println((i + 1) + ". " + manoJugador.get(i));
        }
    
        System.out.print("Selecciona una carta (1-" + manoJugador.size() + "):  ");
        int seleccion = scanner.nextInt() - 1;
    
        while (seleccion < 0 || seleccion >= manoJugador.size()) {
            System.out.print("Selección inválida. Por favor, selecciona una carta válida (1-" + manoJugador.size() + "): ");
            seleccion = scanner.nextInt() - 1;
        }
    
        return manoJugador.get(seleccion);
    }
    
    public static Carta LanzarCarta(Carta cartaActual, Carta cartaJugador, ArrayList<Carta> manoJugador) {
        Scanner scanner = new Scanner(System.in);
        boolean cartaValida = false;
    
        while (!cartaValida) {
            // Validación de CartaNormal
            if (cartaActual instanceof CartaNormal && cartaJugador instanceof CartaNormal) {
                CartaNormal cartaNormal = (CartaNormal) cartaActual;
    
                if (cartaJugador.validarCarta(cartaNormal)) {
                    System.out.println("Carta normal válida: " + cartaJugador);
                    cartaValida = true;
                    return cartaJugador;
                } else {
                    System.out.println("Carta normal inválida. Por favor, selecciona otra carta: ");
                    cartaJugador = otraCarta(scanner, manoJugador);
                }
            } else if (cartaActual instanceof CartaComodin && cartaJugador instanceof CartaComodin) {
                CartaComodin cartaC = (CartaComodin) cartaActual;
                if (cartaJugador.validarCarta(cartaC)) {
                    System.out.println("Carta comodín válida: " + cartaJugador);
                    cartaValida = true;
                    return cartaJugador;
                } else {
                    System.out.println("Carta comodín inválida. Por favor, selecciona otra carta:");
                    cartaJugador = otraCarta(scanner, manoJugador);
                }
            } else if (cartaJugador instanceof ComodinEspecial) {
                ComodinEspecial cartaE = (ComodinEspecial) cartaJugador;
                System.out.println("Comodín especial: " + cartaE);
                cartaValida = true; // No se requiere validación para comodines especiales
                return cartaE;
            } else {
                System.out.println("Tipo de carta no compatible con la carta actual. Por favor, selecciona otra carta:");
                cartaJugador = otraCarta(scanner, manoJugador);
            }
        }
    
        return cartaJugador; // Devolver la carta válida final seleccionada
    }
    public static Colores ComodinesEspeciales(Carta c, Jugador j1, Bot bot, CartaBaraja mazo, int turnoActual) {
        if (c instanceof ComodinEspecial) {
            ComodinEspecial ce = (ComodinEspecial) c;
            if (ce.getSimbolo().equals("+4")) {
                System.out.println("Chupa cuatro cartas :(");
                for (int i = 0; i < 4; i++) {
                    Carta cartaChupada = mazo.chuparCarta();
                    if (turnoActual == 0) {
                        j1.getBarajaJugador().add(cartaChupada);
                    } else {
                        bot.getBarajaBot().add(cartaChupada);
                    }
                }
                if(turnoActual == 0){
                    Colores[] valoresColores = Colores.values(); // Obtener todos los valores del enum
                    Random random = new Random();
                    Colores colorBot = valoresColores[random.nextInt(valoresColores.length)]; // Obtener color aleatorio
                    return colorBot;
                }
                else{
                    Colores color = ComodinEspecial.elegirColor();
                    return color;
                }
            } else if (ce.getSimbolo().equals("+2")) {
                System.out.println("Toma dos cartas :(");
                for (int i = 0; i < 2; i++) {
                    Carta cartaChupada = mazo.chuparCarta();
                    if (turnoActual == 0) {
                        j1.getBarajaJugador().add(cartaChupada);
                    } else {
                        bot.getBarajaBot().add(cartaChupada);
                    }
                }
                if(turnoActual == 0){
                    Colores[] valoresColores = Colores.values(); // Obtener todos los valores del enum
                    Random random = new Random();
                    Colores colorBot = valoresColores[random.nextInt(valoresColores.length)]; // Obtener color aleatorio
                    return colorBot;
                }
                else{
                    Colores color = ComodinEspecial.elegirColor();
                    return color;
                }
            } else {
                Colores color = ComodinEspecial.elegirColor();
                return color;
            }
        }
        return null; // Retornar null si la carta no es un ComodinEspecial o no se puede manejar
    }

    public void mostrarInformacion() {
        System.out.println("Jugador: " + nombre);
        System.out.print("Baraja del jugador: [");
        for (int i = 0; i < barajaJugador.size(); i++) {
            System.out.print(barajaJugador.get(i));
            if (i < barajaJugador.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
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
