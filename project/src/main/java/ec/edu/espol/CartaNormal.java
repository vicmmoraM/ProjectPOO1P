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

    public boolean validarCarta(Carta carta){
        if(carta instanceof CartaNormal){
            CartaNormal cn = (CartaNormal) carta; 
            if(this.getColor().equals(cn.getColor()) || this.getNumero() == cn.getNumero()){
                return true;
            }
        }
        return false; 
    }

    @Override
    public String toString() {
        return super.toString() + numero + "";
    }
}
