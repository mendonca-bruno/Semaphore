/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author BrunoPC
 */
public class Processo extends Thread{
    private RC rc;
    int task;
    Semaphore sem;
    int idProc;
    
    public Processo(RC rc, int task, Semaphore sem, int id){
        this.rc = rc;
        this.task = task;
        this.sem = sem;
        this.idProc = id;
    }
    
    public void fila(){
        try {
            Thread.sleep((long)(Math.random()*1000));
            System.out.println("Processo: " + idProc + " entrou na fila");
            
        } catch (Exception e) {
        }
    }
    
    public void incluir(){
        int cont = 0;
        try {
            System.out.println("Processo: " + idProc + " Entrou na Região Crítica" );
            Thread.sleep((long)(Math.random()*1000));
        } catch (Exception e) {
        }finally{
            int check;
            while(cont<10){
            check = rc.inserir();
            if (check!=0) System.out.println("Processo: " + idProc + " Inseriu na Região Crítica" );
            else System.out.println("Processo: " + idProc + " Não conseguiu inserir na Região Crítica" );
            cont++;
            }
        }
    }
    public void excluir(){
        int cont = 0;
        try {
            System.out.println("Processo: " + idProc + " Entrou na Região Crítica" );
            Thread.sleep((long)(Math.random()*1000));
        } catch (Exception e) {
        }finally{
            int check;
            while(cont<10){
            check = rc.deletar();
            if(check!=0) System.out.println("Processo: " + idProc + " Deletou na Região Crítica" );
            else System.out.println("Processo: " + idProc + " Não conseguiu deletar na Região Crítica" );
            cont++;
            }
        }
    }
    
    
    @Override
    public void run(){
        fila();
        try {
            sem.acquire();
            if(task == 1) incluir();
            else excluir();
        } catch (Exception e) {
        }finally{
            System.out.println("Processo: " + idProc + " Saiu da Região Crítica" );
            System.out.println("Itens armazenados: " + rc.cont);
            sem.release();
        }
    }
}
