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

    public static void lanzarCartaB(Carta cartaActual, Carta cartaBot){
        if(cartaActual instanceof CartaNormal){
            CartaNormal CartaN = (CartaNormal) cartaActual;
            if(cartaBot.validarCarta(CartaN)){
                System.out.println(cartaBot);
            }
        } 
        if(cartaActual instanceof CartaComodin){
            CartaComodin CartaC =(CartaComodin) cartaActual;
            if(cartaBot.validarCarta(CartaC)){
                System.out.println(cartaBot);
            }
        }
        if(cartaBot instanceof ComodinEspecial){
            ComodinEspecial CartaE = (ComodinEspecial) cartaBot;
            System.out.println(CartaE);
        }
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
