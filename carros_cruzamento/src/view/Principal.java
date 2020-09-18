package view;

import java.util.concurrent.Semaphore;

import controller.CarroThread;

public class Principal {
	
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		CarroThread carroL = new CarroThread('L', semaforo);
		CarroThread carroO = new CarroThread('O', semaforo);
		CarroThread carroS = new CarroThread('S', semaforo);
		CarroThread carroN = new CarroThread('N', semaforo);
		
		carroL.start();
		carroO.start();
		carroS.start();
		carroN.start();
		
		
		
	}

}
