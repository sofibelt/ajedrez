/**
 * Jugador.java
 * 
 * Mayo 2019
 * 
 * realizado por Ana Sofia Beltran Rios 1004716847
 * @author utp: odau
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import static co.edu.utp.isc.pro4.ajedrez.modelo.Color.BLANCO;
import java.util.Scanner;

/** clase Jugador, cuya funcion es simular los jugadores del ajedrez */
public class Jugador {
/** lo que se pretende es simular todas las opciones que nos daria el juego real, 
 *  permitiendole al jugador hacer todo lo que se podria en un ajedrez normal*/
    
    //Variables
    private Ajedrez ajedrez;
    private String nombre;
    private Color color;
    
    
    //Constructor
    public Jugador(String nombre,Color color) {
        this.nombre = nombre;
        this.color =color;

    }
    
    //Metodos
    public void jugar(String posicionInicial, String posicionFinal) {
        //se manda a ajedrez las posiciones finales he iniciales de las fichas
        ajedrez.moverFicha(posicionInicial,posicionFinal); 
    }

    public void setAjedrez(Ajedrez ajedrez) {
        this.ajedrez = ajedrez;
    }

    public String getNombre() {
        return this.nombre;
    }

    private void rendirse() {
        // No me gusta pero los estudiantes lo pidieron
        ajedrez.rendirse();
    }
    
    public Color getColor(){
        return color;
    }

}
