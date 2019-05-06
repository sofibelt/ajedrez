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
public class Peon extends Ficha {
    private boolean inicio;
    
    public Peon(Color color) {
        super(color);
        inicio=true;
    }

    @Override
    public void mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
        
            if(((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()+1==casillaFinal.getFila()))&&(!casillaFinal.isOcupada()) ||
               ((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()-1==casillaFinal.getFila()))&&(!casillaFinal.isOcupada()) ||
               ((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()+2==casillaFinal.getFila()))&&(!casillaFinal.isOcupada()) ||
               ((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()-2==casillaFinal.getFila()))&&(!casillaFinal.isOcupada())    
                    ){
                    if(casillaInicial.getFicha().getColor().equals(color)){
                            int i=0,libre=0;
                           while((i<camino.length)&&(camino[i]!=null)){   
                               if(!camino[i].isOcupada()){                            
                                    libre++;
                                }
                                i++; 
                            }
                            if(libre==i){
                                setCasilla(casillaFinal); 
                                casillaFinal.setFicha(this);
                                inicio=false;  
                           }else{
                               System.out.println("hay una ficha en medio"); 
                            }  
                    }
                }else if(((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()+1==casillaFinal.getFila()))&&(!casillaFinal.isOcupada()) ||
                        ((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()-1==casillaFinal.getFila()))&&(!casillaFinal.isOcupada())    
                        ){  
                            if(casillaInicial.getFicha().getColor().equals(color)){
                                    setCasilla(casillaFinal);
                                    casillaFinal.setFicha(this);  
                            }   
                        }else if(casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor().equals(color))
                                &&(casillaFinal.getFicha().getColor()!=color)
                                ){
                                    if(Math.abs(casillaFinal.getColumna()-casillaInicial.getColumna())==1&&Math.abs(casillaFinal.getFila()-casillaInicial.getFila())==1){  
                                        comer(casillaInicial,casillaFinal); 
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
