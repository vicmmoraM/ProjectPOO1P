package ec.edu.espol;
import java.util.Scanner;

public class ComodinEspecial extends CartaComodin{
    public ComodinEspecial(Colores carta, String simbolo){
        super(carta, simbolo); //creada porque hay comodines de color N :o
    }

     public static Colores elegirColor(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Elija el color de la carta [R-A-V-Z]: ");
        String color = sc.nextLine().toUpperCase(); //convertir el texto en mayúscula
        while(!(color.equals(("R")) || (color.equals("A"))|| (color.equals("V")) || (color.equals("Z")))) //validar que sea un color válido
        {
            System.out.print("Elija un color válido D:< [R-A-V-Z]: ");
            color = sc.nextLine().toUpperCase();
        }

        if(color.equals("R")){
            return Colores.R;
        }

        if(color.equals("A")){
            return Colores.A;
        }

        if(color.equals("V")){
            return Colores.V;
        }

        else{
            return Colores.Z;
        }
    }
}
