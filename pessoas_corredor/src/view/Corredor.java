package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPessoa;

public class Corredor {
	
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for(int id = 0; id < 4; id++){
			Thread pessoa = new ThreadPessoa(id, semaforo);
			pessoa.start();
		}
	}

}
