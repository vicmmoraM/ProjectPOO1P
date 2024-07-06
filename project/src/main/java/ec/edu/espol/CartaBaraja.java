package ec.edu.espol;
import java.util.ArrayList;
import java.util.Collections;

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
            cartas.add(new ComodinEspecial(c, "+2"));
            cartas.add(new ComodinEspecial(c, "+2"));
            cartas.add(new ComodinEspecial(c, "+4"));
            cartas.add(new ComodinEspecial(c, "+4"));
            cartas.add(new ComodinEspecial(c, "%"));
            cartas.add(new ComodinEspecial(c, "%"));
        }
    }

    public void barajar(){
        Collections.shuffle(cartas);
    }

    public Carta chuparCarta(){ //la baraja esta de cabeza y asi se chupa la carta final
        return cartas.remove(cartas.size()-1);
    }
}


