/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5ExamenFinal;

import java.util.Random;
import java.text.DecimalFormat;
import java.util.Vector;

public class Evolutivo {

    //-----------------------------------------------
    //------------ CONFIGURACION DEL ALGORITMO ---------------------            
    public int CANTIDADINDIVIDUOS=4;    
    public int MINIMIZAR=0;
    public int MAXIMIZAR=1;
    public int MAXGENERACIONES=20;    

    public int FLAG=MINIMIZAR; //por default configurado para minimizar

    public Double INICIOINTERVALO=0.0;
    public Double FININTERVALO=1.0;


    //----- Configuracion de NPGA -----
    public static Double SHARE=1.0;
    public static int PRESIONDOMINANCIA=15;

    //-------------------------------

    
    public Pareto2 funcionesPareto=new Pareto2(MINIMIZAR);
    //-----------------------------------------------
    //------------ FIN CONFIGURACION DEL ALGORITMO ---------------------        
    public Individuo [] poblacion;
    Individuo [] poblacionHijos;    
    Individuo [] poblacionNPGA;
    Individuo [] listadoNoDominados;
    Individuo [] paretoSetTemp;

    Individuo [] paretoAntes;//temporal
    Vector <Individuo> paretoSet=new Vector<Individuo>();//donde se guardan los paretos

    
    Individuo elMejor; //la mejor solucion segun el contexto

    
    public Evolutivo(int individuos,int generaciones,int flagMinMax, Double a, Double b,Double share, int presionDominancia){
        CANTIDADINDIVIDUOS=individuos;    
        MAXGENERACIONES=generaciones;    
        FLAG=flagMinMax; //por default configurado para minimizar    
        INICIOINTERVALO=a;
        FININTERVALO=b;
        
        poblacion=new Individuo[CANTIDADINDIVIDUOS];
        poblacionHijos=new Individuo[CANTIDADINDIVIDUOS];


        //---npga
        SHARE=share;
        PRESIONDOMINANCIA=presionDominancia;
        //----
        
        for(int i=0;i<poblacion.length;i++){
            poblacion[i]=new Individuo();
        }//for
    }//fin constructor
    
    
        
    
    /* Recorre la tabla de poblacion y guarda los nuevos hijos despues
        pool de apareamiento, los mejores entran de a pares con la poblacion
    */
    public void CruzarPoblacion(){        
        Individuo hijo=new Individuo();
        //inicializar a cero tabla hijos
        for(int i=0;i<poblacion.length;i++){
            //--- cargar ---
            poblacionHijos[i]=new Individuo();
        }//for        
        //---------------------------------
        
        System.out.println("ANTES DEL CICLO poblacionNPGA.length="+poblacionNPGA.length);
        for(int i=0;i<poblacionNPGA.length;i++){            
            //evita cruzar consigo mismo
//                System.out.println("-----antes del cruzamiento individual");
                hijo=new Individuo();
                hijo.vectorDecisiones=Cruzamiento(poblacionNPGA[i].vectorDecisiones, poblacion[i].vectorDecisiones);
//                hijo=poblacionNPGA[i];
                //solo importa el vector de decisiones
                poblacion[i]=MutacionIndividual(hijo, INICIOINTERVALO, FININTERVALO);
                poblacion[i]=hijo;
                poblacion[i].calcularFuncion();//calcula su funcion nuevamente
                poblacion[i].index=i;//guarda el indice                        
                poblacion[i].dominado=0;//guarda el indice                        
            //cruzamiento por parejas
        }//for
        
        System.out.println("FIN DEL CICLO poblacionNPGA.length="+poblacionNPGA.length);        
        
        //inicializar a cero el vector poblacion
        for(int i=0;i<poblacion.length;i++){
                poblacion[i].index=i;//guarda el indice                        
                poblacion[i].dominado=0;//guarda el indice
        }//for
        
    }//fin cruzar poblacion
    
    
    
       
    
