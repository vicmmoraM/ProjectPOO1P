package ec.edu.espol;
import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    private ArrayList<Carta> barajaBot;
    private ArrayList<Carta> barajaJugador;

    public ArrayList<Carta> getBarajaBot() {
        return barajaBot;
    }
    public void setBarajaBot(ArrayList<Carta> barajaBot) {
        this.barajaBot = barajaBot;
    }
    public ArrayList<Carta> getBarajaJugador() {
        return barajaJugador;
    }
    public void setBarajaJugador(ArrayList<Carta> barajaJugador) {
        this.barajaJugador = barajaJugador;
    }

    public Jugador agregarJugador(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa tu nombre: ");
        String nombre = sc.nextLine();
        Jugador jugador = new Jugador(nombre);
        return jugador;
    }
}
