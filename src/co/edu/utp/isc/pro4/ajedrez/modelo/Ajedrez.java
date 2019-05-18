/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.ui.PnlTablero;

/**
 *
 * @author utp: odau
 */
public class Ajedrez {

    private PnlTablero pnlTablero;

    private Jugador[] jugadores;
    private Tablero tablero;
    private Cronometro cronometro;

    private int turno;
    private boolean terminado;

    public Ajedrez() {
        jugadores = new Jugador[2];
        tablero = new Tablero();
        cronometro = new Cronometro();
        turno = 0;
        terminado = false;
    }

    public Ajedrez(Jugador jugador1, Jugador jugador2) {
        this();
        this.jugadores[0] = jugador1;
        this.jugadores[1] = jugador2;
    }

    public void setPnlTablero(PnlTablero pnlTablero) {
        this.pnlTablero = pnlTablero;
        pnlTablero.setTablero(tablero);
    }

    public void jugar() {
        jugadores[0].setAjedrez(this);
        jugadores[1].setAjedrez(this);

        ubicarFichasTablero();
        cronometro.iniciar();
        mostrarTablero();
        // LO QUE HACE JUGAR ES INICIALIZA TABLERO
        //EL RESTO DE LAS JUGADAS SON DESDE MOVER FICHA

    }

    public void cambioTurno() {
        turno = (turno == 0 ? 1 : 0);
        cronometro.cambio();
    }

