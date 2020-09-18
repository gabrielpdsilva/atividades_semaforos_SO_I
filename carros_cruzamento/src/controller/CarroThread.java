package controller;

import java.util.concurrent.Semaphore;

public class CarroThread extends Thread {

	char sentido;
	Semaphore semaforo;
	
	public CarroThread(char sentido, Semaphore semaforo) {
		this.sentido = sentido;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		
		avancar();
		
		try {
			semaforo.acquire();
			cruzar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			liberarEstrada();
		}
		
	}
	
	public void avancar(){
		int distanciaTotal = 500;
		int distanciaPercorrida = 0;
		int deslocamento = 100;
		int tempo = 1000;
		
		while(distanciaPercorrida < distanciaTotal){
			
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("#"+ sentido +" ja andou " + distanciaPercorrida + " metros.");
			
		}
		
	}
	
	public void cruzar(){
		System.out.println("Carro de sentido " + sentido + " estah atravessando.");
		
		try{
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void liberarEstrada(){
		System.out.println("Carro de sentido " + sentido + " saiu.");
	}

}
