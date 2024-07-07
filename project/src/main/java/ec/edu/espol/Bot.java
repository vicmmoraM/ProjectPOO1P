package ec.edu.espol;
import java.util.ArrayList;
import java.util.Random;

public class Bot {
    private ArrayList<Carta> barajaBot;

    public Bot(){
        this.barajaBot = new ArrayList<>();
    }

    public ArrayList<Carta> getBarajaBot() {
        return barajaBot;
    }

    public void setBarajaBot(ArrayList<Carta> barajaBot) {
        this.barajaBot = barajaBot;
    }

    public void barajaInicial(ArrayList<Carta> cartaBaraja){
        for(int i = 0; i < 7; i++){
            barajaBot.add(cartaBaraja.remove(cartaBaraja.size()-1));
        }
    }

}
