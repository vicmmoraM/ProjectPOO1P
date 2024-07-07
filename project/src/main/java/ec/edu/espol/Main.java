package ec.edu.espol;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CartaBaraja baraja = new CartaBaraja();
        Juego juego = new Juego();
        System.out.println("-------------------\nBienvenido a UNO\n-------------------");
        baraja.generarCartas();
        baraja.barajar();
        String jugador = sc.nextLine();
        Jugador j1 = new Jugador("Bot");
        Jugador j2 = new Jugador(jugador);
        j1.barajaInicial(baraja.getCartas());
        System.out.println(baraja);
        System.out.println(j1);
        System.out.println(j2);
    }
}