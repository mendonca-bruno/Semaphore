/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 *
 * @author BrunoPC
 */
public class RcSem {
    public static void main(String[] args) throws InterruptedException {
        
        int cont = 0;
        Semaphore semaforo = new Semaphore(1);
        RC rc = new RC();
        List<Processo> processos = new ArrayList<>();
        
        for(int i=0; i<10; i++){
            if(i<5){
                processos.add(new Processo(rc, 1, semaforo, i));
            }else{
                processos.add(new Processo(rc, 0, semaforo, i));   
            }
        }
        
        while(cont<200){
            Collections.shuffle(processos);
            for(int i=0; i<10; i++){
                try {
                    processos.get(i).start();
                } catch (Exception e) {
                    processos.get(i).join();                    
                    processos.set(i, new Processo (rc, processos.get(i).task, semaforo, processos.get(i).idProc));
                    processos.get(i).start();
                }
                }
            cont++;
            
            }
  
    }
}
