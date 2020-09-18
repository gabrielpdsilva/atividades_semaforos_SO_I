package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

/*
 * 10 carros estao distantes de um estacionamento de 1 a 2 km.
 * Os carros rodam a velocidade media de 100 m/s.
 * O estacionamento tem 3 vagas.
 * Os 3 primeiros a chegar estacionam e ficam entre 1 a 3 s.
 * Se o estacionamento estiver lotado, os outros aguardam
 * em fila por ordem de chegada.
 * Quando um carro sai, o proximo da fila entra.
 * Saber a ordem de chegada e saida eh importante.
 * 
 */

public class Estacionamento {
	
	public static void main(String[] args) {
		int permissoes = 3;
		Semaphore semaforo = new Semaphore(permissoes);
		for(int idCarro = 0; idCarro < 10; idCarro++){
			Thread carro = new ThreadCarro(idCarro, semaforo);
			carro.start();
		}
	}

}
