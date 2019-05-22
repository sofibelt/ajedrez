/**
 * Tablero.java
 * 
 * Mayo 2019
 * 
 * realizado por Ana Sofia Beltran Rios 1004716847
 * @author utp: odau
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

/** clase Tablero, cuya funcion es simular el tablero del ajedrez */
public class Tablero {
/** lo que se pretende es simular todas las opciones que nos daria el juego real, 
 *  permitiendole al jugador hacer todo lo que se podria en un ajedrez normal*/
    
    //Variable
    private final Casilla[][] casillas;

    //Constructor
    public Tablero() {
        casillas = new Casilla[8][];
        for (int i = 0; i < 8; i++) {
            casillas[i] = new Casilla[8];
            for (int j = 0; j < 8; j++) {
                casillas[i][j] = new Casilla(
                        (i + j) % 2 == 0 ? Color.NEGRO : Color.BLANCO,
                        i + 1,
                        (char) ('A' + j));
            }
        }
    }
    
    //Metodo
    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public Casilla getCasilla(String posicion) {
        int columna = posicion.charAt(0) - 'A';
        int fila = Integer.valueOf(posicion.substring(1)) - 1;
        return getCasilla(fila, columna);
    }

    public Casilla[] getCamino(Casilla primeraPosicion, Casilla segundaPosicion) {
        Casilla[] posicion = new Casilla[6];
        int i;
        if(primeraPosicion==segundaPosicion){
            return posicion;
        }
        if (primeraPosicion.getFila() == segundaPosicion.getFila()) {
            i = 1;
            if (primeraPosicion.getColumna() < segundaPosicion.getColumna()) {
                while (primeraPosicion.getColumna() + i != segundaPosicion.getColumna()) {
                    posicion[i - 1] = getCasilla((char) (primeraPosicion.getColumna() + i)
                            + Integer.toString(primeraPosicion.getFila()));
                    i++;
                }
            } else {
                while (primeraPosicion.getColumna() - i != segundaPosicion.getColumna()) {
                    posicion[i - 1] = getCasilla((char) (primeraPosicion.getColumna() - i)
                            + Integer.toString(primeraPosicion.getFila()));
                    i++;
                }
            }
        } else if (primeraPosicion.getColumna() == segundaPosicion.getColumna()) {
            i = 1;
            if (primeraPosicion.getFila() < segundaPosicion.getFila()) {
                while (primeraPosicion.getFila() + i != segundaPosicion.getFila()) {
                    posicion[i - 1] = getCasilla((char) (primeraPosicion.getColumna())
                            + Integer.toString(primeraPosicion.getFila() + i));
                    i++;
                }
            } else {
                while (primeraPosicion.getFila() - i != segundaPosicion.getFila()) {
                    posicion[i - 1] = getCasilla((char) (primeraPosicion.getColumna())
                            + Integer.toString(primeraPosicion.getFila() - i));
                    i++;
                }
            }
        } else {
            i = 1;
            if ((primeraPosicion.getFila() < segundaPosicion.getFila())
                    && (primeraPosicion.getColumna() < segundaPosicion.getColumna())) {
                while ((primeraPosicion.getFila() + i != segundaPosicion.getFila())
                        && (primeraPosicion.getColumna() + i != segundaPosicion.getColumna())) {
                    posicion[i - 1] = getCasilla((char) (primeraPosicion.getColumna() + i)
                            + Integer.toString(primeraPosicion.getFila() + i));
                    i++;
                }
            } else if ((primeraPosicion.getFila() > segundaPosicion.getFila())
                    && (primeraPosicion.getColumna() < segundaPosicion.getColumna())) {
                while ((primeraPosicion.getFila() - i != segundaPosicion.getFila())
                        && (primeraPosicion.getColumna() + i != segundaPosicion.getColumna())) {
                    posicion[i - 1] = getCasilla((char) (primeraPosicion.getColumna() + i)
                            + Integer.toString(primeraPosicion.getFila() - i));
                    i++;
                }
            } else if ((primeraPosicion.getFila() > segundaPosicion.getFila())
                    && (primeraPosicion.getColumna() > segundaPosicion.getColumna())) {
                while ((primeraPosicion.getFila() - i != segundaPosicion.getFila())
                        && (primeraPosicion.getColumna() - i != segundaPosicion.getColumna())) {
                    posicion[i - 1] = getCasilla((char) (primeraPosicion.getColumna() - i)
                            + Integer.toString(primeraPosicion.getFila() - i));
                    i++;
                }
            } else if ((primeraPosicion.getFila() < segundaPosicion.getFila())
                    && (primeraPosicion.getColumna() > segundaPosicion.getColumna())) {
                while ((primeraPosicion.getFila() + i != segundaPosicion.getFila())
                        && (primeraPosicion.getColumna() - i != segundaPosicion.getColumna())) {
                    posicion[i - 1] = getCasilla((char) (primeraPosicion.getColumna() - i)
                            + Integer.toString(primeraPosicion.getFila() + i));
                    i++;
                }
            }
        }
        return posicion;
    }

