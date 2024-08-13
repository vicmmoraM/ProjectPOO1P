package ec.edu.espol;

public abstract class Carta {
    private Colores color;

    protected Carta(Colores color){
        this.color = color;
    }

    public Colores getColor() {
        return color;
    }

    public void setColor(Colores color) {
        this.color = color;
    }

    public abstract boolean validarCarta(Carta color);

    @Override
    public String toString() {
        return color + " ";
    }

}
