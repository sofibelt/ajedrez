/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

/**
 *
 * @author utp
 */
public class Tablero {

    private final Casilla[][] casillas;

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

    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public Casilla getCasilla(String posicion) {
        int columna = posicion.charAt(0) - 'A';
        int fila = Integer.valueOf(posicion.substring(1)) - 1;
        return getCasilla(fila, columna);
    }
    
    public Casilla[] getCamino(Casilla primeraPosicion, Casilla segundaPosicion){
        Casilla[] posicion = new Casilla[6];
        int i;
        if(primeraPosicion.getFila()==segundaPosicion.getFila()){   
           i=1;
            if(primeraPosicion.getColumna()<segundaPosicion.getColumna()){
                while(primeraPosicion.getColumna()+i!=segundaPosicion.getColumna()){
                 posicion[i-1]=getCasilla((char)(primeraPosicion.getColumna()+i)+
                            Integer.toString(primeraPosicion.getFila()));   
                 i++;
                }
            }else{
                while(primeraPosicion.getColumna()-i!=segundaPosicion.getColumna()){
                 posicion[i-1]=getCasilla((char)(primeraPosicion.getColumna()-i)+
                            Integer.toString(primeraPosicion.getFila()));   
                 i++;
                }
            }  
        }else if(primeraPosicion.getColumna()==segundaPosicion.getColumna()){      
            i=1;
            if(primeraPosicion.getFila()<segundaPosicion.getFila()){
                while(primeraPosicion.getFila()+i!=segundaPosicion.getFila()){
                 posicion[i-1]=getCasilla((char)(primeraPosicion.getColumna())+
                            Integer.toString(primeraPosicion.getFila()+i));   
                 i++;
                }
            }else{
                while(primeraPosicion.getFila()-i!=segundaPosicion.getFila()){
                 posicion[i-1]=getCasilla((char)(primeraPosicion.getColumna())+
                            Integer.toString(primeraPosicion.getFila()-i));   
                 i++;
                }
            }
        }else{
            i=1;
            if((primeraPosicion.getFila()<segundaPosicion.getFila())&&
                    (primeraPosicion.getColumna()<segundaPosicion.getColumna())){
                while((primeraPosicion.getFila()+i!=segundaPosicion.getFila())&&
                        (primeraPosicion.getColumna()+i!=segundaPosicion.getColumna())){
                 posicion[i-1]=getCasilla((char)(primeraPosicion.getColumna()+i)+
                            Integer.toString(primeraPosicion.getFila()+i));   
                 i++;
                }
            }else if((primeraPosicion.getFila()>segundaPosicion.getFila())&&
                    (primeraPosicion.getColumna()<segundaPosicion.getColumna())){
                while((primeraPosicion.getFila()-i!=segundaPosicion.getFila())&&
                        (primeraPosicion.getColumna()+i!=segundaPosicion.getColumna())){
                 posicion[i-1]=getCasilla((char)(primeraPosicion.getColumna()+i)+
                            Integer.toString(primeraPosicion.getFila()-i));   
                 i++;
                }
            }else if((primeraPosicion.getFila()>segundaPosicion.getFila())&&
                    (primeraPosicion.getColumna()>segundaPosicion.getColumna())){
                while((primeraPosicion.getFila()-i!=segundaPosicion.getFila())&&
                        (primeraPosicion.getColumna()-i!=segundaPosicion.getColumna())){
                 posicion[i-1]=getCasilla((char)(primeraPosicion.getColumna()-i)+
                            Integer.toString(primeraPosicion.getFila()-i));   
                 i++;
                }
            }else if((primeraPosicion.getFila()<segundaPosicion.getFila())&&
                    (primeraPosicion.getColumna()>segundaPosicion.getColumna())){
                while((primeraPosicion.getFila()+i!=segundaPosicion.getFila())&&
                        (primeraPosicion.getColumna()-i!=segundaPosicion.getColumna())){
                 posicion[i-1]=getCasilla((char)(primeraPosicion.getColumna()-i)+
                            Integer.toString(primeraPosicion.getFila()+i));   
                 i++;
                }
            }
        }  
        return  posicion;
    }

}
