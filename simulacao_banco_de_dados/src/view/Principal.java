package view;

import java.util.concurrent.Semaphore;
import controller.ThreadRequisicao;

/*

Um servidor com multiprocessamento recebe requisi��es que envolve
realizar c�lculos e fazer transa��es com bancos de dados. Por ter
uma quantidade grande de n�cleos de processamentos e threads, al�m
de um bom algoritmo de escalonamento de threads, enquanto as threads
fazem c�lculos, estes podem ocorrer simultaneamente, mas quando se
faz a transa��o no banco de dados, esta deve ser apenas uma thread por
vez. Considere um conjunto de threads com IDs definidas na pr�pria aplica��o
com n�meros iniciando em 1 e incrementando de um em um. As threads tem
comportamento como segue:

a) Threads com ID dividido por 3 resultando em resto igual a um fazem as transa��es:
- C�lculos de 0,2 a 1,0 segundos
- Transa��o de BD por 1 segundo
- C�lculos de 0,2 a 1,0 segundos
- Transa��o de BD por 1 segundo

b) Threads com ID dividido por 3 resultando em resto igual a dois fazem as transa��es:
- C�lculos de 0,5 a 1,5 segundos
- Transa��o de BD por 1,5 segundo
- C�lculos de 0,5 a 1,5 segundos
- Transa��o de BD por 1,5 segundo
- C�lculos de 0,5 a 1,5 segundos
- Transa��o de BD por 1,5 segundo

c) Threads com ID dividido por 3 resultando em resto igual a zero fazem as transa��es:
- C�lculos de 1 a 2 segundos
- Transa��o de BD por 1,5 segundo
- C�lculos de 1 a 2 segundos
- Transa��o de BD por 1,5 segundo
- C�lculos de 1 a 2 segundos
- Transa��o de BD por 1,5 segundo

Fa�a uma aplica��o em Java que simule a situa��o de 21 Threads simult�neas,
com exibi��o em console de cada passo que a Thread est� realizando.

 */

public class Principal {
	
	public static void main(String[] args) {
		Semaphore limiteBanco = new Semaphore(1);
		
		for(int id = 1; id <= 21; id++){
			Thread requisicao = new ThreadRequisicao(id, limiteBanco);
			requisicao.start();
		}
	}

}
