package ec.edu.espol;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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
            // Verificar si cb es CartaNormal y cartaActual también es CartaNormal
            if (cb instanceof CartaNormal && cartaActual instanceof CartaNormal) {
                CartaNormal cbot = (CartaNormal) cb;
                if (cbot.validarCarta(cartaActual)) {
                    return cb;
                }
            }
            else if (cb instanceof ComodinEspecial) {// Retornar ComodinEspecial directamente
                return cb;
            }
            // Verificar si cb es CartaComodin y cartaActual es CartaNormal
            else if (cb instanceof CartaComodin && cartaActual instanceof CartaNormal) {
                CartaComodin cbotc = (CartaComodin) cb;
                if (cbotc.validarCarta(cartaActual)) {
                    return cb;
                }
            }
            else if(cb instanceof CartaNormal && cartaActual instanceof CartaComodin){
                CartaComodin cActualc = (CartaComodin) cartaActual;
                if (cActualc.validarCarta(cb)){
                    return cb;
                }
            }
        }
        // Si no se encuentra ninguna carta válida, se puede manejar retornando null.
        //Asi en la clase Juego implementar algún tipo de ciclo
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
