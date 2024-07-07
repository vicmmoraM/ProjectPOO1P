package ec.edu.espol;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Juego {
    private Jugador bot;
    private Jugador jugador;

    public Juego(Jugador jugador,Jugador bot){
        this.bot=bot;
        this.jugador=jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public Jugador getBot() {
        return bot;
    }
    public void setBot(Jugador bot) {
        this.bot = bot;
    }

    public static Jugador agregarJugador(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa tu nombre: ");
        String nombre = sc.nextLine();
        Jugador jugador = new Jugador(nombre);
        return jugador;
    }

    public static void  iniciarJuego(Jugador j1,Jugador bot,CartaBaraja mazo){
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int indice = r.nextInt(mazo.getCartas().size()-1);
        while(!(mazo.getCartas().get(indice) instanceof CartaNormal)){
            indice = r.nextInt(mazo.getCartas().size()-1);
        }
        System.out.print("Carta Inicial");
        System.out.println(mazo.getCartas().get(indice));
        Carta CartaTablero = mazo.getCartas().get(indice); //Carta Actual
        mazo.removerCarta(indice);

        while(j1.getBarajaJugador().isEmpty() || bot.getBarajaJugador().isEmpty()){
            System.out.println("Escoge que carta quieres: (0 al" + (j1.getBarajaJugador().size() - 1) + ")");
            int index = sc.nextInt();
            sc.nextLine();
            while(index < 0 || index > (j1.getBarajaJugador().size()-1)){
                System.out.println("Ingrese un valor dentro del rango especificado!! >:|");
                System.out.println("Escoge que carta quieres: (0 al" + (j1.getBarajaJugador().size() - 1) + ")");
                index = sc.nextInt();
                sc.nextLine();
            }
            Carta CartaJugador = j1.getBarajaJugador().get(index);

            Jugador.LanzarCarta(CartaTablero,CartaJugador);
            CartaTablero = CartaJugador;
            j1.getBarajaJugador().remove(index);

            System.out.println("Turno del BOT(ADRIAN)");

            int indexbot = r.nextInt(mazo.getCartas().size()-1);
            Carta CartaBot = bot.getBarajaJugador().get(indexbot);
            Bot.LanzarCarta(CartaTablero, CartaBot);
        }
    }
}
