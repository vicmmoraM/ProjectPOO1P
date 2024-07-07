package ec.edu.espol;

public class CartaComodin extends Carta{       
    private String simbolo;

    public CartaComodin(Colores carta, String simbolo){
        super(carta);
        this.simbolo = simbolo;
    }

    public String getSimbolo(){
        return this.simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public boolean validarCarta(Carta carta){
        if (carta instanceof CartaComodin){
            CartaComodin cartacomodin = (CartaComodin) carta;
            return this.getCarta().equals(cartacomodin.getCarta());
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + simbolo + " ";
    }
}
