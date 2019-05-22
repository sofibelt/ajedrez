/**
 * Peon.java
 * 
 * Mayo 2019
 * 
 * realizado por Ana Sofia Beltran Rios 1004716847
 * @author utp: odau
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;
import java.lang.Math;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/** clase Peon, cuya funcion es simular el movimiento del Peon */
public class Peon extends Ficha {
/** lo que se pretende es simular todas las opciones que nos daria el juego real, 
 *  permitiendole al jugador hacer todo lo que se podria en un ajedrez normal*/
    
    //Variable
    private boolean inicio;
    String ascension;
    int movimientos;
    
    //Constructor
    public Peon(Color color) {
        super(color);
        inicio=true;
        movimientos=0;
    }
    
    //Metodos en general heredados
    @Override
    public boolean mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
        //se realiza el mismo procedimiento que en alfil, solo que con otra validacion de casillas
        //dependiendo de si se mueve una o dos casillas (estado inicial falso o verdadero)
        //la unica condicion extra es que si dependiendo de su color y su estado incial, hay casillas inalcanzables
        boolean validarMovimiento=false;
            if(((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&
                    (casillaInicial.getFila()+1==casillaFinal.getFila()))&&(!casillaFinal.isOcupada()&&
                    (casillaInicial.getFicha().getColor()==Color.BLANCO)) ||
               ((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&
                    (casillaInicial.getFila()-1==casillaFinal.getFila()))&&(!casillaFinal.isOcupada()&&
                    (casillaInicial.getFicha().getColor()==Color.NEGRO)) ||
               ((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&
                    (casillaInicial.getFila()+2==casillaFinal.getFila()))&&
                    (!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.BLANCO)) ||
               ((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&
                    (casillaInicial.getFila()-2==casillaFinal.getFila()))&&(!casillaFinal.isOcupada()&&
                    (casillaInicial.getFicha().getColor()==Color.NEGRO))    
                    ){
                    if(casillaInicial.getFila()+1==casillaFinal.getFila()||casillaInicial.getFila()-1==casillaFinal.getFila()){
                        movimientos=1;
                    }else{
                        movimientos=2;
                    }
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
                }else if(((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()+1==casillaFinal.getFila()))&&
                         (!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.BLANCO)) ||
                         ((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()-1==casillaFinal.getFila()))&&
                         (!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.NEGRO))    
                        ){  
                            if(casillaInicial.getFicha().getColor().equals(color)){
                                    setCasilla(casillaFinal);
                                    casillaFinal.setFicha(this);  
                            }   
                        }else if(casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor().equals(color))
                                &&(casillaFinal.getFicha().getColor()!=color)
                                ){
                            
                                  if(casillaInicial.getFicha().getColor()==Color.BLANCO){
                                    if((casillaInicial.getFila()+1==casillaFinal.getFila()&&
                                        casillaInicial.getColumna()-1==casillaFinal.getColumna())||
                                       (casillaInicial.getFila()+1==casillaFinal.getFila()&&
                                        casillaInicial.getColumna()+1==casillaFinal.getColumna())){
                                        inicio=false; 
                                           comer(casillaInicial,casillaFinal); 
                                       }  
                                 }else{
                                     if((casillaInicial.getFila()-1==casillaFinal.getFila()&&
                                        casillaInicial.getColumna()+1==casillaFinal.getColumna())||
                                       (casillaInicial.getFila()-1==casillaFinal.getFila()&&
                                        casillaInicial.getColumna()-1==casillaFinal.getColumna())){
                                         inicio=false; 
                                          comer(casillaInicial,casillaFinal); 
                                       }
                                 }
                                  
                                }else{
                                    System.out.println("no se pudo mover");
                                    System.out.println("casilla de donde intento moverse: "+casillaInicial.getColumna()+casillaInicial.getFila());
                                    System.out.println("casilla a donde intento moverse: "+casillaFinal.getColumna()+casillaFinal.getFila());
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
        // 50x50 dibujar la ficha
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 50, y + 50,
                java.awt.Color.WHITE));
        g.fill(new Ellipse2D.Float(x + 17, y + 15, 16, 16));
        g.fill(new Rectangle2D.Float(x + 15, y + 30, 20, 15));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 17, y + 15, 16, 16));
        g.draw(new Rectangle2D.Float(x + 15, y + 30, 20, 15));
    }

    @Override
    public Ficha duplicar() {
        Peon nuevoPeon = new Peon(color);
        nuevoPeon.inicio=inicio;
        nuevoPeon.movimientos=movimientos;
        return nuevoPeon;
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicial, Casilla casillaFinal, Casilla[] camino, Color color) {
        boolean validarMovimiento=false;
            if(((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()+1==casillaFinal.getFila()))&&
                    (!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.BLANCO)) ||
               ((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()-1==casillaFinal.getFila()))&&
                    (!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.NEGRO)) ||
               ((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()+2==casillaFinal.getFila()))&&
                    (!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.BLANCO)) ||
               ((inicio==true)&&(casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()-2==casillaFinal.getFila()))&&
                    (!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.NEGRO))    
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
                                validarMovimiento=true;
                                inicio=false;  
                           }else{
                               
                            }  
                    }
                }else if(((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()+1==casillaFinal.getFila()))&&
                         (!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.BLANCO)) ||
                         ((casillaFinal.getColumna()==casillaInicial.getColumna())&&(casillaInicial.getFila()-1==casillaFinal.getFila()))&&
                         (!casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor()==Color.NEGRO))    
                        ){  
                            if(casillaInicial.getFicha().getColor().equals(color)){
                                    validarMovimiento=true;  
                            }   
                        }else if(casillaFinal.isOcupada()&&(casillaInicial.getFicha().getColor().equals(color))
                                &&(casillaFinal.getFicha().getColor()!=color)
                                ){
                            
                                  if(casillaInicial.getFicha().getColor()==Color.BLANCO){
                                    if((casillaInicial.getFila()+1==casillaFinal.getFila()&&
                                        casillaInicial.getColumna()-1==casillaFinal.getColumna())||
                                       (casillaInicial.getFila()+1==casillaFinal.getFila()&&
                                        casillaInicial.getColumna()+1==casillaFinal.getColumna())){
                                           validarMovimiento=true;
                                       }  
                                 }else{
                                     if((casillaInicial.getFila()-1==casillaFinal.getFila()&&
                                        casillaInicial.getColumna()+1==casillaFinal.getColumna())||
                                       (casillaInicial.getFila()-1==casillaFinal.getFila()&&
                                        casillaInicial.getColumna()-1==casillaFinal.getColumna())){
                                          validarMovimiento=true; 
                                       }
                                 }
                                  
                                }
            
     return validarMovimiento; 
    }
       
    public int getMovimientos(){
        return movimientos;
    }
}
