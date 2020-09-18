package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

public class Principal {
	
	public static void main(String[] args) {
		
		Semaphore limitePista = new Semaphore(5);
		
		Semaphore limiteEquipe = new Semaphore(1);
		
		//pra gerar valores aleatorios num certo intervalo...
		//random.nextInt((max - min) + 1) + min;
		Random random = new Random();
		
		int numeroEquipe;
		
		for(int id = 0; id < 14; id++){
			
			numeroEquipe = random.nextInt((6 - 0) + 1) + 0;
			
			Thread carro = new ThreadCarro(id, numeroEquipe, limitePista, limiteEquipe);
			carro.start();
		}
		
	}

}