    private boolean validarJaqueMate( Casilla piezasDefensa[], Casilla piezasJaque[], Casilla rey, Tablero copia) {
        boolean validarJaqueMate = true;
        int i = 0,j=0;
        while (i < piezasJaque.length && piezasJaque[i] != null) {
            i++;
        }
        if (i == 1) { 
            while(j<piezasDefensa.length&&piezasDefensa[j]!=null){       
                Casilla fichas[] = copia.getCamino(piezasDefensa[j],piezasJaque[0]);              
                int contrincante=(turno == 0 ? 1 : 0);
                Ficha primeraPosicionCopia=piezasDefensa[j].getFicha();
                Ficha segundaPosicionCopia=piezasJaque[0].getFicha();   
                boolean validacionMovimiento= piezasDefensa[j].getFicha().validarMovimiento(piezasDefensa[j],piezasJaque[0],fichas,jugadores[contrincante].getColor());
                if(validacionMovimiento&&!(piezasDefensa[j].getFicha() instanceof Rey)){
                            piezasDefensa[j].getFicha().mover(piezasDefensa[j],piezasJaque[0],fichas,piezasDefensa[j].getFicha().getColor());
                            Casilla fichasAtacantes[] = copia.buscarJaque(jugadores[contrincante].getColor()); 
                            Casilla jaque[]=verificarJaque(fichasAtacantes,rey,copia);
                            int a = 0;
                            boolean enJaque=false;
                            while (a < jaque.length && jaque[a] != null) {
                                System.out.println("pieza que le queda haciendo jaque: "+jaque[a]);
                                enJaque=true;
                                a++;
                            }
                            if (!enJaque) {
                                System.out.println("como se evito el jaque: ");
                                copia.mostrarTablero();
                                piezasDefensa[j].setFicha(primeraPosicionCopia);
                                piezasJaque[0].setFicha(segundaPosicionCopia);
                                System.out.println("regresa el tablero a la normalidad: ");
                                copia.mostrarTablero();
                                return false;
                            }else{
                                piezasDefensa[j].setFicha(primeraPosicionCopia);
                                piezasJaque[0].setFicha(segundaPosicionCopia);
                                System.out.println("no se pudo intentar mover, porque entro en jaque");
                            }

                        }else{
                            Casilla camino[]= copia.getCamino(piezasJaque[0],rey);
                            int a=0;
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
         int b=0;
         while(b<8){
          int contrincante=(turno == 0 ? 1 : 0);
          Casilla casillas[]=copia.encontrarCaminoRey(rey);
          if(casillas[b]!=null){
                Casilla evitarJaque[]=copia.getCamino(rey,casillas[b]);
                boolean validacionMovimiento= rey.getFicha().validarMovimiento(rey,casillas[b],evitarJaque,rey.getFicha().getColor());
                Ficha primeraPosicionCopia=rey.getFicha();
                Ficha segundaPosicionCopia=casillas[b].getFicha(); 
                if(validacionMovimiento){
                  rey.getFicha().mover(rey,casillas[b],evitarJaque,jugadores[contrincante].getColor());   
                  Casilla fichasAtacantes[] = copia.buscarJaque(jugadores[contrincante].getColor()); 
                  Casilla jaque[]=verificarJaque(fichasAtacantes,casillas[b],copia);
                  int a = 0;
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
         int b=0;
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
                  int a = 0;
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

    private boolean validarTablas() {
        //TODO: Validar si los jugadores se han quedado sin posibilidad de ganar
        return false;
    }

    public void rendirse() {
        terminado = true;
    }
    
    public boolean estadoDelJuego(){
        if(terminado){
            return false;
        }else{
            return true;
        }
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

    public Casilla[] verificarJaque(Casilla jaque[], Casilla casillaRey,Tablero copia) {
        Casilla[] peligroDeJaque = new Casilla[16];
        int i = 0, j = 0;
        while ((i < jaque.length) && (jaque[i] != null)) {
           Casilla camino[] = copia.getCamino(jaque[i],casillaRey);
           if(jaque[i].getFicha().validarMovimiento(jaque[i], casillaRey,camino,jaque[i].getFicha().getColor())){
               peligroDeJaque[j]=jaque[i];
               j++;
           }
            i++;
        }

        return peligroDeJaque;
    }
    

    public void moverFicha(String primeraPosicion, String segundaPosicion) {
        Tablero copia = tablero.duplicarTablero();
        Ficha fichaCopia = copia.getCasilla(primeraPosicion).getFicha();
        Casilla caminoCopia[] = copia.getCamino(copia.getCasilla(primeraPosicion), copia.getCasilla(segundaPosicion));
        Ficha primeraPosicionCopia=copia.getCasilla(primeraPosicion).getFicha();
        Ficha segundaPosicionCopia=copia.getCasilla(segundaPosicion).getFicha();
        fichaCopia.mover(copia.getCasilla(primeraPosicion), copia.getCasilla(segundaPosicion),
                caminoCopia, jugadores[turno].getColor());
        copia.mostrarTablero();
        Casilla fichas[] = copia.buscarJaque(jugadores[turno].getColor());
        Casilla jaque[] = verificarJaque(fichas, copia.buscarRey(jugadores[turno].getColor()),copia);
        int i = 0;
        boolean enJaque=false;
        while (i < jaque.length && jaque[i] != null) {
            System.out.println("ficha que hace jaque: "+jaque[i]);
            enJaque=true;
            i++;
        }
        if (enJaque) {  
            copia.getCasilla(primeraPosicion).setFicha(primeraPosicionCopia);
            copia.getCasilla(segundaPosicion).setFicha(segundaPosicionCopia);
            copia.mostrarTablero();
            System.out.println("no se pudo mover, porque entro en jaque");
            return;
        }

        Casilla casillaInicial = tablero.getCasilla(primeraPosicion);
        Casilla casillaFinal = tablero.getCasilla(segundaPosicion);
        Casilla camino[] = tablero.getCamino(casillaInicial, casillaFinal);
        Ficha ficha = casillaInicial.getFicha();
        boolean validarMovimiento = ficha.mover(casillaInicial, casillaFinal, camino, jugadores[turno].getColor());
        mostrarTablero();
        
        
        int contrincante=(turno == 0 ? 1 : 0);
        Casilla fichasContrario[] = tablero.buscarJaque(jugadores[contrincante].getColor());
        Casilla jaqueContrario[] = verificarJaque(fichasContrario,tablero.buscarRey(jugadores[contrincante].getColor()),copia);
        i = 0;
        enJaque=false;
        while (i < jaqueContrario.length && jaqueContrario[i] != null) {
            System.out.println("ficha con la que se hizo el jaque: "+jaqueContrario[i]);
            enJaque=true;
            i++;
        }
        if (enJaque) {
            System.out.println("el jugador "+jugadores[turno].getColor()+" le hizo jaque a "+jugadores[contrincante].getColor() );
            Casilla fichasContrarioDefensa[] =copia.buscarJaque(jugadores[turno].getColor());
            Casilla fichasContrarioAtaque[] = copia.buscarJaque(jugadores[contrincante].getColor());
            Casilla jaqueContrarioCopia[] = verificarJaque(fichasContrarioAtaque,copia.buscarRey(jugadores[contrincante].getColor()),copia);
            // Validar si hay Jaque Mate y terminar
            if (validarJaqueMate(fichasContrarioDefensa,jaqueContrarioCopia,copia.buscarRey(jugadores[contrincante].getColor()),copia)) {
                System.out.println("el jugador "+jugadores[turno].getColor()+" le hizo jaque a "+jugadores[contrincante].getColor()+" y este no se puedo mover" );
                terminado = true;
            }else{
                System.out.println("el jugador "+jugadores[contrincante].getColor()+" ha evitado el jaque");    
            }

        }
        
        if (validarTablas()) {
            terminado = true;
        }

        // Sino, cambiar turno, solo si se pudo hacer una jugada
        if (validarMovimiento) {
            cambioTurno();
        } else {
            System.out.println("intente nuevamente");
        }
  
        //cronometro.parar();
  
    }

}
