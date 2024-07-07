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

    public void iniciarJuego(Jugador j1,Jugador bi, ArrayList<Carta> mazo){

        while(j1.getBarajaJugador().isEmpty() || bi.getBarajaJugador().isEmpty()){
            
        }
    }
    
}
