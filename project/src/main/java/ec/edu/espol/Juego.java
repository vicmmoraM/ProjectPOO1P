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
    
    public static int sinCarta(Jugador j1, Bot bot, CartaBaraja mazo,int turno,Carta cartaActual){
        int contador = 0;
        if(turno == 1){
            if(cartaActual instanceof CartaNormal){
                CartaNormal carta = (CartaNormal)cartaActual;
                for(Carta c : j1.getBarajaJugador()){
                    if(!(c.validarCarta(carta))){
                        contador++;
                    }
                } 
            } 
            if(cartaActual instanceof ComodinEspecial){
                ComodinEspecial ce = (ComodinEspecial) cartaActual;
                for(Carta c: j1.getBarajaJugador()){
                    if(!(c.getCarta().equals(Colores.N))){
                        contador++;
                    }
                }
            }
            if(cartaActual instanceof CartaComodin){
                CartaComodin cc = (CartaComodin)cartaActual;
                for(Carta c : j1.getBarajaJugador()){
                    if(!(cc.validarCarta(c))){
                        contador++;
                    }
                }
            }
            if(contador == j1.getBarajaJugador().size()){
                j1.getBarajaJugador().add(mazo.chuparCarta());
                turno-=1;
                return turno;
            }
            return turno;
        }

        else{
            if(cartaActual instanceof CartaNormal){
                CartaNormal carta = (CartaNormal)cartaActual;
                for(Carta c : bot.getBarajaBot()){
                    if(!(c.validarCarta(carta))){
                        contador++;
                    }
                } 
            } 
            if(cartaActual instanceof CartaComodin){
                CartaComodin cc = (CartaComodin)cartaActual;
                for(Carta c : bot.getBarajaBot()){
                    if(!(cc.validarCarta(c))){
                        contador++;
                    }
                }
            }
            if(contador == bot.getBarajaBot().size()){
                bot.getBarajaBot().add(mazo.chuparCarta());
                turno += 1;
                return turno;
                }
            return turno;
        }
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
        // Empieza el Juego
        while (!(j1.getBarajaJugador().isEmpty() || bot.getBarajaBot().isEmpty())) {
            // Verificar si el jugador tiene una carta válida para lanzar
            turno = sinCarta(j1, bot, mazo, turno, cartaInicial);
    
            if (turno == 1) {
                System.out.print("Escoge que carta quieres: (1 al " + (j1.getBarajaJugador().size()) + "): ");
                int index = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea pendiente
    
                System.out.print("Baraja Jugador:");
                System.out.println(j1.getBarajaJugador());
                // Validación Carta Correcta
        
                while (index < 1 || index > j1.getBarajaJugador().size()) {
                    System.out.println("Ingrese un valor dentro del rango especificado!! >:|");
                    System.out.println(j1.getBarajaJugador());
                    System.out.print("Escoge que carta quieres: (1 al " + (j1.getBarajaJugador().size()) + "): ");
                    index = sc.nextInt();
                }
               
                // Generamos cartaJugador
                Carta cartaJugador = j1.getBarajaJugador().get(index - 1);
                cartaJugador = j1.lanzarCarta(cartaInicial, cartaJugador, j1.getBarajaJugador());
                System.out.println("-------------------------");
                System.out.println("Carta en el tablero: " + cartaJugador);
                System.out.println("-------------------------");
                System.out.print("Baraja Jugador: ");
                System.out.println(j1.getBarajaJugador());
                System.out.println("-------------------------");
                // Aplicar efecto del comodín si es necesario
                if (cartaJugador instanceof ComodinEspecial) {
                    Colores color = Jugador.comodinesEspeciales(cartaJugador, j1, bot, mazo, 1);
                    Carta cartaNueva = new CartaNormal(color, 10);
                    j1.getBarajaJugador().remove(cartaJugador);
                    System.out.println("-------------------------");
                    System.out.print("Baraja Jugador: ");
                    System.out.println(j1.getBarajaJugador());
                    System.out.println("-------------------------");
                    cartaInicial = cartaNueva;
                    j1.mostrarInformacion();
                    turno = 1;
                } else if (cartaJugador instanceof CartaComodin) {
                    int turnoNuevo = Jugador.comodin(cartaJugador, j1, bot, mazo, 1);
                    j1.getBarajaJugador().remove(cartaJugador);
                    System.out.println("Entre al método Comodin");
                    System.out.println(j1.getBarajaJugador());
                    turno = turnoNuevo;
                } else {
                    cartaInicial = cartaJugador;
                    j1.getBarajaJugador().remove(cartaJugador); // Quitar la carta jugada
                    turno = sinCarta(j1, bot, mazo, 1, cartaInicial);
                    turno = 0; // Cambio de turno
                    j1.mostrarInformacion();
                }
                if (j1.getBarajaJugador().size() == 1) {
                    System.out.println("UNOOOOOO");
                }
            } else {
                // turno del BOT
                System.out.println("--------------------");
                System.out.print("Baraja BOT: ");
                System.out.println(bot.getBarajaBot());
                System.out.println("Turno del BOT (ADRIAN)");
    
                // Verificar si el bot tiene una carta válida para lanzar
                turno = sinCarta(j1, bot, mazo, turno, cartaInicial);
    
                // Carta aleatoria para el bot

    
                // Intentar lanzar la carta del bot
                Carta cartaBot = bot.lanzarCartaB(cartaInicial, bot.getBarajaBot());
                while(bot.lanzarCartaB(cartaBot,bot.getBarajaBot()) == null){
                    if(cartaBot == null){
                        int index = mazo.getCartas().size() - 1;
                        cartaBot = mazo.getCartas().get( index - 1 );
                        bot.getBarajaBot().add(cartaBot);
                        mazo.getCartas().remove(cartaBot);
                    }
                }
                
    
                System.out.println("Carta en el tablero: " + cartaBot);
                
                // Aplicar efecto del comodín si es necesario
                if (cartaBot instanceof ComodinEspecial) {
                    Colores color1 = Jugador.comodinesEspeciales(cartaInicial, j1, bot, mazo, turno);
                    Random r = new Random();
                    Colores[] color = Colores.values();
                    int numero = r.nextInt(4);
                    Carta cartaNueva = new CartaNormal(color[numero], 10);
                    System.out.println("Carta en el tablero: "+ cartaNueva);
                    bot.getBarajaBot().remove(cartaBot);
                    System.out.println(bot.getBarajaBot());
                    cartaInicial = cartaNueva;
                    j1.mostrarInformacion();
                    turno = 0;

                }else if (cartaBot instanceof CartaComodin) {
                    int turnoNuevo = Jugador.comodin(cartaBot, j1, bot, mazo, 0);
                    bot.getBarajaBot().remove(cartaBot);
                    turno = turnoNuevo; 
                }
                else {
                    cartaInicial = cartaBot;
                    bot.getBarajaBot().remove(cartaBot); // Quitar la carta jugada
                    System.out.println(bot.getBarajaBot());
                    turno = 1; // Cambio de turno
                    System.out.println("--------------------");
                }
                if (bot.getBarajaBot().size() == 1) {
                    System.out.println("UNO; bot: I'd win");
                }
            }
        }
    
        sc.close(); // Cerrar el escáner al final del juego
        if(j1.getBarajaJugador().isEmpty()){
            System.out.println("Felicades "+ j1.getNombre() + "ganaste!!");
        } else{
            System.out.println("La Maquina Gano");
        }
    }
}