package ec.edu.espol;
/*
* 
* @author Victor, Valentina, José
*
*/
public class Main {
    public static void main(String[] args) {
        CartaBaraja baraja = new CartaBaraja();
        System.out.println("-------------------\nBienvenido a UNO\n-------------------");
        System.out.println("Tipo de Cartas: ");
        System.out.println(" +2 --> Tomar dos cartas");
        System.out.println(" +4 ---> Tomar cuatro cartas");
        System.out.println(" % --> Cambio de color");
        System.out.println(" ^ --> Reverse");
        System.out.println(" & --> Bloqueo");
        baraja.generarCartas();
        baraja.barajar();
        Jugador j1 = Juego.agregarJugador();
        Bot j2 = new Bot();
        j1.barajaInicial(baraja.getCartas());
        j2.barajaInicialB(baraja.getCartas());
        System.out.println(j1);
        System.out.println(j2);
        Carta cartaInicial = Juego.cartaInicial(baraja);
        Juego.iniciarJuego(j1,j2,baraja,cartaInicial);
    }
}
