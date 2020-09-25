package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadRequisicao extends Thread {
	
	int idRequisicao;
	Semaphore limiteBanco;
	
	public ThreadRequisicao(int idRequisicao, Semaphore limiteBanco) {
		this.idRequisicao = idRequisicao;
		this.limiteBanco = limiteBanco;
		
	}
	
	@Override
	public void run() {
		operar();
	}
	
	public void calcular(){
		
		System.out.println("#" + idRequisicao + " esta calculando.");
	
		Random random = new Random();
		
		int tempoMedioInicial;
		int tempoMedioLimite;
		
		int tempo = 0;
		
		if(idRequisicao % 3 == 1){
			
			tempoMedioInicial = 200;
			tempoMedioLimite = 1000;
			
			tempo = random.nextInt((tempoMedioLimite - tempoMedioInicial) + 1) + tempoMedioInicial;
			
		} else if(idRequisicao % 3 == 2) {
			
			tempoMedioInicial = 500;
			tempoMedioLimite = 1500;
			
			tempo = random.nextInt((tempoMedioLimite - tempoMedioInicial) + 1) + tempoMedioInicial;
			
			
		} else if(idRequisicao % 3 == 0 ){
			
			tempoMedioInicial = 1000;
			tempoMedioLimite = 2000;
			
			tempo = random.nextInt((tempoMedioLimite - tempoMedioInicial) + 1) + tempoMedioInicial;
			
		}
		
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void acessarBanco(){
		System.out.println("#" + idRequisicao + " esta processando no banco.");
		
		int tempo = 0;
		
		if(idRequisicao % 3 == 1){

			tempo = 1000;
			
		} else if((idRequisicao % 3 == 2) || (idRequisicao % 3 == 0)) {
			
			tempo = 1500;
		}
		
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void operar(){
		
		if(idRequisicao % 3 == 1){
			
			for(int i = 0; i < 2; i++){
				
				calcular();
				
				try {
					limiteBanco.acquire();
					acessarBanco();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					limiteBanco.release();
				}
				
			}
			
		}
		
		else if((idRequisicao % 3 == 2) || (idRequisicao % 3 == 0)) {
			
			for(int i = 0; i < 3; i++){
				
				calcular();
				try {
					limiteBanco.acquire();
					acessarBanco();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					limiteBanco.release();
				}
			}
		}
		
	}

}
