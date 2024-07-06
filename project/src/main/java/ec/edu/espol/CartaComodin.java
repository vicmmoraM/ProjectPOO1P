package ec.edu.espol;

public class CartaComodin extends Carta{
    private String simbolo;

    public CartaComodin(Colores carta, String simbolo){
        super(carta);
        this.simbolo = simbolo;
    }
}
