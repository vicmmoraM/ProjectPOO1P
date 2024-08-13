package ec.edu.espol;
import java.util.*;

public class CartaBaraja {
    private List<Carta> cartas;

    public CartaBaraja(){
        this.cartas = new ArrayList<>();
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
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
                addCartasComodin(c,"^",2);
                addCartasComodin(c,"&",2);
            }
            else{
                addCartasComodin(Colores.N,"+2",2);
                addCartasComodin(Colores.N,"+4",2);
                addCartasComodin(Colores.N,"%",2);
            }
        }
    }

    private void addCartasComodin(Colores color, String simbolo, int cont){
        for(int i = 0; i<cont; i++){
            cartas.add(new CartaComodin(color,simbolo));
        }
    }

    public void barajar(){
        Collections.shuffle(cartas);
    }

    public Carta TomarCarta(){ //la baraja esta de cabeza y asi se chupa la carta final
        return cartas.remove(cartas.size()-1);
    }

    public Carta cartaRandom(){
        Random r = new Random();
        int indice = r.nextInt(cartas.size());
        return cartas.remove(indice);
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


