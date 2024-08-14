package ec.edu.espol;

import java.util.List;
import java.util.ArrayList;

public class Bot{
    private String nombre;
    private List<Carta> barajaBot;

    public Bot(){
        this.nombre = "Bot";
        this.barajaBot = new ArrayList<>();
    }

    public List<Carta> getBarajaBot(){
        return barajaBot;
    }

    public void setBarajaBot(List<Carta> barajaBot){
        this.barajaBot = barajaBot;
    }

    public void barajaInicialB(List<Carta> cartaBaraja){
        for(int i = 0; i < 7; i++){
            barajaBot.add(cartaBaraja.remove(cartaBaraja.size()-1));
        }
    }

    public static Carta lanzarCartaB(Carta cartaActual, List<Carta> barajabot) {
        for (Carta cb : barajabot) {
            if (esNormalValida(cb, cartaActual) || esComodinEspecial(cb) || esCartaComodinValida(cb, cartaActual)) {
                return cb;
            }
        }
        return null; 
    }

    public static boolean esNormalValida(Carta cartaActual, Carta cb){
        if (cb instanceof CartaNormal && cartaActual instanceof CartaNormal) {
            CartaNormal cbot = (CartaNormal) cb;
            return cbot.validarCarta(cartaActual);
        } else if (cb instanceof CartaNormal && cartaActual instanceof CartaComodin) {
            CartaComodin cActualc = (CartaComodin) cartaActual;
            return cActualc.validarCarta(cb);
        }
        return false;
    } 

    private static boolean esComodinEspecial(Carta cb){
        return cb instanceof ComodinEspecial;
    }

    private static boolean esCartaComodinValida(Carta cb, Carta cartaActual) {
        if (cb instanceof CartaComodin && cartaActual instanceof CartaNormal) {
            CartaComodin cbotc = (CartaComodin) cb;
            return cbotc.validarCarta(cartaActual);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Nombre: ").append(nombre).append("\n");
        for(Carta c : barajaBot){ //se recorre cada elemento de la lista para agregarlo al stringbuilder
            s.append(c.toString()).append(", ");
        }
        s.deleteCharAt(s.length()-1).deleteCharAt(s.length()-1); //se elimina el último ", "
        return s.toString(); //se convierte el stringbuilder en string 
    }
}
