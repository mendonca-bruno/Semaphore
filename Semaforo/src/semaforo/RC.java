/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

/**
 *
 * @author BrunoPC
 */
public class RC {
    public int critZone[];
    public int inicio;
    public int fim;
    public int cont;
    
    public RC(){
        critZone = new int[1000];
        inicio = -1;
        fim = -1;
        cont = 0;
    }
    
    public int inserir(){
        if(fim<1000){         
        
            if(inicio == -1){
                
                inicio++;
                critZone[inicio] = 1;
                fim = inicio;
                cont++;
            }else{
                fim++;
                critZone[fim] = 1;
                cont++;
            }
            return 1;
        }
        return 0;
    }
    
    public int deletar(){
        if(cont!=0){
            if(inicio ==0){
                inicio--;
            }
            fim--;
            cont--;
            return 1;
        }
        return 0;
    }
}
