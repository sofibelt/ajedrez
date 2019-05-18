/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;
import java.awt.GradientPaint;
import java.lang.Math;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
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
              + Math.abs(casillaFinal.getFila()-casillaInicial.getFila())==3&&
                (casillaFinal.getColumna()-casillaInicial.getColumna())!=0&&
                (casillaFinal.getFila()-casillaInicial.getFila())!=0
                ){
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
    
    @Override
    public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 11);
        polyline.moveTo(x + 30, y + 33);
        polyline.lineTo(x + 15, y + 35);
        polyline.lineTo(x + 10, y + 30);
        polyline.lineTo(x + 20, y + 20);
        polyline.lineTo(x + 25, y + 10);
        polyline.lineTo(x + 35, y + 10);
        polyline.lineTo(x + 40, y + 20);
        polyline.lineTo(x + 45, y + 30);
        polyline.lineTo(x + 45, y + 45);
        polyline.lineTo(x + 15, y + 45);
        polyline.lineTo(x + 30, y + 33);
        
        // 50x50 dibujar la ficha
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 50, y + 50,
                java.awt.Color.WHITE));
       g.fill(polyline);
       g.setColor(java.awt.Color.BLACK);
       g.draw(polyline);
       
       
    }

    @Override
    public Ficha duplicar() {
        Ficha nuevoCaballo = new Caballo(color);
        return nuevoCaballo;
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicial, Casilla casillaFinal, Casilla[] camino, Color color) {
        boolean validarMovimiento=false;
        if(Math.abs(casillaFinal.getColumna()-casillaInicial.getColumna())
              + Math.abs(casillaFinal.getFila()-casillaInicial.getFila())==3&&
                (casillaFinal.getColumna()-casillaInicial.getColumna())!=0&&
                (casillaFinal.getFila()-casillaInicial.getFila())!=0
                ){
            if(casillaInicial.getFicha().getColor().equals(color)){
                if(casillaFinal.isOcupada()){
                    if(casillaFinal.getFicha().getColor()!=color){
                       validarMovimiento=true; 
                    }
                }else{
                    validarMovimiento=true;
                }
            }
        }
        return validarMovimiento;  
    }
    
}
