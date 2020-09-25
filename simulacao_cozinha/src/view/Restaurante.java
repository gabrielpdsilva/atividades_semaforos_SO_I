package view;

import java.util.concurrent.Semaphore;
import controller.ThreadPrato;

/*

Existem diversos jogos de simulação. Um deles simula a participação
de cozinheiros em uma cozinha profissional realizando pratos. Numa das
fases, o cozinheiro precisa realizar o cozimento de 5 pratos simultâneos,
onde cada cozimento não depende da interação do jogador. Pratos de ID ímpar,
são chamados de Sopa de Cebola e levam de 0,5 a 0,8 segundos para ficar prontos.
Pratos de ID par, são chamados de Lasanha a Bolonhesa e levam de 0,6 a 1,2
segundos para ficar prontos. Quando um prato inicia, é necessário comunicar, em
console, que se iniciou e, a cada 0,1 segundos, deve-se exibir o percentual de
cozimento (O percentual é definido pelo tempo total dividido por 0,1 segundos).
Quando um prato fica pronto, é necessário comunicar em console o final e fazer a
entrega, que leva 0,5 segundos. O jogador só pode entregar um prato por vez e deve
comunicar a entrega. Simular a situação em Java.

 */

public class Restaurante {
	
	public static void main(String[] args) {
		
		Semaphore limiteEntrega = new Semaphore(1);
		
		for(int id = 0; id < 4; id++){
			Thread prato = new ThreadPrato(id, limiteEntrega);
			prato.start();
		}
		
	}

}
