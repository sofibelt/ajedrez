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
public abstract class Ficha {

    private Casilla casilla;
    private final Color color;
    private boolean estado;

    public Ficha(Color color) {
        this.color = color;
        this.estado=true;
    }

    public abstract void mover(Casilla casillaInicial,Casilla casillaFinal);

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
    
    public void cambiarEstado(boolean estado ){
        this.estado = estado;
    }
    
    public boolean getEstado(){
        return estado;
    }

}
