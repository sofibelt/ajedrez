/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

/**
 *
 * @author utp
 */
public class Torre extends Ficha {

    public Torre(Color color) {
        super(color);
    }

    @Override
    public void mover(Casilla casillaInicial,Casilla casillaFinal,Casilla camino[],Color color) {
        if((casillaFinal.getColumna()==casillaInicial.getColumna())||
          (casillaFinal.getFila()==casillaInicial.getFila())){
            setCasilla(casillaFinal);
            casillaFinal.setFicha(this); 
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
        if((casillaFinal.getColumna()==casillaInicial.getColumna())||
          (casillaFinal.getFila()==casillaInicial.getFila())){
            setCasilla(casillaFinal);
            Ficha fichaAnterior=casillaFinal.getFicha();
            
            casillaFinal.setFicha(this);
        }else{
                System.out.println("no pudo comer");
                System.out.println("casilla donde intento moverse: "+casillaInicial.getColumna()+casillaInicial.getFila());
                System.out.println("casilla donde intento moverse: "+casillaFinal.getColumna()+casillaFinal.getFila());
            }
        if(casillaFinal.getFicha()==this){
                Ficha ficha=null;
                casillaInicial.setFicha(ficha);   
            }
    }
    
}
