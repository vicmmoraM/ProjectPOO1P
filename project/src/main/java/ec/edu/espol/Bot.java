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

    public static Carta lanzarCartaB(Carta cartaActual, ArrayList<Carta> barajabot) {
        for (Carta cb : barajabot) {
            // Verificar si cb es CartaNormal y cartaActual también es CartaNormal
            if (cb instanceof CartaNormal && cartaActual instanceof CartaNormal) {
                CartaNormal cbot = (CartaNormal) cb;
                if (cbot.validarCarta(cartaActual)) {
                    return cb;
                }
            }
            // Verificar si cb es CartaComodin y cartaActual es CartaNormal
            else if (cb instanceof CartaComodin && cartaActual instanceof CartaNormal) {
                CartaComodin cbotc = (CartaComodin) cb;
                if (cbotc.validarCarta(cartaActual)) {
                    return cb;
                }
            }
            // Retornar ComodinEspecial directamente
            else if (cb instanceof ComodinEspecial) {
                return cb;
            }
        }
        // Si no se encuentra ninguna carta válida, se puede manejar retornando null o lanzando una excepción según tu lógica de juego.
        return null;
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
