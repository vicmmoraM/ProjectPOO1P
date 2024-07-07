package ec.edu.espol;

import java.util.ArrayList;

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

    public static void LanzarCarta(Carta cartaActual, Carta CartaBot){
        if(cartaActual instanceof CartaNormal){
            CartaNormal CartaN = (CartaNormal) cartaActual;
            if(CartaBot.validarCarta(CartaN)){
                System.out.println(CartaBot);
            }
        } 
        if(cartaActual instanceof CartaComodin){
            CartaComodin CartaC =(CartaComodin) cartaActual;
            if(CartaBot.validarCarta(CartaC)){
                System.out.println(CartaBot);
            }
        }
        if(CartaBot instanceof ComodinEspecial){
            ComodinEspecial CartaE = (ComodinEspecial) CartaBot;
            System.out.println(CartaE);
        }
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
