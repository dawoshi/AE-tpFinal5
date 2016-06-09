
package tp5ExamenFinal;

/*

TP 5 (Examen Final)
Este trabajo práctico propone resolver problemas del Benchmark DTLZ propuesto por Deb, 
Thiele, Laumanns y Zitzler (http://www.tik.ee.ethz.ch/sop/publicationListFiles/dtlz2002a.pdf) 
utilizando un algoritmo evolutivo multiobjetivo (Multi-Objective Evolutionary Algorithm - MOEA)
de su preferencia, para el caso Many-Objective - MaOP, es decir, cuando el número de objetivos 
es al menos 4. Por ejemplo, puede optar por implementar el SPEA, SPEA2, NSGA, NSGA-II, 
o cualquier otro que escoja o decida proponer, debidamente optimizado para resolver problemas MaOP. 
Solo se verificará la capacidad de resolver el problema DTLZ 2, con 4 y 8 objetivos, 
con una variable de decisión x (variable independiente) de dimensión 20.
Enlaces de Referencia:
http://people.ee.ethz.ch/~sop/download/supplementary/testproblems/dtlz2/index.php

AYUDA: 
Se podrán verificar los resultados utilizando el entorno jMetal (http://jmetal.sourceforge.net/) 

* Se implementó el algoritmo NPGA.

*/

public class Principal {

    
    //------------ CONFIGURACION DEL ALGORITMO ---------------------    
    // PARA OBTENER UN BUEN FRENTE PARETO EN DOS DIMENSIONES
    // -------------------------------------------------------------
    public static Double inicioIntervalo=0.0;
    public static Double finIntervalo=1.0;
        
    public static int cantidadIndividuos=100;    
    public static int MINIMIZAR=0;
    public static int MAXIMIZAR=1;
    public static int MAXGENERACIONES=500;    

//    public static int FLAG=MAXIMIZAR;
    public static int FLAG=MINIMIZAR;    

    public static Double SHARE=4.0;
    public static int PRESIONDOMINANCIA=20;

    
/*    
    //-----------------------------------------------
    //------------ CONFIGURACION DEL ALGORITMO ---------------------    
    public static Double inicioIntervalo=0.0;
    public static Double finIntervalo=1.0;
        
    public static int cantidadIndividuos=100;    
    public static int MINIMIZAR=0;
    public static int MAXIMIZAR=1;
    public static int MAXGENERACIONES=400;    

//    public static int FLAG=MAXIMIZAR;
    public static int FLAG=MINIMIZAR;    

    public static Double SHARE=4.0;
    public static int PRESIONDOMINANCIA=20;
    */
    //-----------------------------------------------
    //------------ FIN CONFIGURACION DEL ALGORITMO ---------------------        
    
    
    public static void main(String[] args) {

        /*
        Funcion prueba=new Funcion(20,4);        
        double [] decision={0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,0.99,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,0.99};        
        prueba.calcularFuncion(decision);
        */

//        Evolutivo evo=new Evolutivo(cantidadIndividuos,MAXGENERACIONES,FLAG,0.0,1.0,SHARE,PRESIONDOMINANCIA);
 //       evo.ProcesoPrincipal();
//        evo.IniciarPoblacion(inicioIntervalo, finIntervalo);
//        evo.MostrarPoblacion();
//        evo.CruzarPoblacion();


/*        
        Double [] papa={0.1,0.2,0.3,0.4,0.5,0.1,0.2,0.3,0.4,0.5};        
        Double [] mama={0.12,0.0100,0.4444,0.1,0.1,0.1244,0.0,0.4,0.1,0.1};        
        Double [] hijo={0.12,0.0,0.4,0.1,0.1};        
        
        Individuo p=new Individuo(papa.length,4);
        p.vectorDecisiones=evo.Cruzamiento(papa, mama);        
        p.mostrarDeciciones();
*/

//---------- PRUEBA DE DISTANCIAS     
//    Double [] vector1={12.0,0.0,5.0};
//    Double [] vector2={13.0,1.0,6.0};
//    System.out.println("distancia="+evo.distanciaEuclideana(vector1, vector2));
    //para probar los paretos

/*    
//----MATRIZ DE PRUEBAS POBLACION
    Double [][] matrizp={
//            {2.0,2.0},
            {7.0,4.0},
            {7.0,6.0},
            {9.0,7.0},            
            {8.0,6.0},            
            {1.0,8.0},
            {2.0,7.0},
            {3.0,5.0},
            {4.0,3.0},
            {6.0,2.0},
            {8.0,1.0},
            {10.0,1.0},
            {4.0,5.0},
            {1.0,10.0},            
            {8.0,4.0},                                    
            {2.0,2.0},                        
    };
    Pareto2 funcionPareto=new Pareto2(0);
    Individuo [] lPrueba=new Individuo[matrizp.length];
    Individuo [] lparetos=new Individuo[matrizp.length];    
    Double share=4.0;//parametro de vecindad

    //--------------- cargar POBLACION DE PRUEBA
    for(int i=0;i<matrizp.length;i++){
        lPrueba[i]=new Individuo();
        lPrueba[i].vectorSoluciones=matrizp[i]; 
        lPrueba[i].mostrarSoluciones();
        System.out.println();
    }//for carga
    //--------------- cargar
*/
    /*    
    //OBTIENE PARETOS
    lparetos=funcionPareto.getConjuntoPareto(lPrueba);//devuelve conjunto pareto
    //-------------
    System.out.println("CONJUNTO PARETOS PICHICATEADOS-->");
    for(int i=0;i<lparetos.length;i++){
        lparetos[i].mostrarSoluciones();
        System.out.println();
    }//for
*/
/*    
    System.out.println("--MALDITA ZORRA NPGA-->");
    
    for(int i=0;i<lparetos.length;i++){
    System.out.println("--DESDE PUNTO PARETO-->");    
    lparetos[i].mostrarSoluciones();
    System.out.println("<--");                
    System.out.println("Vecinos con share="+share+"->"+" fila="+i+") cantvecinos="+evo.conteoDistanciaNPGA(4.0,lparetos[i],lparetos));
//     System.out.println("distancia="+evo.distanciaEuclideana(lparetos[0].vectorSoluciones, lparetos[i].vectorSoluciones));
     System.out.println("<--");     
    }//for
*/


    //CARGA UNA POBLACION CON PARETOS
    //para tener la poblacion inicializada
//    evo.poblacion=lparetos; //paretos
//    evo.poblacion=lPrueba;
//    int presionDominancia=5;//grupo de cinco individuos

    System.out.println("-------- CALCULO DE NPGA---------------------");
    Evolutivo evo=new Evolutivo(cantidadIndividuos,MAXGENERACIONES,FLAG,inicioIntervalo,finIntervalo,SHARE,PRESIONDOMINANCIA);
//    evo.IniciarPoblacion(0.0, 1.0);
//    evo.SeleccionNPGA(PRESIONDOMINANCIA, SHARE);

evo.ProcesoPrincipal();

    
    
    
    }//fin de main
    
}//fin de principal
