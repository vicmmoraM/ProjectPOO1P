package ec.edu.espol;
import java.util.ArrayList;
public class Bot {
    private ArrayList<Carta> barajaBot;

    public Bot(){
        this.barajaBot = new ArrayList<>();
    }

    public ArrayList<Carta> getBaraja() {
        return baraja;
    }

    public void setBaraja(ArrayList<Carta> baraja) {
        this.baraja = baraja;
    }

    public void barajaInicial(ArrayList<Carta> carta){
        Random r = new Random();
        for(int i = 0; i < 7; i++){
            int indice = r.nextInt(carta.size() + 1) - 1;
            barajaBot.add(carta.get(indice));
            carta.remove(indice);
        }
    }

}
