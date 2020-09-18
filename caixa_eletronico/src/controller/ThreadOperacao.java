package controller;

import java.util.concurrent.Semaphore;

public class ThreadOperacao extends Thread {
	
	int id;
	int saldo;
	int valorASerTransacionado;
	Semaphore limiteTipo;
	Semaphore limiteTransacoes;
	
	public ThreadOperacao(int id, int saldo, int valorASerTransacionado, Semaphore limiteTipo, Semaphore limiteTransacoes){
		this.id = id;
		this.saldo = saldo;
		this.valorASerTransacionado = valorASerTransacionado;
		this.limiteTipo = limiteTipo;
		this.limiteTransacoes = limiteTransacoes;
	}
	
	@Override
	public void run() {
		try {
			limiteTipo.acquire();
			limiteTransacoes.acquire();
			realizarOperacao();
		} catch(InterruptedException e){
			e.printStackTrace();
		} finally {
			limiteTipo.release();
			limiteTransacoes.release();
		}
	}
	
	public void realizarOperacao(){
		if(saldo > 0){
			sacar();
		} else {
			depositar();
		}
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sacar(){
		System.out.println("ID da conta: " + id + "; saldo atual: " + saldo + "; a sacar: " + valorASerTransacionado + "; ");
		saldo -= valorASerTransacionado;
		System.out.println("Saldo atualizado do ID " + id + ": " + saldo);
		System.out.println();
		
	}
	
	public void depositar(){
		System.out.print("ID da conta: " + id + "; saldo atual: " + saldo + "; a depositar: " + valorASerTransacionado + "; ");
		saldo += valorASerTransacionado;
		System.out.println("Saldo atualizado do ID " + id + ": " + saldo);
		System.out.println();
	}
	
}
