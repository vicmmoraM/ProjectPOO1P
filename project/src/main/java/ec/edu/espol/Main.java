package ec.edu.espol;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CartaBaraja baraja = new CartaBaraja();
        System.out.println("-------------------\nBienvenido a UNO\n-------------------");
        baraja.generarCartas();
        baraja.barajar();
        Jugador j1 = Juego.agregarJugador();
        Jugador j2 = new Jugador("Bot");
        Juego juego = new Juego(j1,j2);
        j1.barajaInicial(baraja.getCartas());
        j2.barajaInicial(baraja.getCartas());
        System.out.println(j1);
        System.out.println(j2);
    }
}
