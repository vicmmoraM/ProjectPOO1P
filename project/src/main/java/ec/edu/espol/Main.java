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
        baraja.generarCartas();
        baraja.barajar();
        Jugador j1 = Juego.agregarJugador();
        Jugador j2 = new Jugador("Bot");
        j1.barajaInicial(baraja.getCartas());
        j2.barajaInicial(baraja.getCartas());
        System.out.println(j1);
        System.out.println(j2);
        Juego.iniciarJuego(j1,j2,baraja);
    }
}
