package tp5ExamenFinal;

import java.util.Arrays;

public class Individuo {
    public int NUMVARIABLES=20;
    public int NUMOBJETIVOS=8;
    
    public Double [] vectorDecisiones=new Double[NUMVARIABLES];
    public Double [] vectorSoluciones=new Double[NUMOBJETIVOS];
    public Double fitness=0.0;
    public int index=0;
    public int dominado=0;

    public Individuo(){
        vectorDecisiones=new Double[NUMVARIABLES];
        vectorSoluciones=new Double[NUMOBJETIVOS];
        fitness=0.0;        
    }//fin contructor

    

    public Individuo(int cantVariables, int cantSoluciones){
        vectorDecisiones=new Double [cantVariables];
        vectorSoluciones=new Double [cantSoluciones];
        fitness=0.0;        
    }//fin contructor
  
    
    public Double [] getDeciciones(){
        Double [] vectorResultado;
        vectorResultado=new Double[vectorDecisiones.length];
        //devuelve el vector
        System.arraycopy(vectorDecisiones, 0, vectorResultado, 0, vectorDecisiones.length);
        return vectorResultado;
    }//getDeciciones

    public Double [] getSoluciones(){
        Double [] vectorResultado;
        vectorResultado=new Double[vectorSoluciones.length];
        //devuelve el vector
        System.arraycopy(vectorSoluciones, 0, vectorResultado, 0, vectorSoluciones.length);
        return vectorResultado;
    }//getDeciciones

    public void mostrarDeciciones(){
        for(int fila=0;fila<vectorDecisiones.length;fila++){
            System.out.print(";"+vectorDecisiones[fila]);
        }        
            System.out.print(";");
    }//mostrarDeciciones
    
    
    public void mostrarDeciciones2(){
        for(int fila=0;fila<vectorDecisiones.length;fila++){
            System.out.println("X"+fila+")="+vectorDecisiones[fila]);
        }        
//            System.out.print(";");
    }//mostrarDeciciones2
    
    
    public void mostrarSoluciones(){
        for(int fila=0;fila<vectorSoluciones.length;fila++){
            System.out.print(";"+vectorSoluciones[fila]);
        }        
            System.out.print(";");
    }//mostrarDeciciones


   public void calcularFuncion(){

    Double[] gen=new Double[vectorDecisiones.length];
       
    System.arraycopy(vectorDecisiones, 0, gen, 0, vectorDecisiones.length);


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

    /*
    for (int i = 0; i < NUMOBJETIVOS; i++){
      System.out.print(" y"+i+"="+f[i]);
    }
    */
    
    System.arraycopy(f, 0, vectorSoluciones, 0, vectorSoluciones.length);
  }//calcularFuncion    






}//fin Individuo
