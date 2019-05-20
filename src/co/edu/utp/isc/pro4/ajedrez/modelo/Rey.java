/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;
import java.lang.Math;

import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author utp
 */
public class Rey extends Ficha {


    public Rey(Color color) {
        super(color);
        
    }

    @Override
    public boolean mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
        boolean validarMovimiento=false;
     if(((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaFinal.getFila()==casillaInicial.getFila()+1))||
        ((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaFinal.getFila()==casillaInicial.getFila()-1))||
        ((casillaFinal.getFila()==casillaInicial.getFila())&&(casillaFinal.getColumna()==(char)casillaInicial.getColumna()+1))||
        ((casillaFinal.getFila()==casillaInicial.getFila())&&(casillaFinal.getColumna()==(char)casillaInicial.getColumna()-1))||
        ((casillaFinal.getFila()!=casillaInicial.getFila()&&casillaFinal.getColumna()!=casillaInicial.getColumna())
             &&Math.abs(casillaFinal.getColumna()-casillaInicial.getColumna())+Math.abs(casillaFinal.getFila()-casillaInicial.getFila())==2))
     {
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
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 6);
        polyline.moveTo(x + 15, y + 15);
        polyline.lineTo(x + 15, y + 15);
        polyline.lineTo(x + 35, y + 15);
        polyline.lineTo(x + 30, y + 25);
        polyline.lineTo(x + 20, y + 25);
        polyline.lineTo(x + 15, y + 15);
    
        // 50x50 dibujar la ficha
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 50, y + 50,
                java.awt.Color.WHITE));
       
        g.fill(new Rectangle2D.Float(x + 20, y + 25, 10, 18));
        g.fill(new Rectangle2D.Float(x + 17, y + 43, 15, 3));     
        g.fill(new Rectangle2D.Float(x + 15, y + 10, 19, 5)); 
        g.fill(new Rectangle2D.Float(x + 23, y + 0, 1, 10));
        g.fill(new Rectangle2D.Float(x + 19, y + 4, 10, 1));
        g.draw(new Rectangle2D.Float(x + 23, y + 0, 1, 10));
        g.draw(new Rectangle2D.Float(x + 19, y + 4, 10, 1));
        g.fill(polyline);
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Rectangle2D.Float(x + 20, y + 25, 10, 18));
        g.draw(new Rectangle2D.Float(x + 17, y + 43, 15, 3));
        g.draw(new Rectangle2D.Float(x + 15, y + 10, 19, 5));
        g.draw(polyline);
        
        
        
        
    }

    @Override
    public Ficha duplicar() {
       Rey nuevoRey = new Rey(color);
        return nuevoRey;
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicial, Casilla casillaFinal, Casilla[] camino, Color color) {
        boolean validarMovimiento=false;
     if(((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaFinal.getFila()==casillaInicial.getFila()+1))||
        ((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaFinal.getFila()==casillaInicial.getFila()-1))||
        ((casillaFinal.getFila()==casillaInicial.getFila())&&(casillaFinal.getColumna()==(char)casillaInicial.getColumna()+1))||
        ((casillaFinal.getFila()==casillaInicial.getFila())&&(casillaFinal.getColumna()==(char)casillaInicial.getColumna()-1))||
        ((casillaFinal.getFila()!=casillaInicial.getFila()&&casillaFinal.getColumna()!=casillaInicial.getColumna())
             &&Math.abs(casillaFinal.getColumna()-casillaInicial.getColumna())+Math.abs(casillaFinal.getFila()-casillaInicial.getFila())==2))
     {
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
