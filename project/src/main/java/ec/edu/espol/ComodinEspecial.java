package ec.edu.espol;
import java.util.Scanner;

public class ComodinEspecial extends CartaComodin{
    public ComodinEspecial(Colores color, String simbolo){
        super(color, simbolo); //creada porque hay comodines de color N :o
    }

    public static Colores elegirColor(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Elija el color de la carta [R-A-V-Z]: ");
        String color = sc.nextLine().toUpperCase(); //convertir el texto en mayúscula
        String colorval = ComodinEspecial.colorValido(color,sc);
        return switch(colorval){
            case "R" -> Colores.R;
            case "A" -> Colores.A;
            case "V" -> Colores.V;
            default -> Colores.Z;
        };
    }

    public static String colorValido(String color,Scanner sc){
        while(!(color.equals(("R")) || (color.equals("A"))|| (color.equals("V")) || (color.equals("Z")))){ //validar que sea un color válido
            System.out.print("Elija un color válido D:< [R-A-V-Z]: ");
            color = sc.nextLine().toUpperCase();
        }
        return color;
    }
}