        /*
    En el cruzamiento se asume que se tienen a dos progenitores.
    Engendra un hijo del tamaño del padre
    */       
    public Double [] Cruzamiento(Double [] papa, Double [] mama){
        //se asume que ambas cadenas son del mismo tamano
        int LARGO=papa.length;
        int LARGOM=mama.length;

        
        int aleatorio=0;
        //--- uso random
//        Random rnd=new Random();       
//        rnd.setSeed(System.currentTimeMillis());
//        aleatorio=(int)(rnd.nextDouble()*(LARGO-1)+0);

        aleatorio=(int)(Math.random()*(LARGO)+0);
         
        //--- fin uso de random
        int CORTE=aleatorio;
        
        //devolver el resultado
//        Hijos resultadoHijos=new Hijos();
        Double [] hijo=new Double[LARGO];
        
//        System.out.println("Funcion cruzamiento->");
//        System.out.println("LARGO=->"+LARGO+" aleatorio="+aleatorio);        

        //bucle para cambiar valores
        for(int i=0;i<LARGO;i++){

            if(i<CORTE){
                //System.out.println("i <= CORTE");
                //primera parte del padre y madre
                hijo[i]=papa[i];
            }else{
                //segunda parte del padre y madre
                //System.out.println("i > CORTE");
                hijo[i]=mama[i];
            }//if
            
        }//for


        return hijo;
    }//Cruzamiento

    
    
    public void IniciarPoblacion(Double a, Double b ){
        Random rnd=new Random();        
        rnd.setSeed(System.currentTimeMillis());
        for(int fila=0;fila<poblacion.length;fila++){
            for(int i=0;i<poblacion[fila].vectorDecisiones.length;i++){
                poblacion[fila].vectorDecisiones[i]=(rnd.nextDouble()*b)+a;
            }//for
            poblacion[fila].dominado=0;//reinicia a no dominado
            poblacion[fila].index=fila;//siempre guarda su posicion en la tabla para compararlo            
            poblacion[fila].calcularFuncion(); //si o si debe calcular el valor
        }//for
    }//IniciarPoblacion

    public void MostrarPoblacion(){
        for(int fila=0;fila<poblacion.length;fila++){
            poblacion[fila].mostrarDeciciones();
//            System.out.print(" ->");
//            poblacion[fila].mostrarSoluciones();
            System.out.println();
        }//for
    }//MostrarPoblacion

    
    public Double distanciaEuclideana(Double [] punto1,Double [] punto2){
        Double distancia=0.0;
        Double sumatoria=0.0;
        if(punto1.length==punto2.length){
            //si son iguales en longitud hacer
            for(int i=0;i<punto1.length;i++){
                sumatoria=sumatoria+(Math.pow((punto1[i]-punto2[i]), 2));
            }//for
            distancia=Math.sqrt(sumatoria);
        }else{
            distancia=-1.0;//error
        }            
        return distancia;
    }//distancia eucideana
    
    public int conteoDistanciaNPGA(Double share,Individuo individuo,Individuo [] conjunto){
        int conteo=0;
        Double distancia=0.0;
        
        for(int i=0;i<conjunto.length;i++){
            distancia=distanciaEuclideana(individuo.vectorSoluciones,conjunto[i].vectorSoluciones);
//            System.out.println("a punto->");            
//            conjunto[i].mostrarSoluciones();
//            System.out.println("fconteo d="+distancia);
               //si son iguales es 0, si d1<d2=-1; d1>d2=1;
//               System.out.println("compare="+Double.compare(distancia, share));
            if((Double.compare(distancia, share)==-1) && !(Double.compare(distancia, 0.0)==0)){
                conteo++;//cuenta los vecinos
            }//if
        }//for
        return conteo;
    }//conteoDistanciaNPGA

