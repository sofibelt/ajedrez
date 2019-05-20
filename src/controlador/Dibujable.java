/**
 * Dibujable.java
 * 
 * Mayo 2019
 * 
 * realizado por Ana Sofia Beltran Rios 1004716847
 * @author utp: odau
 */
package controlador;

import java.awt.Graphics2D;

/** clase Dibujable, se encarga de generar el metodo dibujar */
public abstract class Dibujable {
    
    public abstract void draw(Graphics2D g, float x, float y);
}
