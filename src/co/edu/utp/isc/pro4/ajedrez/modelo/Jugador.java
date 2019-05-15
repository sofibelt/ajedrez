/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import static co.edu.utp.isc.pro4.ajedrez.modelo.Color.BLANCO;
import java.util.Scanner;
/**
 *
 * @author utp
 */
public class Jugador {

    private Ajedrez ajedrez;
    private String nombre;
    private Color color;

    public Jugador(String nombre,Color color) {
        this.nombre = nombre;
        this.color =color;

    }

    public void jugar(String posicionInicial, String posicionFinal) {  
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
