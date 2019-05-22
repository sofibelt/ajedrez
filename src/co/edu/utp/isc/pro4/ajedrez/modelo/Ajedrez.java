/**
 * Ajedrez.java
 * 
 * Mayo 2019
 * 
 * realizado por Ana Sofia Beltran Rios 1004716847
 * @author utp: odau
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.ui.PnlTablero;

/** clase Ajedrez, cuya funcion es simular el juego ajedrez*/
public class Ajedrez {
/** lo que se pretende es simular todas las opciones que nos daria el juego real, 
 *  permitiendole al jugador hacer todo lo que se podria en un ajedrez normal*/
    
    //Variables
    private PnlTablero pnlTablero;
    private Jugador[] jugadores;
    private Tablero tablero;
    private Cronometro cronometro;
    private int turno;
    private boolean terminado;
    private boolean terminadoPorJaque;
    
    //Constructores
    public Ajedrez() {
        jugadores = new Jugador[2];
        tablero = new Tablero();
        cronometro = new Cronometro();
        turno = 0;
        terminado = false;
        terminadoPorJaque=false;
    }

    public Ajedrez(Jugador jugador1, Jugador jugador2) {
        this();
        this.jugadores[0] = jugador1;
        this.jugadores[1] = jugador2;
    }
    
    //Metodos
    public void setPnlTablero(PnlTablero pnlTablero) {
        this.pnlTablero = pnlTablero;
        pnlTablero.setTablero(tablero);
    }

    public void jugar() {
        //se le asigana a cada jugador el respectivo tabler y se hubican las fichas en el tablero
        jugadores[0].setAjedrez(this);
        jugadores[1].setAjedrez(this);

        ubicarFichasTablero();
        cronometro.iniciar();
        mostrarTablero();
        
    }

    public void cambioTurno() {
        //se cambia de turno
        turno = (turno == 0 ? 1 : 0);
        cronometro.cambio();
    }

