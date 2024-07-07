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

    public static Carta LanzarCartaB(Carta cartaActual, Carta cartaBot, ArrayList<Carta> barajabot){
        boolean cartaValida = false;
        while(!(cartaValida)){
            if (cartaActual instanceof CartaNormal && cartaBot instanceof CartaNormal) {
                CartaNormal cartaN = (CartaNormal) cartaActual;
                if (cartaBot.validarCarta(cartaN)){
                    System.out.println(cartaBot);
                    cartaActual = cartaN;
                    cartaValida = true;
                    return cartaN;
                } 
                else{
                    int indiceNuevo = Randomnum((barajabot.size()-1));
                    cartaBot = barajabot.get(indiceNuevo);
                    return cartaActual;
                }
            }
            else if (cartaActual instanceof CartaComodin && cartaBot instanceof CartaComodin) {
                CartaComodin cartaC = (CartaComodin) cartaActual;
                if (cartaBot.validarCarta(cartaC)) {
                    System.out.println(cartaBot);
                    cartaValida = true;
                    return cartaC;
                } 
                else {
                    int indiceNuevo = Randomnum((barajabot.size()-1));
                    cartaBot = barajabot.get(indiceNuevo);
                    return cartaActual;
                } 
            }
            else if (cartaBot instanceof ComodinEspecial) {
                ComodinEspecial cartaE = (ComodinEspecial) cartaBot;
                System.out.println(cartaE);
                cartaValida = true; // No se requiere validación para comodines especiales
                return cartaE;
            }
            else{
                int indiceNuevo = Randomnum((barajabot.size()-1));
                    cartaBot = barajabot.get(indiceNuevo);
                return cartaActual;
            }
        }
        return cartaActual;
    }

    public static int Randomnum(int cantidad){
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
