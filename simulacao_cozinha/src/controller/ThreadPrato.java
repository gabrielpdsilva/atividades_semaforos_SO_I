package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadPrato extends Thread {
	
	int idPrato;
	Semaphore limiteEntrega;
	
	public ThreadPrato(int idPrato, Semaphore limiteEntrega) {
		this.idPrato = idPrato;
		this.limiteEntrega = limiteEntrega;
	}
	
	@Override
	public void run() {
		
		iniciarPrato();
		cozinharPrato();
		
		try {
			limiteEntrega.acquire();
			entregarPrato();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			limiteEntrega.release();
		}
		
	}
	
	public void entregarPrato(){
		
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("#" + idPrato + " foi entregue.");
	}
	
	public void cozinharPrato(){
		
		System.out.println("#" + idPrato + " esta sendo processado.");
		
		Random random = new Random();
		int tempoTotal;

		int tempoProcessamento = 0;
		
		if(idPrato % 2 != 0){	//sopa de cebola, pronta entre 0,5 e 0,8 segundos
			
			tempoTotal = random.nextInt((800 - 500) + 1) + 500;
			
		} else {	//lasanha a bolonhesa, pronta entre 0,6 e 1,2 segundos
			
			tempoTotal = random.nextInt((1200 - 600) + 1) + 600;
		}
		
		int tempoPorcentagem;
		while(tempoProcessamento < tempoTotal){
			
			//incrementando a cada 0,1 segundos
			tempoProcessamento += 100;
			
			//regra de 3
			tempoPorcentagem = tempoProcessamento * 100 / tempoTotal;
			
			//impedindo que seja printado um valor maior que 100. Como
			//o valor eh quebrado, na regra de 3 o 100% pode resultar em valor
			//um pouco maior como 104% ou 111%
			if(tempoPorcentagem >= 100)
				System.out.println("#" + idPrato + " -> tempo de processamento (milisegundos): " + tempoProcessamento + "\n" + 100 + "% concluido.\n");
			else
				System.out.println("#" + idPrato + " -> tempo de processamento (milisegundos): " + tempoProcessamento + "\n" + tempoPorcentagem + "% concluido.\n");
		}
		
		try {
			sleep(tempoTotal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void iniciarPrato(){
		System.out.println("#" + idPrato + " prato foi iniciado.");
	}
	
}
