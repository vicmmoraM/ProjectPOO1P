package ec.edu.espol;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CartaBaraja {
    private ArrayList<Carta> cartas;

    public CartaBaraja(){
        this.cartas = new ArrayList<>();
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }
    /*
     * 
     * @Adrian ggs
     */
    public void generarCartas(){
        for(Colores c: Colores.values()){ //Recorriendo la clase Colores<Enum>
            if(c != Colores.N){
                for(int i = 0; i<=9; i++){
                    cartas.add(new CartaNormal(c,i));
                }
                cartas.add(new CartaComodin(c,"^"));
                cartas.add(new CartaComodin(c,"^"));
                cartas.add(new CartaComodin(c,"&"));
                cartas.add(new CartaComodin(c,"&"));
            }
            else{
                cartas.add(new ComodinEspecial(Colores.N, "+2"));
                cartas.add(new ComodinEspecial(Colores.N, "+2"));
                cartas.add(new ComodinEspecial(Colores.N, "+4"));
                cartas.add(new ComodinEspecial(Colores.N, "+4"));
                cartas.add(new ComodinEspecial(Colores.N, "%"));
                cartas.add(new ComodinEspecial(Colores.N, "%"));
            }
        }
    }

    public void barajar(){
        Collections.shuffle(cartas);
    }

    public Carta chuparCarta(){ //la baraja esta de cabeza y asi se chupa la carta final
        return cartas.remove(cartas.size()-1);
    }

    public Carta cartaRandom(){
        Random r = new Random();
        int indice = r.nextInt(cartas.size());
        Carta c = cartas.remove(indice);
        return c;
    }

    public void removerCarta(int indice){
        cartas.remove(indice);
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Carta c : cartas){
            s.append(c).append("");
        }
        return s.toString();
    }

    
}


