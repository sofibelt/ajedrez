/**
 * Alfil.java
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

/** clase Alfil, cuya funcion es simular el movimiento del Alfil */
public class Alfil extends Ficha {
/** lo que se pretende es simular todas las opciones que nos daria el juego real, 
 *  permitiendole al jugador hacer todo lo que se podria en un ajedrez normal*/
    
    //Constructor
    public Alfil(Color color) {
        super(color);
    }
    
    //Metodos, en general heredados
    @Override
    public boolean mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
        boolean validarMovimiento=false;
        //se evalua la casilla a donde se quiere mover (si es valido el movimiento)
        if(Math.abs(casillaFinal.getColumna()-casillaInicial.getColumna())==Math.abs(casillaFinal.getFila()-casillaInicial.getFila())
                ){
            //se evalua que la ficha que mueve sea del color del jugador
                if(casillaInicial.getFicha().getColor().equals(color)){
                     int i=0,libre=0;//variables temporales
                     //se evalua que el camino entre ambas fichas este libre
                        while((i<camino.length)&&(camino[i]!=null)){   
                            if(!camino[i].isOcupada()){                            
                                libre++;
                            }
                              i++; 
                        }
                   //si la casilla final esta ocupada, se evalua que se pueda comer la ficha     
                   if(casillaFinal.isOcupada()){
                       //se evalua que la ficha sea de diferente color al jugador
                        if(casillaFinal.getFicha().getColor()!=color){
                            if(libre==i){
                                comer(casillaInicial,casillaFinal);
                            }
                        }
                    }else{
                       //si la casilla esta libre se procede a mover la ficha
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
                //se procede a elimar la ficha de la casilla anterior
                Ficha ficha=null;
                casillaInicial.setFicha(ficha);  
                validarMovimiento=true;
            }
        return validarMovimiento; 
    }

    @Override
    public void comer(Casilla casillaInicial,Casilla casillaFinal) {
               //se procede a comer la ficha
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
        //g.setPaint(java.awt.Color.BLACK);
        g.fill(new Rectangle2D.Float(x + 17, y + 25, 10, 20));
        g.fill(new  Ellipse2D.Float(x + 18, y + 4, 8, 15));
        g.fill(new Rectangle2D.Float(x + 15, y + 20, 14, 5));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Rectangle2D.Float(x + 15, y + 20, 14, 5));
        g.draw(new Rectangle2D.Float(x + 17, y + 25, 10, 20));
        g.draw(new  Ellipse2D.Float(x + 18, y + 4, 8, 15));
        
    }

    @Override
    public Ficha duplicar() {
        //se duplica la ficha
        Ficha nuevoAlfil = new Alfil(color);
        return nuevoAlfil;
        
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicial, Casilla casillaFinal, Casilla[] camino, Color color) {
       //se hace el mismo proceso de mover ficha para validar si esta puede ser movida a un casilla, sin ejecutar el movimiento verdaderamente
        boolean validarMovimiento=false;
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
                                validarMovimiento=true;
                            }
                        }
                    }else{
                       if(libre==i){
                           validarMovimiento=true;
                       }
                   }        
                }
            }
        
        return validarMovimiento;  
    }
    
    
    
}
