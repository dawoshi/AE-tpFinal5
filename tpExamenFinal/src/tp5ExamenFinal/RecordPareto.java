/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5ExamenFinal;

/**
 *
 * @author usuario
 */
public class RecordPareto {
    public Double [] objetivos;
    public Double [] x;
    public int dominado;
    public RecordPareto(int cantidadCol){        
        objetivos=new Double[cantidadCol]; //conjunto        
        dominado=0;
    }//fin contructor         
    
    public void mostrar(){
        for(int i=0;i<objetivos.length;i++){
            System.out.print(objetivos[i]+",");           
        }//fin mostrar        
        System.out.print("->"+dominado); 
    }//fin Mostrar

    public void cargar(Double [] vectorCarga){
        for(int i=0;i<vectorCarga.length;i++){
            objetivos[i]=vectorCarga[i];
        }//fin mostrar 
    }//
}//fin Pareto
