/**
 * Ficha.java
 * 
 * Mayo 2019
 * 
 * realizado por Ana Sofia Beltran Rios 1004716847
 * @author utp: odau
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import controlador.Dibujable;

/** clase Ficha, cuya funcion es simular las Fichas usadas en el ajedrez*/
public abstract class Ficha extends Dibujable {
/** lo que se pretende es simular todas las opciones que nos daria el juego real, 
 *  permitiendole al jugador hacer todo lo que se podria en un ajedrez normal*/
    
    //Variables
    private Casilla casilla;
    final Color color;
    
    //Constructor
    public Ficha(Color color) {
        this.color = color;
    }
    
    //Metodos
    public abstract boolean mover(Casilla casillaInicial,Casilla casillaFinal, Casilla camino[],Color color);

    public abstract void comer(Casilla casillaInicial,Casilla casillaFinal);

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        String tipo = "";
        if (this instanceof Peon) {
            tipo = "P";
        } else if (this instanceof Torre) {
            tipo = "T";
        } else if (this instanceof Caballo) {
            tipo = "C";
        } else if (this instanceof Alfil) {
            tipo = "A";
        } else if (this instanceof Reina) {
            tipo = "Q";
        } else if (this instanceof Rey) {
            tipo = "R";
        }
        return tipo + (getColor() == Color.BLANCO ? "B" : "N");
    }

    public abstract Ficha  duplicar();
    public abstract boolean validarMovimiento(Casilla casillaInicial,Casilla casillaFinal, Casilla camino[],Color color);
    
    

}
