/**
 * Torre.java
 * 
 * Mayo 2019
 * 
 * realizado por Ana Sofia Beltran Rios 1004716847
 * @author utp: odau
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

/** clase Torre, cuya funcion es simular el movimiento de la Torre */
public class Torre extends Ficha {
/** lo que se pretende es simular todas las opciones que nos daria el juego real, 
 *  permitiendole al jugador hacer todo lo que se podria en un ajedrez normal*/
    
    //Constructor
    public Torre(Color color) {
        super(color);
    }
    
    //Metodos, en general heredados
    @Override
    public boolean mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
        //se realiza el mismo procedimiento que en alfil, solo que con otra validacion de casillas
        boolean validarMovimiento=false;
        if((casillaFinal.getColumna()==casillaInicial.getColumna())||
          (casillaFinal.getFila()==casillaInicial.getFila())){
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
    
    public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 5, y + 5);
        polyline.lineTo(x + 5, y + 15);
        polyline.lineTo(x + 10, y + 15);
        polyline.lineTo(x + 10, y + 45);
        polyline.lineTo(x + 40, y + 45);
        polyline.lineTo(x + 40, y + 15);
        polyline.lineTo(x + 45, y + 15);
        polyline.lineTo(x + 45, y + 5);
        polyline.lineTo(x + 37, y + 5);
        polyline.lineTo(x + 37, y + 10);
        polyline.lineTo(x + 29, y + 10);
        polyline.lineTo(x + 29, y + 5);
        polyline.lineTo(x + 21, y + 5);
        polyline.lineTo(x + 21, y + 10);
        polyline.lineTo(x + 13, y + 10);
        polyline.lineTo(x + 13, y + 5);
        polyline.lineTo(x + 5, y + 5);

        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 100, y + 50,
                java.awt.Color.WHITE));
        g.fill(polyline);
        g.setColor(java.awt.Color.BLACK);
        g.draw(polyline);
    }

    @Override
    public Ficha duplicar() {
       Torre nuevoTorre = new Torre(color);
        return nuevoTorre;
    }

    @Override
    public boolean validarMovimiento(Casilla casillaInicial, Casilla casillaFinal, Casilla[] camino, Color color) {
        boolean validarMovimiento=false;
        if((casillaFinal.getColumna()==casillaInicial.getColumna())||
          (casillaFinal.getFila()==casillaInicial.getFila())){
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
