package view;

import java.util.concurrent.Semaphore;
import controller.ThreadPrato;

/*

Existem diversos jogos de simula��o. Um deles simula a participa��o
de cozinheiros em uma cozinha profissional realizando pratos. Numa das
fases, o cozinheiro precisa realizar o cozimento de 5 pratos simult�neos,
onde cada cozimento n�o depende da intera��o do jogador. Pratos de ID �mpar,
s�o chamados de Sopa de Cebola e levam de 0,5 a 0,8 segundos para ficar prontos.
Pratos de ID par, s�o chamados de Lasanha a Bolonhesa e levam de 0,6 a 1,2
segundos para ficar prontos. Quando um prato inicia, � necess�rio comunicar, em
console, que se iniciou e, a cada 0,1 segundos, deve-se exibir o percentual de
cozimento (O percentual � definido pelo tempo total dividido por 0,1 segundos).
Quando um prato fica pronto, � necess�rio comunicar em console o final e fazer a
entrega, que leva 0,5 segundos. O jogador s� pode entregar um prato por vez e deve
comunicar a entrega. Simular a situa��o em Java.

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
