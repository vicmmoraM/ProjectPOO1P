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
        return super.getCarta() == carta.getCarta();
    }

    @Override
    public String toString() {
        return super.toString() + simbolo + " ";
    }
}
