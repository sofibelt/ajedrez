/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;
import java.lang.Math;
/**
 *
 * @author utp
 */
public class Caballo extends Ficha {

    public Caballo(Color color) {
        super(color);
    }

    @Override
    public boolean mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
        boolean validarMovimiento=false;
        if(Math.abs(casillaFinal.getColumna()-casillaInicial.getColumna())
              + Math.abs(casillaFinal.getFila()-casillaInicial.getFila())==3){
            if(casillaInicial.getFicha().getColor().equals(color)){
                if(casillaFinal.isOcupada()){
                    if(casillaFinal.getFicha().getColor()!=color){
                        comer(casillaInicial,casillaFinal);  
                    }
                }else{
                    setCasilla(casillaFinal);
                    casillaFinal.setFicha(this);
                }
            }
        }else{
                System.out.println("no se pudo mover");
                System.out.println("casilla donde intento moverse: "+casillaInicial.getColumna()+casillaInicial.getFila());
                System.out.println("casilla donde intento moverse: "+casillaFinal.getColumna()+casillaFinal.getFila());
            }
        if(casillaFinal.getFicha()==this){
                Ficha ficha=null;
                casillaInicial.setFicha(ficha);  
                validarMovimiento=true;
            }
        return validarMovimiento;  
    }

    @Override
    public void comer(Casilla casillaInicial,Casilla casillaFinal) {
               Casilla nuevaCasilla = null;
               setCasilla(casillaFinal);
               Ficha fichaAnterior=casillaFinal.getFicha();
               fichaAnterior.setCasilla(nuevaCasilla);
               casillaFinal.setFicha(this);  
    }
    
}
