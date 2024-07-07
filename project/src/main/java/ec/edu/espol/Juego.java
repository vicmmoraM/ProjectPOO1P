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
    
    public static Carta cartaInicial(CartaBaraja barajaCompleta){
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int indice = r.nextInt(barajaCompleta.getCartas().size() - 1);

        // Lanzamiento de Carta Inicial
        while (!(barajaCompleta.getCartas().get(indice) instanceof CartaNormal)) {
            indice = r.nextInt(barajaCompleta.getCartas().size() - 1);
        }

        System.out.print("Carta Actual: ");
        System.out.println(barajaCompleta.getCartas().get(indice));
        Carta cartaTablero = barajaCompleta.getCartas().get(indice); // Carta Actual
        barajaCompleta.removerCarta(indice);
        return cartaTablero;
    }

    public static void iniciarJuego(Jugador j1, Bot bot, CartaBaraja mazo, Carta cartaInicial) {
        Scanner sc = new Scanner(System.in);
        int turno = 1;
        // Empieza el Juego
        while (!(j1.getBarajaJugador().isEmpty() || bot.getBarajaBot().isEmpty())) {
            if (turno == 1) {
                System.out.print("Escoge que carta quieres: (0 al " + (j1.getBarajaJugador().size() - 1) + "): ");
                int index = sc.nextInt();
    
                System.out.print("Baraja Jugador:");
                System.out.println(j1.getBarajaJugador());

                //Validación Carta Correcta

                while (index < 0 || index > (j1.getBarajaJugador().size() - 1)) {
                    System.out.println("Ingrese un valor dentro del rango especificado!! >:|");
                    System.out.print("Escoge que carta quieres: (0 al " + (j1.getBarajaJugador().size() - 1) + "): ");
                    index = sc.nextInt();
                    sc.nextLine();
                }
                //Generamos cartaJugador 
                Carta cartaJugador = j1.getBarajaJugador().get(index);
                cartaJugador = Jugador.LanzarCarta(cartaInicial, cartaJugador, j1.getBarajaJugador());
    
                System.out.println("Carta en el tablero: " + cartaJugador);
                // Aplicar efecto del comodín si es necesario
                if (cartaJugador instanceof ComodinEspecial || cartaJugador instanceof CartaComodin) {
                    Colores color = Jugador.ComodinesEspeciales(cartaJugador, j1, bot, mazo, 1);
                    Carta cartaNueva = new CartaNormal(color,10);
                    j1.getBarajaJugador().remove(cartaJugador);
                    cartaInicial = cartaNueva;
                    j1.mostrarInformacion(); 
                    turno = 1;
                }else{
                    cartaInicial = cartaJugador;
                    j1.getBarajaJugador().remove(cartaJugador); // Quitar la carta jugada
                    turno = 0; // Cambio de turno
                    j1.mostrarInformacion(); 
                }
                if(j1.getBarajaJugador().size() == 1){
                    System.out.println("UNOOOOOO");
                }
            } else {
                // turno del BOT
                System.out.println("--------------------");
                System.out.print("Baraja BOT: ");
                System.out.println(bot.getBarajaBot());
                System.out.println("Turno del BOT (ADRIAN)");
                // Carta aleatoria para el bot
                int num = Bot.randomnum(bot.getBarajaBot().size());
                Carta cartaBot = bot.getBarajaBot().get(num);
                Bot.lanzarCartaB(cartaInicial, cartaBot, bot.getBarajaBot());
    
                System.out.println("Carta en el tablero: " + cartaBot);
    
                // Aplicar efecto del comodín si es necesario
                if (cartaBot instanceof ComodinEspecial || cartaBot instanceof CartaComodin) {
                    Jugador.ComodinesEspeciales(cartaBot, j1, bot, mazo, 0);
                    Colores color = Jugador.ComodinesEspeciales(cartaBot, j1, bot, mazo, 1);
                    Carta cartaNueva = new CartaNormal(color,10);
                    bot.getBarajaBot().remove(cartaBot);
                    cartaInicial = cartaNueva;
                    j1.mostrarInformacion(); 
                    turno = 0;
                }
                else{
                    cartaInicial = cartaBot;
                    bot.getBarajaBot().remove(cartaBot); // Quitar la carta jugada
                    turno = 1; // Cambio de turno
                    System.out.println("--------------------");
                }
                if(bot.getBarajaBot().size() == 1){
                    System.out.println("UNO; bot: i'd win");
                }
                
            }
        }
    
        sc.close(); // Cerrar el escáner al final del juego :(
    }
}
