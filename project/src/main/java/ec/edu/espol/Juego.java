package ec.edu.espol;
import java.util.Scanner;
import java.util.Random;

public class Juego {
    private Jugador bot;
    private Jugador jugador;

    public Juego(Jugador jugador,Jugador bot){
        this.bot=bot;
        this.jugador=jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public Jugador getBot() {
        return bot;
    }
    public void setBot(Jugador bot) {
        this.bot = bot;
    }

    public static Jugador agregarJugador(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresa tu nombre: ");
        String nombre = sc.nextLine();
        Jugador jugador = new Jugador(nombre);
        return jugador;
    }

    public static void ComodinesEspeciales(Carta c, Jugador j1, Jugador bot, CartaBaraja mazo, int t){
        Scanner sc = new Scanner(System.in);
        if(c instanceof ComodinEspecial){ 
            ComodinEspecial ce = (ComodinEspecial) c;
            if(ce.getSimbolo().equals("+4")){
                System.out.println("Toma cuatro cartas :()");
                for(int i = 0; i<4 ; i++){
                    Carta cartaChupada = mazo.chuparCarta();
                    if(t == 0){
                        j1.getBarajaJugador().add(cartaChupada);
                        t = 0;
                    }
                    else{
                        bot.getBarajaJugador().add(cartaChupada);
                        t = 1;
                    }   
                }
            }
            if(ce.getSimbolo().equals("+2")){
                System.out.println("Toma dos cartas :()");
                for(int i = 0; i<2 ; i++){
                    Carta cartaChupada = mazo.chuparCarta();
                    if(t == 0){
                        j1.getBarajaJugador().add(cartaChupada);
                        t = 0;
                    }
                    else{
                        bot.getBarajaJugador().add(cartaChupada);
                        t = 1;
                    }
                }
            }
        }
        else{
            CartaComodin cc = (CartaComodin) c;
            if(cc.getSimbolo().equals("^")){ // Reverse
                if(t == 1){
                    System.out.println("El bot pierde su turno");
                    t = 1;
                }
                else{
                    System.out.println(j1.getNombre() + "pierdes tu turno :(");
                    t = 0;
                }
            }
        }
    }

    public static void  iniciarJuego(Jugador j1,Jugador bot,CartaBaraja mazo){
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int indice = r.nextInt(mazo.getCartas().size()-1);

        // Lanzamiento de Carta Inicial
        int Turno = 1;
        while(!(mazo.getCartas().get(indice) instanceof CartaNormal)){
            indice = r.nextInt(mazo.getCartas().size()-1);
        }

        System.out.print("Carta Inicial: ");
        System.out.println(mazo.getCartas().get(indice));
        Carta CartaTablero = mazo.getCartas().get(indice); //Carta Actual
        mazo.removerCarta(indice);

        // Empieza el Juego

        while(!(j1.getBarajaJugador().isEmpty() || bot.getBarajaJugador().isEmpty())){
            System.out.println("Escoge que carta quieres: (0 al " + (j1.getBarajaJugador().size() - 1) + ")");
            int index = sc.nextInt();

            if(Turno == 1){

                System.out.print("Baraja Jugador:");
                System.out.println(j1.getBarajaJugador());

                while(index < 0 || index > (j1.getBarajaJugador().size()-1)){
                    System.out.println("Ingrese un valor dentro del rango especificado!! >:|");
                    System.out.println("Escoge que carta quieres: (0 al " + (j1.getBarajaJugador().size() - 1) + ")");
                    index = sc.nextInt();
                    sc.nextLine();
                }
                Carta CartaJugador = j1.getBarajaJugador().get(index);
                
                Carta cartaJugador =Jugador.LanzarCarta(CartaTablero,CartaJugador,(j1.getBarajaJugador()));
                System.out.println("Carta en el tablero: "+ cartaJugador);
                CartaTablero = cartaJugador;
                j1.getBarajaJugador().remove(index);
                Turno = Turno - 1;
            }
            if(Turno == 0){
                System.out.print("Baraja BOT: ");
                System.out.println(bot.getBarajaJugador());
                System.out.println("Turno del BOT(ADRIAN)");
    
                int num = Bot.Randomnum((bot.getBarajaJugador().size()));
                Carta cartaBot = bot.getBarajaJugador().get(num);
                Bot.LanzarCartaB(CartaTablero, cartaBot, bot.getBarajaJugador());
                System.out.println("Carta en el tablero: "+ cartaBot);
                CartaTablero = cartaBot;
                bot.getBarajaJugador().remove(num);
                Turno = Turno + 1;
            }

        }
    }
}