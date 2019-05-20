/**
 * Cronometro.java
 * 
 * Mayo 2019
 * 
 * realizado por Ana Sofia Beltran Rios 1004716847
 * @author utp: odau
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.time.LocalTime;

/** clase Cronometro, cuya funcion es simular el cronometro usado en el ajedrez */
public class Cronometro {
/** lo que se pretende es simular todas las opciones que nos daria el juego real, 
 *  permitiendole al jugador hacer todo lo que se podria en un ajedrez normal*/
    
    //Variables
    private LocalTime[] tiempo;
    private int turno;
    
    //Constructores
    public Cronometro() {
        tiempo = new LocalTime[2];
        turno = 0;
    }
    
    //Metodos
    public void iniciar(){
        //TODO: Iniciar el cronometro para el jugador actual
    }
    
    public void cambio(){
        //TODO: Parar el cronometro para el jugador actual
        turno = (turno == 0 ? 1 : 0);
        //TODO: Iniciar el cronometro para el jugador actual
    }
    
    public void parar() {
        //TODO: Parar el cronometro 
        
    }
    
}
