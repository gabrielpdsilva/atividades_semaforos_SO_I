package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadPessoa extends Thread {
	
	int idPessoa;
	Semaphore semaforo;
	
	public ThreadPessoa(int idPessoa, Semaphore semaforo){
		this.idPessoa = idPessoa;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		
		caminhar();
		//---------------secao critica-----------
		try{
			semaforo.acquire();
			cruzarPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			//----------fim secao critica--------
			liberarPorta();
		}	
		
	}
	
	public void caminhar(){
		
		//pra gerar valores aleatorios num certo intervalo...
		//random.nextInt((max - min) + 1) + min;
		Random random = new Random();
		
		int distanciaTotal = 50;
		int deslocamento = random.nextInt((6 - 4) + 1) + 4;
		int distanciaPercorrida = 0;
		
		while(distanciaPercorrida < distanciaTotal){
			
			distanciaPercorrida += deslocamento;
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("#"+idPessoa+" ja andou " + distanciaPercorrida + " metros.");
			
		}
		
	}
	
	public void cruzarPorta(){
		
		Random random = new Random();
		
		System.out.println("#" + idPessoa + " chegou na porta.");
		int tempo = random.nextInt((2000 - 1000) + 1) + 1000;
		
		try{
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void liberarPorta(){
		System.out.println("#" + idPessoa + " liberou a porta.");
	}
	
}