    /*
    es requisito básico una poblacion cargada, por eso utiiza
    la poblacion en forma global para jugar al torneo
    Devuelve un conjunto elegible para hacer algo, a mutar y listo
    */
    public Individuo [] SeleccionNPGA(int presionDominancia, Double share){
        //la poblacion debe estar previamente cargada
        int N=poblacion.length;
        
        Individuo pi=new Individuo();
        Individuo pj=new Individuo();
        
        Vector <Individuo> M=new Vector<Individuo>();//vector
        Individuo[] resultadoNPGA;//devuelve el resultado
        
        Individuo [] PDom=new Individuo[presionDominancia]; //array al azar

        int i=0;//indice de individuo i de la poblacion
        int j=0;//indice de individuo j de la poblacion
        int pdomIndex=0;//valor para el indice en los dominados

        int flagDominadoPi=0;//indica si el elemento poblacion i es dominado
        int flagDominadoPj=0;//indica si el elemento poblacion j es dominado

        int ni=0; //conteo de vecinos para i y j dentro del pareto
        int nj=0;
        int decide=0;

        //repetir hasta que termine poblacion       
        for(int elegidos=0;elegidos<N;elegidos++){
            //---------------------------------
            //seleccion dos individuos, calcula el indice al azar
            //dentro del rango del vector
            i=(int)(Math.random()*(N)+0);
            j=(int)(Math.random()*(N)+0);
            //inicializa las variables para que no queden basuras
            pi=new Individuo();
            pj=new Individuo();
            //asigna los individuos a dos variables locales para comparacion
            //que se utilizaran para insertarlos en el conjunto M
            pi=poblacion[i];
            pj=poblacion[j];
            
//            System.out.print(" NPGA-> pi");  pi.mostrarSoluciones();System.out.println(" ");
//            System.out.print(" NPGA-> pj");  pj.mostrarSoluciones();System.out.println(" ");            
            
            //seleccion del conjunto de comparacion PDom
            //al azar dentro de la poblacion
            PDom=new Individuo[presionDominancia]; //array al azar
            
//            System.out.println("-- CONjunto Pdom --");
            for(int seleccion=0;seleccion<PDom.length;seleccion++){
                pdomIndex=(int)(Math.random()*(N)+0);//aleatorio
                PDom[seleccion]=new Individuo();
                PDom[seleccion]=poblacion[pdomIndex];
                
                //PDom[seleccion].mostrarSoluciones(); System.out.println("| ");
            }//for
            
            //--------
            //se hace el torneo de dominado contra el conjuntoPDom
            flagDominadoPi=0;
            flagDominadoPj=0;
            for(int seleccion=0;seleccion<PDom.length;seleccion++){
                if(funcionesPareto.dominanciaPareto(PDom[seleccion].vectorSoluciones,pi.vectorSoluciones,FLAG)==1){
//                    System.out.println("Pi es dominado flagDominado=->"+flagDominadoPi);
                    flagDominadoPi=1;
                }//fin
                if(funcionesPareto.dominanciaPareto(PDom[seleccion].vectorSoluciones,pj.vectorSoluciones,FLAG)==1){
//                    System.out.println("Pj es dominado flagDominado=->"+flagDominadoPj);
                    flagDominadoPj=1;
                }//fin                
            }//for
            //--------            
            //verificar si es dominado 
            //1 si es dominado, 0 si es no dominado
//            System.out.println("Banderas dominados flagDominadoPi=->"+flagDominadoPi+"  flagDominadoPj=->"+flagDominadoPj);            
            
            if(flagDominadoPi==0 && flagDominadoPj==1){
                //se guarda en M Pi
//                System.out.println("I NO DOMINADO->");
                M.add(pi);
            }else{
                if(flagDominadoPi==1 && flagDominadoPj==0){
//                System.out.println("JJ NO DOMINADO->");
//                pj.mostrarSoluciones();
                    //se guarda en M Pj
                    M.add(pj);
                }else{
//                   System.out.println("NO SE DOMINAN las flags son cero ambas");
//                   System.out.println("Banderas dominados flagDominadoPi=->"+flagDominadoPi+"  flagDominadoPj=->"+flagDominadoPj);
                   
                   if(M.isEmpty()){
//                    System.out.println("M ES VACIO->");
                    //-----------------------------------------
                    //calcular cuantos estan a una distancia
                      ni=conteoDistanciaNPGA(share,pi,PDom);
                      nj=conteoDistanciaNPGA(share,pj,PDom);                      
                    //-----------------------------------------
//                    System.out.println("ni="+ni+"  nj="+nj);
                    
                   }else{
                     //si tiene elementos
                     //calcular cuantos elementos estan a una distancia share de M
                     Individuo[] arrayM = M.toArray(new Individuo[M.size()]);
                     ni=conteoDistanciaNPGA(share,pi,arrayM);    
                     nj=conteoDistanciaNPGA(share,pj,arrayM);
//                    System.out.println("M tiene elementos->");
//                    System.out.println("ni="+ni+"  nj="+nj);
                    
                   }//if(M.isEmpty())                      
                     if(ni<nj){
//                         System.out.println("ni<nj agrega pi");
                         M.add(pi);//tiene menos individuos en su nicho
                     }else{
                         if(nj<ni){
//                         System.out.println("nj<ni agrega pj");
                             M.add(pj);
                         }else{

                             decide=pdomIndex=(int)(Math.random()*(1)+0);
                             //decide al azar que va a hacer
//                            System.out.println("decide al azar->"+decide);                             

                             if(decide==0){
//                                System.out.println("Agrega a M pi->");                                                         
                                 M.add(pi);     
                             }else{
//                            System.out.println("Agrega a M pj->");                                                                                          
                                M.add(pj);
                             }//fin (decide==0
                         }//if(nj<ni)
                     }//if(nj<ni)                     
                }//(flagDominadoPi==1 && flagDominadoPj==0)
            }// if(flagDominadoPi==0 && flagDominadoPj==1)
            //---------------------------------            
        }//for de la poblacion               
        
        //--------------------------------------
/*        
        System.out.println("---- RESULTADO DE M.. M de macanada...---");
        System.out.println("---- inicio Decision...---");        
        for(int indice=0; indice<M.size(); indice++){
            M.elementAt(indice).mostrarDeciciones();
            System.out.println();
        }//for
        System.out.println("---- fin Decision...---");        
        System.out.println("---- inicio Soluciones...---");        
        for(int indice=0; indice<M.size(); indice++){
            M.elementAt(indice).mostrarSoluciones();
            System.out.println();
        }//for
        System.out.println("---- fin Soluciones...---");        
*/
        //--------------------------------------        
        resultadoNPGA = M.toArray(new Individuo[M.size()]);
        return resultadoNPGA;                      
    }//fin Asignacion Fitness
    


