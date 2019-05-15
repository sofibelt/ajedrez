/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;
import java.lang.Math;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
/**
 *
 * @author utp
 */
public class Reina extends Ficha {

    public Reina(Color color) {
        super(color);
    }

    @Override
    public boolean mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
       boolean validarMovimiento=false;
        if(
          (casillaFinal.getColumna()==casillaInicial.getColumna())||
          (casillaFinal.getFila()==casillaInicial.getFila())||
          Math.abs(casillaFinal.getColumna()-casillaInicial.getColumna())==Math.abs(casillaFinal.getFila()-casillaInicial.getFila())     
               ){
           if(casillaInicial.getFicha().getColor().equals(color)){
               int i=0,libre=0;
                        while((i<camino.length)&&(camino[i]!=null)){   
                            if(!camino[i].isOcupada()){                            
                                libre++;
                            }
                              i++; 
                        }
                if(casillaFinal.isOcupada()){
                    if(casillaFinal.getFicha().getColor()!=color){
                            if(libre==i){
                                comer(casillaInicial,casillaFinal);
                            }
                        }
                }else{
                    if(libre==i){
                           setCasilla(casillaFinal); 
                           casillaFinal.setFicha(this);
                       }
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
        g.fill(new Ellipse2D.Float(x + 20, y + 0, 10, 10));
        g.fill(polyline);
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 20, y + 0, 10, 10));
        g.draw(new Rectangle2D.Float(x + 20, y + 25, 10, 18));
        g.draw(new Rectangle2D.Float(x + 17, y + 43, 15, 3));
        g.draw(new Rectangle2D.Float(x + 15, y + 10, 19, 5));
        g.draw(polyline);
    }
    
}
