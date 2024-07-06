package ec.edu.espol;
import java.util.ArrayList;
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

}
