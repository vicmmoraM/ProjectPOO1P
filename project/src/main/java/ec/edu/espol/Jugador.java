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
    
    public static Carta lanzarCarta(Carta cartaActual, Carta cartaJugador, ArrayList<Carta> manoJugador) {
        // 3 opciones Normal, Comodin, Especial <--- Jugador ; Tablero Normal o Comodin
        Scanner sc = new Scanner(System.in);
        boolean validar = true;
        while(validar){
            if(cartaJugador instanceof CartaNormal && cartaActual instanceof CartaNormal){
                CartaNormal cnJugador = (CartaNormal) cartaJugador;
                CartaNormal cnTablero = (CartaNormal) cartaActual;
                if(cnJugador.validarCarta(cnTablero)){
                    return cartaJugador;
                }
                else{
                    System.out.println("La carta no es válida: ");
                    cartaJugador = otraCarta(sc, manoJugador);
                }
            }else if(cartaJugador instanceof ComodinEspecial){
                    ComodinEspecial ce = (ComodinEspecial) cartaJugador;
                    System.out.println("Carta Comodin Especial: ");
                    return ce;
            }
            else if(cartaJugador instanceof CartaNormal && cartaActual instanceof CartaComodin){
                CartaNormal cnJugador = (CartaNormal) cartaJugador;
                CartaComodin cnTablero = (CartaComodin) cartaActual;
                if(cnTablero.validarCarta(cnJugador)){
                    return cartaJugador;
                }
                else{
                    System.out.println("La carta normal no es válida: ");
                    cartaJugador = otraCarta(sc, manoJugador);
                }
            }else if(cartaJugador instanceof CartaComodin && cartaActual instanceof CartaNormal){
                CartaComodin cnJugador = (CartaComodin) cartaJugador;
                CartaNormal cnTablero = (CartaNormal) cartaActual;
                if(cnJugador.validarCarta(cnTablero)){
                    return cartaJugador;
                }
                else{
                    System.out.println("La carta normal no es válida: ");
                    cartaJugador = otraCarta(sc, manoJugador);
                }
            }else{
                validar = false;
            }
        }
        return cartaJugador; 

    }

    public static Colores comodinesEspeciales(Carta c, Jugador j1, Bot bot, CartaBaraja mazo, int turnoActual) {
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
                    System.out.println(colorBot);
                    return colorBot;
                }
                else{
                    Colores color = ComodinEspecial.elegirColor();
                    System.out.println(color);
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
                    System.out.println(colorBot);
                    return colorBot;
                }
                else{
                    Colores color = ComodinEspecial.elegirColor();
                    System.out.println(color);
                    return color;
                }
            } else {
                Colores color = ComodinEspecial.elegirColor();
                return color;
            }
        }
        return null; // Retornar null si la carta no es un ComodinEspecial o no se puede manejar
    }

    public static int comodin(Carta c, Jugador j1, Bot bot, CartaBaraja mazo, int turnoActual){
        if(c instanceof CartaComodin){
            CartaComodin cc = (CartaComodin) c;
            if(cc.getSimbolo().equals("^") || cc.getSimbolo().equals("&")){
                if (turnoActual == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
        return turnoActual;
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
