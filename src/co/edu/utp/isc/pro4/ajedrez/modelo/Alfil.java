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
public class Alfil extends Ficha {

    public Alfil(Color color) {
        super(color);
    }

    @Override
    public void mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
        if(Math.abs(casillaFinal.getColumna()-casillaInicial.getColumna())==Math.abs(casillaFinal.getFila()-casillaInicial.getFila())
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
            }
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