    private boolean validarJaqueMate( Casilla piezasDefensa[], Casilla piezasJaque[], Casilla rey, Tablero copia) {
        boolean validarJaqueMate = true;
        int i = 0,j=0; //variables temporales
        while (i < piezasJaque.length && piezasJaque[i] != null) {
            i++;
        }
        //se evalua cuantas fichas hacen jaque
        if (i == 1) { 
            while(j<piezasDefensa.length&&piezasDefensa[j]!=null){
                //TODO SE VA A REALIZAR EN UN TABLERO COPIA, PARA EVITAR MODIFICAR EL ORIGINAL
                //camino entre la pieza que defiende y la que hace jaque
                Casilla fichas[] = copia.getCamino(piezasDefensa[j],piezasJaque[0]);              
                int contrincante=(turno == 0 ? 1 : 0);
                //se hacen copias de la primera y segunda posicion para regresar la jugada
                Ficha primeraPosicionCopia=piezasDefensa[j].getFicha();
                Ficha segundaPosicionCopia=piezasJaque[0].getFicha();   
                //se evalua si la pieza de defensa se puede comer al atacante
                boolean validacionMovimiento= piezasDefensa[j].getFicha().validarMovimiento(piezasDefensa[j],piezasJaque[0],fichas,jugadores[contrincante].getColor());
                if(validacionMovimiento&&!(piezasDefensa[j].getFicha() instanceof Rey)){ //si se lo puede comer procede a hacerlo
                            piezasDefensa[j].getFicha().mover(piezasDefensa[j],piezasJaque[0],fichas,piezasDefensa[j].getFicha().getColor());
                            //se evalua cuales son las piezas que pueden generar jaque
                            Casilla fichasAtacantes[] = copia.buscarJaque(jugadores[contrincante].getColor()); 
                            //se verifica que piezas hacen jaque
                            Casilla jaque[]=verificarJaque(fichasAtacantes,rey,copia);
                            int a = 0;//variable temporal
                            boolean enJaque=false;
                            //se evalua que no haya jaque en la nueva posicion
                            while (a < jaque.length && jaque[a] != null) {
                                System.out.println("pieza que le queda haciendo jaque: "+jaque[a]);
                                enJaque=true;
                                a++;
                            }
                            //si no hay jaque en la nueva posicion entonces se devuelve el movimiento, para que el tablero no cambie
                            if (!enJaque) {
                                System.out.println("como se evito el jaque: ");
                                copia.mostrarTablero();
                                piezasDefensa[j].setFicha(primeraPosicionCopia);
                                piezasJaque[0].setFicha(segundaPosicionCopia);
                                System.out.println("regresa el tablero a la normalidad: ");
                                copia.mostrarTablero();
                                return false;
                            }else{
                                //si hay jaque se indica y se devuelven las piezas
                                piezasDefensa[j].setFicha(primeraPosicionCopia);
                                piezasJaque[0].setFicha(segundaPosicionCopia);
                                System.out.println("no se pudo intentar mover, porque entro en jaque");
                            }

                        }else{
                            //si la ficha no pudo comerse al atacante, se evalua si se puede poner en medio del atacante y el rey
                            Casilla camino[]= copia.getCamino(piezasJaque[0],rey);
                            int a=0;//variable temporal
                            //se evalua si la pieza se puede mover a alguna de las casillas entre el atacante y el rey
                            //y que en medio de esa defenza no genere jaque
                            while(a<camino.length&&camino[a]!=null&&(piezasDefensa[j]!=rey)){
                                Casilla evitarJaque[]=copia.getCamino(piezasDefensa[j], camino[a]);
                                validacionMovimiento= piezasDefensa[j].getFicha().validarMovimiento(piezasDefensa[j],camino[a],evitarJaque,jugadores[contrincante].getColor());
                                if(validacionMovimiento){
                                    primeraPosicionCopia=piezasDefensa[j].getFicha();
                                    segundaPosicionCopia=camino[a].getFicha();
                                    piezasDefensa[j].getFicha().mover(piezasDefensa[j],camino[a],evitarJaque,jugadores[contrincante].getColor());
                                    Casilla fichasAtacantes[] = copia.buscarJaque(rey.getFicha().getColor()); 
                                    Casilla jaque[]=verificarJaque(fichasAtacantes,rey,copia);
                                    int b = 0;
                                    boolean enJaque=false;
                                    while (b < jaque.length && jaque[b] != null) {
                                        System.out.println("pieza que le queda haciendo jaque: "+jaque[b]);
                                        enJaque=true;
                                        b++;
                                    }
                                    if (!enJaque) {
                                        System.out.println("como se evito el jaque: ");
                                        copia.mostrarTablero();
                                        piezasDefensa[j].setFicha(primeraPosicionCopia);
                                        camino[a].setFicha(segundaPosicionCopia);
                                        copia.mostrarTablero();
                                        return false;
                                    }else{
                                        piezasDefensa[j].setFicha(primeraPosicionCopia);
                                        piezasJaque[0].setFicha(segundaPosicionCopia);
                                        System.out.println("no se pudo intentar mover, porque entro en jaque");
                                    }   
                                    
                                }
                        a++;
                    }
                            
                }
                j++;
            }
         //si no hay ficha que pueda defender al rey o intervenir el ataque, se procede a mover al rey
         int b=0;//variable temporal
         while(b<8){
             //se evalua cual es mi contrincante
             int contrincante=(turno == 0 ? 1 : 0);
             //se evalua cuales son las posibles casillas que puede asumir el rey
             Casilla casillas[]=copia.encontrarCaminoRey(rey);
             if(casillas[b]!=null){//se mueve el rey a cada casilla en la que su movmiento es permitido, para ver cual evita el jaque
                    //camino entre el rey y su proxima casilla(basio)
                    Casilla evitarJaque[]=copia.getCamino(rey,casillas[b]);
                    //se evalua si se puede generar el movimiento
                    boolean validacionMovimiento= rey.getFicha().validarMovimiento(rey,casillas[b],evitarJaque,rey.getFicha().getColor());
                    //se crea copia de los movimientos para luedo retrocederlo
                    Ficha primeraPosicionCopia=rey.getFicha();
                    Ficha segundaPosicionCopia=casillas[b].getFicha(); 
                    //si el movimiento es valido se procede a mover la ficha a su nueva casilla y verificar que no se hara jaque en esta
                    if(validacionMovimiento){
                         rey.getFicha().mover(rey,casillas[b],evitarJaque,jugadores[contrincante].getColor());   
                         Casilla fichasAtacantes[] = copia.buscarJaque(jugadores[contrincante].getColor()); 
                         Casilla jaque[]=verificarJaque(fichasAtacantes,casillas[b],copia);
                         int a = 0;//variable temporal
                        boolean enJaque=false;
                         while (a < jaque.length && jaque[a] != null) {
                                 System.out.println("pieza que le queda haciendo jaque: "+jaque[a]);
                                 enJaque=true;
                                 a++;
                            }
                        if (!enJaque) {
                            System.out.println("como se evito el jaque: ");
                            copia.mostrarTablero();
                            rey.setFicha(primeraPosicionCopia);
                            casillas[b].setFicha(segundaPosicionCopia);  
                            System.out.println("regresa el tablero a la normalidad: ");
                            copia.mostrarTablero();
                            return false;
                        }else{
                            rey.setFicha(primeraPosicionCopia);
                            casillas[b].setFicha(segundaPosicionCopia); 
                            System.out.println("no se pudo intentar mover, porque entro en jaque");
                        }
                                      }
          }
          b++;
         }
         
        }else{
          //si existe mas de una ficha haciendo jaque, obligatoriamente hay que mover al rey
          int b=0;//variable temporal
          while(b<8){
          int contrincante=(turno == 0 ? 1 : 0);
          Casilla casillas[]=copia.encontrarCaminoRey(rey);
          if(casillas[b]!=null){
                Casilla evitarJaque[]=copia.getCamino(rey,casillas[b]);
                boolean validacionMovimiento= rey.getFicha().validarMovimiento(rey,casillas[b],evitarJaque,rey.getFicha().getColor());
                Ficha primeraPosicionCopia=rey.getFicha();
                Ficha segundaPosicionCopia=casillas[b].getFicha(); 
                if(validacionMovimiento){
                  rey.getFicha().mover(rey,casillas[b],evitarJaque,rey.getFicha().getColor());   
                  Casilla fichasAtacantes[] = copia.buscarJaque(casillas[b].getFicha().getColor()); 
                  Casilla jaque[]=verificarJaque(fichasAtacantes,casillas[b],copia);
                  int a = 0;//variable temporal
                  boolean enJaque=false;
                  while (a < jaque.length && jaque[a] != null) {
                      System.out.println("pieza que le queda haciendo jaque: "+jaque[a]);
                     enJaque=true;
                     a++;
                  }
                  if (!enJaque) {
                      System.out.println("como se evito el jaque: ");
                      copia.mostrarTablero();
                      rey.setFicha(primeraPosicionCopia);
                      casillas[b].setFicha(segundaPosicionCopia);  
                      System.out.println("regresa el tablero a la normalidad: ");
                      copia.mostrarTablero();
                      return false;
                  }else{
                      rey.setFicha(primeraPosicionCopia);
                      casillas[b].setFicha(segundaPosicionCopia); 
                      System.out.println("no se pudo intentar mover, porque entro en jaque");
                  }
                                      }
          }
          b++;
         }
       
        } 
        return validarJaqueMate;
    }
    
   
    private boolean validarTablas(Color jugador, Color contrincante) {
        //se evalua si el juego ha quedado en tablas (solamente reyes, o reyes y un alfil o un caballo)
        //se evaluan cuales son los aliados
        Casilla aliados[]=tablero.buscarJaque(contrincante);
        //se evaluan cuales son los enemigos
        Casilla enemigos[]=tablero.buscarJaque(jugador);
        int i=0,numeroAliados=0,numeroContrincante=0;//variables temporales
        //se evalua cuantos aliados y cuantos enemigos aun estan en el juego
        while(i<aliados.length&&aliados[i]!=null){
            numeroAliados++;
            i++;
        }
        i=0;//variable temporal
        while(i<enemigos.length&&enemigos[i]!=null){
            numeroContrincante++;
            i++;
        }
        int contador=0;//variable temporal
        //si se hayan mas de un atacante y un defensor, se procede a evaluar que otras fichas se encuentran en el tablero
            if(numeroContrincante!=1&&numeroAliados==1){
                while(contador<enemigos.length&&enemigos[contador]!=null){
                    if(enemigos[contador].getFicha() instanceof Peon||enemigos[contador].getFicha() instanceof Torre||
                            enemigos[contador].getFicha() instanceof Reina){
                            return false;
                    }
                    contador++;
                }
                System.out.println("tablar porque se quedo solo con el rey y otra ficha (contrincante)");
                return true;
            }else{
               if(numeroAliados!=1&&numeroContrincante==1){
                  while(contador<aliados.length&&aliados[contador]!=null){
                    if(aliados[contador].getFicha() instanceof Peon||aliados[contador].getFicha() instanceof Torre||
                            aliados[contador].getFicha() instanceof Reina){
                            return false;
                    }
                    contador++;
                }
                  System.out.println("tablas porque se quedo solo con el rey y otra ficha (aliado)");
                return true; 
               } 
            }
            if(numeroAliados==1&&numeroContrincante==1){
                System.out.println("solo reyes");
                return true;
            }
          
        return false;
    }

