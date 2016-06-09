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
public class Funcion {
    
    public int NUMVARIABLES=20;
    public int NUMOBJETIVOS=4;
    
    public Funcion(int cantidadVariables,int cantidadObjetivos){
        NUMVARIABLES=cantidadVariables;
        NUMOBJETIVOS=cantidadObjetivos;
    }//fin constructor
    

    public void calcularFuncion(Double [] variablesDecision){
    Double[] gen  = variablesDecision;

    Double [] x = new Double[NUMVARIABLES];
    Double [] f = new Double[NUMOBJETIVOS];
    int k = NUMVARIABLES - NUMOBJETIVOS + 1;
        
    for (int i = 0; i < NUMVARIABLES; i++){
      x[i] = gen[i];
    }
        
    Double g = 0.0;
    for (int i = NUMVARIABLES - k; i < NUMVARIABLES; i++){
      g += (x[i] - 0.5)*(x[i] - 0.5);
    }//for
        
    for (int i = 0; i < NUMOBJETIVOS; i++){
      f[i] = 1.0 + g;
    }//for
        
    for (int i = 0; i < NUMOBJETIVOS; i++){
      for (int j = 0; j < NUMOBJETIVOS - (i + 1); j++){            
        f[i] *= Math.cos(x[j]*0.5*Math.PI);
      }//for
        if (i != 0){
          int aux = NUMOBJETIVOS - (i + 1);
          f[i] *= Math.sin(x[aux]*0.5*Math.PI);
        } //if 
    } // for
        
    for (int i = 0; i < NUMOBJETIVOS; i++){
      System.out.print(" y"+i+"="+f[i]);
    }
        //solution.setObjective(i,f[i]);        
  }//calcularFuncion    

    
    
}//fin funcion
