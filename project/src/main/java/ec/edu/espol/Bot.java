package ec.edu.espol;

import java.util.ArrayList;
import java.util.Random;

public class Bot{
     private String nombre;
    private ArrayList<Carta> barajaBot;

    public Bot(){
        this.nombre = "Bot";
        this.barajaBot = new ArrayList<>();
    }

    public ArrayList<Carta> getBarajaBot(){
        return barajaBot;
    }

    public void setBarajaBot(ArrayList<Carta> barajaBot){
        this.barajaBot = barajaBot;
    }

    public void barajaInicialB(ArrayList<Carta> cartaBaraja){
        for(int i = 0; i < 7; i++){
            barajaBot.add(cartaBaraja.remove(cartaBaraja.size()-1));
        }
    }

    public static Carta lanzarCartaB(Carta cartaActual, Carta cartaBot, ArrayList<Carta> barajabot) {
        boolean cartaValida = false;
        while (!cartaValida) {
            if (cartaActual instanceof CartaNormal && cartaBot instanceof CartaNormal) {
                CartaNormal cartaN = (CartaNormal) cartaActual;
                if (cartaBot.validarCarta(cartaN)) {
                    cartaValida = true;
                    return cartaBot;
                } else {
                    int indiceNuevo = randomnum(barajabot.size() - 1);
                    cartaBot = barajabot.get(indiceNuevo);
                }
            } else if ((cartaActual instanceof CartaComodin && cartaBot instanceof CartaComodin) || cartaActual instanceof CartaNormal) {
                CartaComodin cb = (CartaComodin) cartaBot;
                if (cb.validarCarta(cartaActual)) {
                    cartaValida = true;
                    return cartaBot;
                } else {
                    int indiceNuevo = randomnum(barajabot.size() - 1);
                    cartaBot = barajabot.get(indiceNuevo);
                }
            } else if (cartaBot instanceof ComodinEspecial) {
                ComodinEspecial cartaE = (ComodinEspecial) cartaBot;
                cartaValida = true; // No se requiere validación para comodines especiales
                return cartaE;
            } else {
                int indiceNuevo = randomnum(barajabot.size() - 1);
                cartaBot = barajabot.get(indiceNuevo);
            }
        }
        return cartaBot; // Devuelve la carta válida encontrada
    }

    public static int randomnum(int cantidad){
        Random r = new Random();
        int numero = r.nextInt(cantidad);
        return numero;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Nombre: ").append(nombre).append("\n");
        for(Carta c : barajaBot){ //se recorre cada elemento de la lista para agregarlo al stringbuilder
            s.append(c).append("");
        }
        return s.toString(); //se convierte el stringbuilder en string 
    }
}
