package ec.edu.espol;

public abstract class Carta {
    private Colores carta;

    protected Carta(Colores carta){
        this.carta = carta;
    }

    public Colores getCarta() {
        return carta;
    }

    public void setCarta(Colores carta) {
        this.carta = carta;
    }

    public abstract boolean validarCarta(Carta carta);

    @Override
    public String toString() {
        return carta + " ";
    }

}
