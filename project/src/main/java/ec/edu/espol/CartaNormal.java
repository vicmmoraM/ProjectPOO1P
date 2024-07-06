package ec.edu.espol;

public class CartaNormal extends Carta{
    private int numero;

    public CartaNormal(Colores carta, int numero) {
        super(carta);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    
}