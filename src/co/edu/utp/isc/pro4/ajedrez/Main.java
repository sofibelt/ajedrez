/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez;

import co.edu.utp.isc.pro4.ajedrez.modelo.Ajedrez;
import co.edu.utp.isc.pro4.ajedrez.modelo.Color;
import co.edu.utp.isc.pro4.ajedrez.modelo.Jugador;

/**
 *
 * @author utp
 */
public class Main {

    public static void main(String[] args) {
        Ajedrez juego = new Ajedrez(
                new Jugador("Cesar",Color.BLANCO),
                new Jugador("Ana",Color.NEGRO));
        juego.jugar();
        
    }
}