    public Casilla buscarRey(Color color) {
        Casilla casilla = null;
         for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getCasilla(i, j).getFicha() instanceof Rey
                            && getCasilla(i, j).getFicha().getColor() == color) {
                        casilla = getCasilla(i, j);
                    }
                }
            }  
        return casilla;
    }
    public Casilla[] buscarPeon() {
        Casilla casilla[]=new Casilla[16];
        int a=0;
         for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getCasilla(i, j).getFicha() instanceof Peon){
                        casilla[a] = getCasilla(i, j);
                        a++;
                    }
                }
            }  
        return casilla;
    }

    public Casilla[] buscarJaque(Color color) {
        Casilla[] fichas = new Casilla[16];
        int arreglo = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getCasilla(i, j).isOcupada()) {
                    if (getCasilla(i, j).getFicha().getColor() != color) {
                        fichas[arreglo] = getCasilla(i, j);
                        arreglo++;
                    }
                }
            }
        }

        return fichas;
    }

    public Tablero duplicarTablero() {
        Tablero temporal = new Tablero();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Ficha ficha = getCasilla(i,j).getFicha();
                if(ficha!=null){
                    ficha=ficha.duplicar();
                    ficha.setCasilla(temporal.getCasilla(i,j));
                }
                temporal.getCasilla(i,j).setFicha(ficha);
            }
            
        }
        return temporal; 
    }
    
    public void mostrarTablero(){
        System.out.println("  \tA \tB \tC \tD \tE \tF \tG \tH");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1));
            for (int j = 0; j < 8; j++) {
                System.out.print("\t" + getCasilla(i, j));
            }
            System.out.println();
        }
    }
    
    public Casilla[] encontrarCaminoRey(Casilla rey){
        Casilla[] casillas = new Casilla[8];
        if(rey.getFila()+1<=8){
          casillas[0]=getCasilla(rey.getColumna()+Integer.toString(rey.getFila()+1));
        }
        if(rey.getColumna()+1<='H'&&rey.getFila()+1<=8){
          casillas[1]=getCasilla((char)(rey.getColumna()+1)+Integer.toString(rey.getFila()+1));
        }
        if(rey.getColumna()+1<='H'){
          casillas[2]=getCasilla((char)(rey.getColumna()+1)+Integer.toString(rey.getFila()));
        }
        if(rey.getColumna()+1<='H'&&rey.getFila()-1>=1){
          casillas[3]=getCasilla((char)(rey.getColumna()+1)+Integer.toString(rey.getFila()-1));
        }
        if(rey.getFila()-1>=1){
          casillas[4]=getCasilla(rey.getColumna()+Integer.toString(rey.getFila()-1));
        }
        if(rey.getColumna()-1>='A'&&rey.getFila()-1>=1){
          casillas[5]=getCasilla((char)(rey.getColumna()-1)+Integer.toString(rey.getFila()-1));
        }
        if(rey.getColumna()-1>='A'){
          casillas[6]=getCasilla((char)(rey.getColumna()-1)+Integer.toString(rey.getFila()));
        }
        if(rey.getColumna()-1>='A'&&rey.getFila()+1<=8){
          casillas[7]=getCasilla((char)(rey.getColumna()-1)+Integer.toString(rey.getFila()+1));
        }
    return casillas;
    } 
    
    public void ascensionPeon(String ficha,Casilla casilla,Color color,Ficha fichaAntigua){
        if(ficha=="reina"){
            Ficha reina = new Reina(color);
            reina.setCasilla(casilla);
            casilla.setFicha(reina); 
            fichaAntigua.setCasilla(null);
        }else if(ficha=="alfil"){
            Ficha alfil = new Alfil(color);
            alfil.setCasilla(casilla);
            casilla.setFicha(alfil); 
            fichaAntigua.setCasilla(null);
        }else if(ficha=="torre"){
            Ficha torre = new Torre(color);
            torre.setCasilla(casilla);
            casilla.setFicha(torre); 
            fichaAntigua.setCasilla(null);
        }else if(ficha=="caballo"){
            Ficha caballo = new Caballo(color);
            caballo.setCasilla(casilla);
            casilla.setFicha(caballo); 
            fichaAntigua.setCasilla(null);
        }
        System.out.println("va a cambiar por: "+ficha);
    }

}
