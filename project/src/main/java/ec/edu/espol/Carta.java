package ec.edu.espol;

public abstract class Carta {
    private Colores carta;

    public Carta(Colores carta){
        this.carta = carta;
    }

    public Colores getCarta() {
        return carta;
    }

    public void setCarta(Colores carta) {
        this.carta = carta;
    }

    public abstract boolean validadCarta(Carta carta);
}
