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
            if(this.getCarta().equals(cn.getCarta()) || this.getNumero() == cn.getNumero()){
                return true;
            }
        }
        if (carta instanceof CartaComodin){
            CartaComodin cartacomodin = (CartaComodin) carta;
            return this.getCarta().equals(cartacomodin.getCarta());
        }
        return false; 
    }

    public boolean puedeJugar(Carta cartaActual) {
        if (cartaActual instanceof CartaNormal) {
            CartaNormal cartaNormalActual = (CartaNormal) cartaActual;
            return this.getCarta() == cartaNormalActual.getCarta() || this.numero == cartaNormalActual.numero;
        } else if (cartaActual instanceof CartaComodin) {
            return this.getCarta() == cartaActual.getCarta();
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + numero + " ";
    }

    
}