    /*
    Se encarga de mutar una sola cadena de ADN
    */
    public Individuo MutacionIndividual(Individuo individuo, Double a, Double b){
        Individuo resultado=new Individuo(); //resultado
        
        //se obtiene el largo de la cadena a mutar
        int LARGO=individuo.vectorDecisiones.length;

        //--- uso random
        Random rnd=new Random();        
        rnd.setSeed(System.currentTimeMillis());

        //---------------------------
        //bit que va a mutar dentro de la cadena
        //hasta el largo de la cadena
        //---------------------------
        int posicion=(int)((rnd.nextDouble()*(LARGO)+0));

        //prepara el bit que va a mutar
//        Double bitMutar=(rnd.nextDouble()*b)+a;
        Double bitMutar=rnd.nextDouble();
        
            //------------------------------------------------            
            //---reemplazar en una posicion especifica el bit mutado
            individuo.vectorDecisiones[posicion]=bitMutar;
            resultado=individuo;

        return resultado;
    }//MutacionIndividual


    
    
    public void ProcesoPrincipal(){
        int flagIgual=0;
        //inicializar la poblacion
        IniciarPoblacion(INICIOINTERVALO, FININTERVALO);
        
        for(int generacion=0;generacion<MAXGENERACIONES;generacion++){

            
        System.out.println(generacion+")--- INICIO GENERACION -------------------------");                            
        MostrarPoblacion();                
        System.out.println("---------------SELECCIONANDO NPGA----------------------");
        
        poblacionNPGA=SeleccionNPGA(PRESIONDOMINANCIA, SHARE);

/*
        System.out.println("inicio vector poblacionNPGA----------------------");         
        for(int i=0;i<poblacionNPGA.length;i++){
            poblacionNPGA[i].mostrarDeciciones();
            System.out.println("");             
        }//for
        System.out.println("fin vector poblacionNPGA----------------------");                 
        //-----------------------------------------------
*/
//----------------listadoNoDominados=poblacionNPGA;
        //------------aca guardo las buenas soluciones ----
        listadoNoDominados=funcionesPareto.getConjuntoPareto(poblacionNPGA);//del NPGA lindo y bonito
        //actualizar el pareto puretoooo

    
        if(generacion==0){
//------------------------------------------------------------.
            paretoSet.add(listadoNoDominados[0]);//al pareto existente le agrega los recien llegados 
            //------------------------------------------------------------            
        }//generacion==0
//------------------------------------------------------------
        for(int fila=0;fila<listadoNoDominados.length;fila++){        
            flagIgual=0;//inicializa la bandera

            for(int j=0;j<paretoSet.size();j++){

                if(funcionesPareto.esIgual(listadoNoDominados[fila].vectorSoluciones, paretoSet.elementAt(j).vectorSoluciones)==1){
                    //son iguales
                    flagIgual=1;
                    //break;
                }//if                
            }//for

            if(flagIgual==0){
                paretoSet.add(listadoNoDominados[fila]);//al pareto existente le agrega los recien llegados                
            }//if flagIgual==0            
        }//for
//------------------------------------------------------------


        //una vez agregados los nuevos elementos no dominados a pareto
        //se convierte a array para poder verificar los no dominados
        paretoAntes=paretoSet.toArray(new Individuo[paretoSet.size()]);
        
        //se calcula el nuevo frente pareto
        paretoSetTemp=funcionesPareto.getConjuntoPareto(paretoAntes);
        //obtener el nuevo frente pareto
        
        paretoSet.clear();
        for(int i=0;i<paretoSetTemp.length;i++){
            paretoSet.add(paretoSetTemp[i]);//guarda finalmente en el pareto
        }//for
        //--------------------------


        //guardar el frente pareto
        //------------aca guardo las buenas soluciones ----

        


  
        System.out.println("---------------DESPUES DE SELECCION----------------------");        
  //      MostrarPoblacion();  
        System.out.println("---------------ANTES DEL CRUZAMIENTO----------------------");        
        //se van a la piscina de apareamiento
        CruzarPoblacion();        
        System.out.println("---------------FIN DEL CRUZAMIENTO----------------------");        
//        MostrarPoblacion();  

        System.out.println(generacion+"----------------- FIN GENERACION -------------------------");                
        }//fin generaciones

        System.out.println("--- RESULTADO FINAL ---");      
//        System.out.println("INTERVALO a="+INICIOINTERVALO+"  "+"b="+FININTERVALO);

        if(FLAG==MAXIMIZAR){
            System.out.println("BUSQUEDA DE MÁXIMO");                  
        }else{
            System.out.println("BUSQUEDA DE MINIMO");                          
        }


        
        System.out.println("-------INICIO ULTIMO VECTOR SOLUCIONES-----------------");
        for(int i=0;i<poblacion.length;i++){
            poblacion[i].mostrarSoluciones();
            System.out.println();
        }//for
        System.out.println("-------FIN ULTIMO VECTOR SOLUCIONES-----------------");                  

        System.out.println("-------INICIO ULTIMO VECTOR DE DECISION-----------------");
        for(int i=0;i<poblacion.length;i++){
//            poblacion[i].mostrarDeciciones2();
            poblacion[i].mostrarDeciciones();
            System.out.println();
        }//for
        System.out.println("-------FIN ULTIMO VECTOR DE DECISION-----------------");                  
        

        System.out.println("--- INICIO DECISIONES VECTOR PARETO FINAL---");      
        for(int i=0;i<paretoSet.size();i++){
            paretoSet.elementAt(i).mostrarDeciciones();
            System.out.println();            
        }//for
        System.out.println("--- FIN  DECISIONES VECTOR PARETO FINAL---");        
        
        
        System.out.println("--- SOLUCIONES INICIO VECTOR PARETO FINAL---");      
        for(int i=0;i<paretoSet.size();i++){
            paretoSet.elementAt(i).mostrarSoluciones();
            System.out.println();            
        }//for
        System.out.println("--- SOLUCIONES FIN VECTOR PARETO FINAL---");
        
        
        System.out.println("--- RESULTADO FINAL NPGA---");      
        System.out.println("INTERVALO a="+INICIOINTERVALO+"  "+"b="+FININTERVALO+" SHARE="+SHARE+" P.DOMINANCIA="+PRESIONDOMINANCIA);
        System.out.println("INDIVIDUOS="+CANTIDADINDIVIDUOS+"  "+"FLAG MIN/MAX="+FLAG);
        System.out.println("MAXGENERACIONES="+MAXGENERACIONES+" NUMVARIABLES="+poblacion[0].NUMVARIABLES+" NUMOBJETIVOS="+poblacion[0].NUMOBJETIVOS);
        System.out.println("CANTIDADINDIVIDUOS="+CANTIDADINDIVIDUOS);
        

        
    }//fin proceso principal
    
}//fin clase Evolutivo


