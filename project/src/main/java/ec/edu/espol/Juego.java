package ec.edu.espol;
import java.util.Scanner;
import java.util.List;
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
    
    public static int sinCarta(Jugador j1, Bot bot, CartaBaraja mazo, int turno, Carta cartaActual) {
        if (turno == 1) {
            if (verificarCartasNoCoinciden(j1.getBarajaJugador(), cartaActual)) {
                j1.getBarajaJugador().add(mazo.TomarCarta());
                return turno - 1;
            }
        } else {
            if (verificarCartasNoCoinciden(bot.getBarajaBot(), cartaActual)) {
                bot.getBarajaBot().add(mazo.TomarCarta());
                return turno + 1;
            }
        }
        return turno;
    }

    private static boolean verificarCartasNoCoinciden(List<Carta> baraja, Carta cartaActual) {
        int contador = 0;
        for (Carta c : baraja) {
            if (!validarCarta(c, cartaActual)) {
                contador++;
            }
        }
        return contador == baraja.size();
    }

    private static boolean validarCarta(Carta c, Carta cartaActual) {
        if (cartaActual instanceof CartaNormal) {
            return c.validarCarta((CartaNormal) cartaActual);
        } else if (cartaActual instanceof ComodinEspecial) {
            return !c.getColor().equals(Colores.N);
        } else if (cartaActual instanceof CartaComodin) {
            return ((CartaComodin) cartaActual).validarCarta(c);
        }
        return false;
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
        int turno = 1; //Turno Jugador
    
        while (!(j1.getBarajaJugador().isEmpty() || bot.getBarajaBot().isEmpty())) {
            if (turno == 1) {
                turno = TurnoJugador(j1, bot, mazo, cartaInicial, sc, turno);
            } else {
                turno = TurnoBot(j1, bot, mazo, cartaInicial);
            }
        }
    
        sc.close(); 
        if (j1.getBarajaJugador().isEmpty()) {
            System.out.println("Felicidades " + j1.getNombre() + " ganaste!!");
        } else {
            System.out.println("La Maquina Ganó");
        }
    }

    private static int TurnoJugador(Jugador j1, Bot bot, CartaBaraja mazo, Carta cartaInicial, Scanner sc, int turno) {
        System.out.print("Escoge qué carta quieres: (1 al " + j1.getBarajaJugador().size() + "): ");
        int index = sc.nextInt();
        sc.nextLine(); 
        index = validarIndice(index, j1.getBarajaJugador().size(), sc);
    
        Carta cartaJugador = j1.getBarajaJugador().get(index - 1);
        cartaJugador = j1.lanzarCarta(cartaInicial, cartaJugador, j1.getBarajaJugador());
    
        System.out.println("-------------------------");
        System.out.println("Carta en el tablero: " + cartaJugador);
        System.out.println("-------------------------");
        System.out.println("Baraja Jugador: " + j1.getBarajaJugador());
        System.out.println("-------------------------");
    
        if (cartaJugador instanceof ComodinEspecial) {
            ComodinEspecial(j1, bot, mazo);
            cartaInicial = new CartaNormal(Colores.values()[new Random().nextInt(Colores.values().length)], 10);
            turno = 0; // Cambio de turno
        } else if (cartaJugador instanceof CartaComodin) {
            int turnoNuevo = Jugador.comodin(cartaJugador, j1, bot, mazo, 1);
            j1.getBarajaJugador().remove(cartaJugador);
            return turnoNuevo;
        } else {
            cartaInicial = cartaJugador;
            j1.getBarajaJugador().remove(cartaJugador); 
            turno = sinCarta(j1, bot, mazo, 1, cartaInicial);
            j1.mostrarInformacion();
            turno = 0; 
        }
    
        if (j1.getBarajaJugador().size() == 1) {
            System.out.println("¡UNOOOOOO!");
        }
    
        return turno;
    }

    private static int validarIndice(int index, int tamañoBaraja, Scanner sc) {
        while (index < 1 || index > tamañoBaraja) {
            System.out.println("Ingrese un valor dentro del rango especificado!! >:|");
            System.out.print("Escoge qué carta quieres: (1 al " + tamañoBaraja + "): ");
            index = sc.nextInt();
        }
        return index;
    }

    private static void ComodinEspecial(Jugador j1, Bot bot, CartaBaraja mazo) {
        Colores color = Jugador.comodinesEspeciales(null, j1, bot, mazo, 1); 
        Carta cartaNueva = new CartaNormal(color, 10);
        j1.getBarajaJugador().removeIf(carta -> carta instanceof ComodinEspecial);
        System.out.println("-------------------------");
        System.out.println("Baraja Jugador: " + j1.getBarajaJugador());
        System.out.println("-------------------------");
    }

    private static int TurnoBot(Jugador j1, Bot bot, CartaBaraja mazo, Carta cartaInicial) {
        System.out.println("--------------------");
        System.out.println("Baraja BOT: " + bot.getBarajaBot());
        System.out.println("Turno del BOT");

        int turno = sinCarta(j1, bot, mazo, 0, cartaInicial);

        Carta cartaBot = bot.lanzarCartaB(cartaInicial, bot.getBarajaBot());

        if (cartaBot == null) {
            cartaBot = mazo.getCartas().get(mazo.getCartas().size() - 1);
            bot.getBarajaBot().add(cartaBot);
            mazo.getCartas().remove(cartaBot);
        }

        System.out.println("Carta en el tablero: " + cartaBot);

        if (cartaBot instanceof ComodinEspecial) {
            ComodinEspecialBot(bot, j1, mazo);
            cartaInicial = new CartaNormal(Colores.values()[new Random().nextInt(4)], 10);
        } else if (cartaBot instanceof CartaComodin) {
            int turnoNuevo = Jugador.comodin(cartaBot, j1, bot, mazo, 0);
            bot.getBarajaBot().remove(cartaBot);
            return turnoNuevo;
        } else {
            cartaInicial = cartaBot;
            bot.getBarajaBot().remove(cartaBot); // Remover la carta jugada nya
            System.out.println("Baraja BOT: " + bot.getBarajaBot());
        }

        if (bot.getBarajaBot().size() == 1) {
            System.out.println("UNO; bot: I'd win");
        }

        return 1; 
    }  

    private static void ComodinEspecialBot(Bot bot, Jugador j1, CartaBaraja mazo) {
        Colores color1 = Jugador.comodinesEspeciales(null, j1, bot, mazo, 0); // Ajustar si es necesario
        Carta cartaNueva = new CartaNormal(color1, 10);
        bot.getBarajaBot().removeIf(carta -> carta instanceof ComodinEspecial);
        System.out.println("Carta en el tablero: " + cartaNueva);
        System.out.println("Baraja BOT: " + bot.getBarajaBot());
    }
}