    public boolean rendirse() {
        terminado = true;
        return terminado;
    }
    
    public boolean estadoDelJuego(){
        //evalua en que estado se encuentra el juego
        if(terminado){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean terminadoJaque(){
        //evalua si el juego termino por jaque
        return terminadoPorJaque;
    }

    private void ubicarFichasTablero() {
        asociarFichaTablero(tablero.getCasilla("A1"), new Torre(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("B1"), new Caballo(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("C1"), new Alfil(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("D1"), new Reina(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("E1"), new Rey(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("F1"), new Alfil(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("G1"), new Caballo(Color.BLANCO));
        asociarFichaTablero(tablero.getCasilla("H1"), new Torre(Color.BLANCO));

        asociarFichaTablero(tablero.getCasilla("A8"), new Torre(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("B8"), new Caballo(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("C8"), new Alfil(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("D8"), new Reina(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("E8"), new Rey(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("F8"), new Alfil(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("G8"), new Caballo(Color.NEGRO));
        asociarFichaTablero(tablero.getCasilla("H8"), new Torre(Color.NEGRO));

        for (int i = 0; i < 8; i++) {
            asociarFichaTablero(tablero.getCasilla(1, i), new Peon(Color.BLANCO));
            asociarFichaTablero(tablero.getCasilla(6, i), new Peon(Color.NEGRO));
        }

    }

    private void asociarFichaTablero(Casilla c, Ficha f) {
        f.setCasilla(c);
        c.setFicha(f);
    }

    private void mostrarTablero() {
        pnlTablero.updateUI();

    }

    public Jugador getJugador() {
        return jugadores[turno];
    }
    
    public Jugador getJugadorContrario(){
        int contrario = (turno == 0 ? 1 : 0);
        return jugadores[contrario];
    }

    public Casilla[] verificarJaque(Casilla jaque[], Casilla casillaRey,Tablero copia) {
        //se verifica si se hizo jaque
        Casilla[] peligroDeJaque = new Casilla[16];
        int i = 0, j = 0;//variables temporales
        //en el while se evalua si alguna de las fichas de la variable jaque, puede comerse al rey
        while ((i < jaque.length) && (jaque[i] != null)) {
           Casilla camino[] = copia.getCamino(jaque[i],casillaRey);
           if(jaque[i].getFicha().validarMovimiento(jaque[i], casillaRey,camino,jaque[i].getFicha().getColor())){
               //si efectivamente se lo pueden comer, son añadidas a el array de peligro jaque
               peligroDeJaque[j]=jaque[i];
               j++;
           }
            i++;
        }

        return peligroDeJaque;
    }
    

    public void moverFicha(String primeraPosicion, String segundaPosicion) {
        //se mueve la ficha de una posicion inicial a una final
        if(tablero.getCasilla(primeraPosicion)!=tablero.getCasilla(segundaPosicion)&&//se evalua que la casilla inicial tenga ficha, y que no se este moviendo a la misma casilla
                tablero.getCasilla(primeraPosicion).getFicha()!=null){
                //se crea una copia del tablero, para no mover el original si no hasta estar seguro del movimiento
                Tablero copia = tablero.duplicarTablero();
                //se crea la copia con la que se va a trabajar
                Ficha fichaCopia = copia.getCasilla(primeraPosicion).getFicha();
                //se visualiza camino entre la copia y la posicion donde se va a mover
                Casilla caminoCopia[] = copia.getCamino(copia.getCasilla(primeraPosicion), copia.getCasilla(segundaPosicion));
                //se crean las variables copia de los movimientos para retrocederlo de ser necesario
                Ficha primeraPosicionCopia=copia.getCasilla(primeraPosicion).getFicha();
                Ficha segundaPosicionCopia=copia.getCasilla(segundaPosicion).getFicha();
                //se mueve la variable copia
                fichaCopia.mover(copia.getCasilla(primeraPosicion), copia.getCasilla(segundaPosicion),
                        caminoCopia, jugadores[turno].getColor());
                //si no se puede mover, se verifica que no se este haciendo un comer al paso
                verificacionComerAlPaso(copia.getCasilla(primeraPosicion), copia.getCasilla(segundaPosicion),copia);
                copia.mostrarTablero();
                //se hace el procedimiento para buscar jaque en esta nueva posicion
                Casilla fichas[] = copia.buscarJaque(jugadores[turno].getColor());
                Casilla jaque[] = verificarJaque(fichas, copia.buscarRey(jugadores[turno].getColor()),copia);
                int i = 0;//variable temporal
                boolean enJaque=false;
                while (i < jaque.length && jaque[i] != null) {
                    System.out.println("ficha que hace jaque: "+jaque[i]);
                    enJaque=true;
                    i++;
                }
                if (enJaque) {  
                    //si la ficha copia queda en jaque, se indica y se retrocede el movimiento
                    copia.getCasilla(primeraPosicion).setFicha(primeraPosicionCopia);
                    copia.getCasilla(segundaPosicion).setFicha(segundaPosicionCopia);
                    copia.mostrarTablero();
                    System.out.println("no se pudo mover, porque entro en jaque");
                    return;
                }
                
                //si no queda en jaque se prosigue a cambiar permanentemente la ficha
                Casilla casillaInicial = tablero.getCasilla(primeraPosicion);
                Casilla casillaFinal = tablero.getCasilla(segundaPosicion);
                Casilla camino[] = tablero.getCamino(casillaInicial, casillaFinal);
                Ficha ficha = casillaInicial.getFicha();
                boolean validarMovimiento = ficha.mover(casillaInicial, casillaFinal, camino, jugadores[turno].getColor());
                verificacionComerAlPaso(casillaInicial,casillaFinal,tablero);
                ascensionPeon();
                mostrarTablero();
                int contrincante=(turno == 0 ? 1 : 0); 
                if (validarTablas(jugadores[turno].getColor(),jugadores[contrincante].getColor())) {
                    //se verifica que no haya quedado en tablas el juego
                    System.out.println("el juego quedo en tablas");
                    terminado = true;
                }
                Casilla fichasContrario[] = tablero.buscarJaque(jugadores[contrincante].getColor());
                Casilla jaqueContrario[] = verificarJaque(fichasContrario,tablero.buscarRey(jugadores[contrincante].getColor()),copia);
                i = 0;//variable temporal
                enJaque=false;
                while (i < jaqueContrario.length && jaqueContrario[i] != null) {
                    System.out.println("ficha con la que se hizo el jaque: "+jaqueContrario[i]);
                    enJaque=true;
                    i++;
                }
                if (enJaque) {
                    //se evalua si con el movimiento que acabo de realizar deje en jaque al otro jugador
                    System.out.println("el jugador "+jugadores[turno].getColor()+" le hizo jaque a "+jugadores[contrincante].getColor() );
                    Casilla fichasContrarioDefensa[] =copia.buscarJaque(jugadores[turno].getColor());
                    Casilla fichasContrarioAtaque[] = copia.buscarJaque(jugadores[contrincante].getColor());
                    Casilla jaqueContrarioCopia[] = verificarJaque(fichasContrarioAtaque,copia.buscarRey(jugadores[contrincante].getColor()),copia);
                    // Validar si hay Jaque Mate y terminar
                    if (validarJaqueMate(fichasContrarioDefensa,jaqueContrarioCopia,copia.buscarRey(jugadores[contrincante].getColor()),copia)) {
                        System.out.println("el jugador "+jugadores[turno].getColor()+" le hizo jaque a "+jugadores[contrincante].getColor()+" y este no se puedo mover" );
                        terminado = true;
                        terminadoPorJaque=true;
                    }else{
                        System.out.println("el jugador "+jugadores[contrincante].getColor()+" ha evitado el jaque");    
                    }

                }
                //cambiar turno, solo si el movimiento fue permitido
                if (validarMovimiento) {
                    cambioTurno();
                } else {
                    System.out.println("intente nuevamente");
                }
        }else{
           System.out.println("intente nuevamente"); 
        }
        //cronometro.parar();
  
    }
    
    public void realizarEnroqueCorto(Color color){
        //se evalua si se puede realizar un enroque corto
        //se crea una copia del tablero
        Tablero copia = tablero.duplicarTablero();
        //se busca al rey en la copia
        Casilla rey=copia.buscarRey(color);
        //se crea una ficha rey para la validacion
        Rey reyFicha = (Rey)rey.getFicha();
        boolean estadoRey= reyFicha.getEstado();
        //se busca a la torre cercana
        Casilla torreCercana=null;
        if(color==Color.BLANCO){
            torreCercana=copia.getCasilla("H1");
        }else{
            torreCercana=copia.getCasilla("H8");
        }
        
        //se evalua que el rey y la torre no hayan sido movidos antes
        if(estadoRey&&torreCercana.getFicha()!=null){
                Torre torreFicha=(Torre)torreCercana.getFicha();
                boolean estadoTorre=torreFicha.getEstado();
                if(estadoTorre&&torreCercana.getFicha() instanceof Torre){
                    //se evalua que el rey no quede en jaque en alguno de los movimientos del enroque
                        Casilla casillas[]=copia.getCamino(torreCercana,rey);
                        Casilla enemigos[]=copia.buscarJaque(color);
                        boolean enJaque=false;
                        int i=0,j=0,libre=0;//variables temporales
                        while(j<casillas.length&&casillas[j]!=null){
                            if(!casillas[j].isOcupada()){
                                Casilla jaque[]= verificarJaque(enemigos,casillas[j],copia);
                                while(i<jaque.length&&jaque[i]!=null){
                                    System.out.println("pieza que le queda haciendo jaque: "+jaque[i]);
                                    enJaque=true;
                                    i++;
                                }
                            }else{
                                System.out.println("pieza estorbando: "+casillas[j]);
                                enJaque=true;
                                break;
                            }
                        j++;
                        }
                        if(!enJaque){
                            //si no se genera jaque se procede a mover al rey y a la torre
                            if(color==Color.BLANCO){
                                Casilla movimiento= tablero.getCasilla("G1");
                                Casilla casillaInicial = tablero.buscarRey(color);
                                casillaInicial.getFicha().setCasilla(movimiento); 
                                movimiento.setFicha(casillaInicial.getFicha());
                                Ficha ficha=null;
                                casillaInicial.setFicha(ficha); 
                            }else{
                                Casilla movimiento= tablero.getCasilla("G8");
                                Casilla casillaInicial = tablero.buscarRey(color);
                                casillaInicial.getFicha().setCasilla(movimiento); 
                                movimiento.setFicha(casillaInicial.getFicha());
                                Ficha ficha=null;
                                casillaInicial.setFicha(ficha); 
                            }
                            if(color==Color.BLANCO){
                                Casilla movimiento= tablero.getCasilla("F1");
                                Casilla casillaInicial = tablero.getCasilla((char)(torreCercana.getColumna())+Integer.toString(torreCercana.getFila()));
                                casillaInicial.getFicha().setCasilla(movimiento); 
                                movimiento.setFicha(casillaInicial.getFicha());
                                Ficha ficha=null;
                                casillaInicial.setFicha(ficha); 
                            }else{
                                Casilla movimiento= tablero.getCasilla("F8");
                                Casilla casillaInicial = tablero.getCasilla((char)(torreCercana.getColumna())+Integer.toString(torreCercana.getFila()));
                                casillaInicial.getFicha().setCasilla(movimiento); 
                                movimiento.setFicha(casillaInicial.getFicha());
                                Ficha ficha=null;
                                casillaInicial.setFicha(ficha); 
                            }
                            cambioTurno();
                    }else{
                        System.out.println("no se puede hacer el enroque");
                    }
            }else{
                    System.out.println("las fichas no estan en sus posiciones iniciales");
                }
        }else{
            System.out.println("las fichas no estan en sus posiciones iniciales");
        }
        mostrarTablero();
    }
    
    public void realizarEnroqueLargo(Color color){
        //enroque largo, el mismo procedimiento que el enroque corto, solo que con la torre contraria
        Tablero copia = tablero.duplicarTablero();
        //se busca al rey en la copia
        Casilla rey=copia.buscarRey(color);
        //se crea una ficha rey para la validacion
        Rey reyFicha = (Rey)rey.getFicha();
        boolean estadoRey= reyFicha.getEstado();
        Casilla torreLejos=null;;
        if(color==Color.BLANCO){
            torreLejos=copia.getCasilla("A1");
        }else{
            torreLejos=copia.getCasilla("A8");
        }
        if(estadoRey&&torreLejos.getFicha()!=null){
            Torre torreFicha=(Torre)torreLejos.getFicha();
            boolean estadoTorre=torreFicha.getEstado();
            if(estadoTorre&&torreLejos.getFicha() instanceof Torre){
                    Casilla casillas[]=copia.getCamino(torreLejos,rey);
                    Casilla enemigos[]=copia.buscarJaque(color);
                    boolean enJaque=false;
                    int i=0,j=0,libre=0;
                    while(j<casillas.length&&casillas[j]!=null){
                        if(!casillas[j].isOcupada()){
                            Casilla jaque[]= verificarJaque(enemigos,casillas[j],copia);
                            while(i<jaque.length&&jaque[i]!=null){
                                System.out.println("pieza que le queda haciendo jaque: "+jaque[i]);
                                enJaque=true;
                                i++;
                            }
                        }else{
                            System.out.println("pieza estorbando: "+casillas[j]);
                            enJaque=true;
                            break;
                        }
                    j++;
                    }
                    if(!enJaque){
                        if(color==Color.BLANCO){
                            Casilla movimiento= tablero.getCasilla("C1");
                            Casilla casillaInicial = tablero.buscarRey(color);
                            casillaInicial.getFicha().setCasilla(movimiento); 
                            movimiento.setFicha(casillaInicial.getFicha());
                            Ficha ficha=null;
                            casillaInicial.setFicha(ficha);   
                        }else{
                            Casilla movimiento= tablero.getCasilla("C8");
                            Casilla casillaInicial = tablero.buscarRey(color);
                            casillaInicial.getFicha().setCasilla(movimiento); 
                            movimiento.setFicha(casillaInicial.getFicha());
                            Ficha ficha=null;
                            casillaInicial.setFicha(ficha); 
                        }
                        if(color==Color.BLANCO){
                            Casilla movimiento= tablero.getCasilla("D1");
                            Casilla casillaInicial = tablero.getCasilla((char)(torreLejos.getColumna())+Integer.toString(torreLejos.getFila()));
                            casillaInicial.getFicha().setCasilla(movimiento); 
                            movimiento.setFicha(casillaInicial.getFicha());
                            Ficha ficha=null;
                            casillaInicial.setFicha(ficha); 
                        }else{
                            Casilla movimiento= tablero.getCasilla("D8");
                            Casilla casillaInicial = tablero.getCasilla((char)(torreLejos.getColumna())+Integer.toString(torreLejos.getFila()));
                            casillaInicial.getFicha().setCasilla(movimiento); 
                            movimiento.setFicha(casillaInicial.getFicha());
                            Ficha ficha=null;
                            casillaInicial.setFicha(ficha); 
                        }
                        cambioTurno();
                    }else{
                        System.out.println("no se puede hacer el enroque");
                    }
                    mostrarTablero();
                    copia.mostrarTablero();
            }else{
                System.out.println("las fichas no estan en sus posiciones iniciales");
            }
        }else{
            System.out.println("las fichas no estan en sus posiciones iniciales");
        }
        mostrarTablero();
    }
    
    public void ascensionPeon(){
        Casilla casillas[]=tablero.buscarPeon();
        int i=0;
        while(i<casillas.length&&casillas[i]!=null){
            if((casillas[i].getFicha().getColor()==Color.BLANCO&&casillas[i].getFila()==8)||
               (casillas[i].getFicha().getColor()==Color.NEGRO&&casillas[i].getFila()==1)){
                String nuevaFicha=pnlTablero.obtenerCasilla();
                tablero.ascensionPeon(nuevaFicha,casillas[i],jugadores[turno].getColor(),casillas[i].getFicha());
                
            }
            i++;
        }
    }
    
    public boolean verificacionComerAlPaso(Casilla casillaInicial, Casilla casillaFinal,Tablero copia){
        boolean validarMovimiento=false;
        if(casillaInicial.getFicha() instanceof Peon&&!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.BLANCO)&&
           ((casillaInicial.getFila()+1==casillaFinal.getFila()&&casillaInicial.getColumna()-1==casillaFinal.getColumna())||
           (casillaInicial.getFila()+1==casillaFinal.getFila()&&casillaInicial.getColumna()+1==casillaFinal.getColumna()))&&
            copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()-1)).getFicha()instanceof Peon&&
            copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()-1)).getFicha().getColor()!=jugadores[turno].getColor()){
                Peon peonFicha=(Peon)copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()-1)).getFicha();
                int movimientos=peonFicha.getMovimientos();
                if(movimientos==2){
                    casillaInicial.getFicha().setCasilla(casillaFinal);
                    copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()-1)).getFicha().setCasilla(null);
                    copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()-1)).setFicha(null);
                    casillaFinal.setFicha( casillaInicial.getFicha());
                    casillaInicial.setFicha(null); 
                    validarMovimiento=true;
                }
        }else{
            if(casillaInicial.getFicha() instanceof Peon&&!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.NEGRO)&&
           ((casillaInicial.getFila()-1==casillaFinal.getFila()&&casillaInicial.getColumna()+1==casillaFinal.getColumna())||
           (casillaInicial.getFila()-1==casillaFinal.getFila()&&casillaInicial.getColumna()-1==casillaFinal.getColumna()))&&
            copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()+1)).getFicha()instanceof Peon&&
            copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()+1)).getFicha().getColor()!=jugadores[turno].getColor()){
                Peon peonFicha=(Peon)copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()+1)).getFicha();
                int movimientos=peonFicha.getMovimientos();
                if(movimientos==2){
                    casillaInicial.getFicha().setCasilla(casillaFinal);
                    copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()+1)).getFicha().setCasilla(null);
                    copia.getCasilla((char)(casillaFinal.getColumna())+Integer.toString(casillaFinal.getFila()+1)).setFicha(null);
                    casillaFinal.setFicha( casillaInicial.getFicha());
                    casillaInicial.setFicha(null); 
                    validarMovimiento=true;
                }
            }
        }
        return validarMovimiento;
    }

}
