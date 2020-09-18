package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.ThreadOperacao;

public class Principal {
	
	public static void main(String[] args) {
		
		Semaphore limiteTransacoes = new Semaphore(2);
		Semaphore limiteSaque = new Semaphore(1);
		Semaphore limiteDeposito = new Semaphore(1);

		Random random = new Random();
		
		
		for(int i = 0; i < 20; i++){
			
			int id = random.nextInt((50 - 0) + 1) + 0;
			int saldo = random.nextInt((3000 - 10) + 1) + (-1000);
			int valorASerTransacionado = random.nextInt((500 - 10) + 1) + 10;
			
			
			if(saldo > 0){
				Thread saque = new ThreadOperacao(id, saldo, valorASerTransacionado, limiteSaque, limiteTransacoes);
				saque.start();
			} else {
				Thread deposito = new ThreadOperacao(id, saldo, valorASerTransacionado, limiteDeposito, limiteTransacoes);
				deposito.start();
			}
			
		}
		
	}

}
