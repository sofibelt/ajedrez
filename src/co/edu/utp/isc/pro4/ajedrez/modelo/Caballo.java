/**
 * Caballo.java
 * 
 * Mayo 2019
 * 
 * realizado por Ana Sofia Beltran Rios 1004716847
 * @author utp: odau
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;
import java.awt.GradientPaint;
import java.lang.Math;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/** clase Caballo, cuya funcion es simular el movimiento del Caballo */
public class Caballo extends Ficha {
/** lo que se pretende es simular todas las opciones que nos daria el juego real, 
 *  permitiendole al jugador hacer todo lo que se podria en un ajedrez normal*/

    //Constructor
    public Caballo(Color color) {
        super(color);
    }
    
    //Metodos en mayor parte Heredados
    @Override
    public boolean mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
        boolean validarMovimiento=false;
        //se evalua la casilla a donde se quiere mover (si es valido el movimiento)
        if(Math.abs(casillaFinal.getColumna()-casillaInicial.getColumna())
              + Math.abs(casillaFinal.getFila()-casillaInicial.getFila())==3&&
                (casillaFinal.getColumna()-casillaInicial.getColumna())!=0&&
                (casillaFinal.getFila()-casillaInicial.getFila())!=0
                ){
            //se evalua que la ficha que mueve sea del color del jugador
            if(casillaInicial.getFicha().getColor().equals(color)){
                //si la casilla final esta ocupada, se evalua que se pueda comer la ficha  
                if(casillaFinal.isOcupada()){
                    //se evalua que la ficha sea de diferente color al jugador
                    if(casillaFinal.getFicha().getColor()!=color){
                        comer(casillaInicial,casillaFinal);  
                    }
                }else{
                    //si la casilla esta libre se procede a mover la ficha
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
        //se hace el mismo proceso de mover ficha para validar si esta puede ser movida a un casilla, sin ejecutar el movimiento verdaderamente
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
