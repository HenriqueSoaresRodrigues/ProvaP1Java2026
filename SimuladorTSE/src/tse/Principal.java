package tse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	static int[] candidatos = {-1,1,2,3};
	static int[] votosRegistrados = {0,0,0,0};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Eleição acontecendo
		List<Voto> listaVotos = new ArrayList<>();
		List<Justificativa> listaJustificativas = new ArrayList<>();
		System.out.println("Qual é a sua opção");
		Scanner scanner = new Scanner(System.in);
		int opcao = Integer.parseInt(scanner.nextLine());
		while(opcao!=0) {
			System.out.println("Qual é o seu título de eleitor?");
			String titulo = scanner.nextLine();
			if(Voto.validaTitulo(titulo)) {
				if(opcao == 1) {
					System.out.println("Qual é o seu voto?");
					int voto = Integer.parseInt(scanner.nextLine());
					Voto votoRegistrado = new Voto(titulo, voto);
					votoRegistrado.exibe();
					System.out.println("Voto registrado");
					listaVotos.add(votoRegistrado);
				}
				if(opcao == 2) {
					System.out.println("Qual é o sua justificativa?");
					String justificativa = scanner.nextLine();
					Justificativa justificativaRegistrada = new Justificativa(titulo, justificativa);
					justificativaRegistrada.exibe();
					System.out.println("Justificativa registrada");
					listaJustificativas.add(justificativaRegistrada);
				}
			}
			else {
				System.out.println("Título inválido");
			}
			
			if(opcao !=1 || opcao!=2 || opcao!=0) {
				System.out.println("Escolha opção correta");
			}
			System.out.println("Qual é a sua opção");
			opcao = Integer.parseInt(scanner.nextLine());
		}
		//Eleição acabada
		contarVotos(listaVotos);
		System.out.println(listaJustificativas.size()+" eleitores justificaram.");
		imprimirVotos();
		definirResultado();
		
	}
	public static void contarVotos(List<Voto> listaVotos) {
		//contar os votos
		for(Voto voto: listaVotos) {
			for(int i = 0; i<candidatos.length; i++) {
				if(voto.getVoto() == candidatos[i]) {
					votosRegistrados[i]++;
				}
			}
		}
	}
	public static void imprimirVotos() {
		//imprimir os votos
		for(int j = 0; j<candidatos.length; j++) {//Porque -1 não é voto válido
			if(candidatos[j]==-1) {
				System.out.println("Houveram "+votosRegistrados[j]+" votos nulos");
			}
			else {
				System.out.println("O candidato "+candidatos[j]+" obteve "+votosRegistrados[j]+" votos");
			}
			
		}
	}
	public static void definirResultado() {
		int somaVotos = 0;
		for (int i = 1; i < candidatos.length; i++) {//Porque -1 não é voto válido
			somaVotos += votosRegistrados[i];
		}
		if (somaVotos == 0) {
			System.out.println("Nenhum voto válido foi registrado.");
			return;
		}
		// Verifica se algum candidato venceu em 1º turno (mais de 50% dos votos)
		for (int j = 1; j < candidatos.length; j++) { //Porque -1 não é voto válido
			if (votosRegistrados[j] > (somaVotos / 2)) {
				System.out.println("O candidato " + candidatos[j] + " venceu a eleição em primeiro turno!");
				return;
			}
		}
		// Se ninguém venceu em 1º turno, define os dois candidatos que vão ao 2º turno
		System.out.println("Haverá segundo turno!");
		int primeiro = -1; // Índice do mais votado
		int segundo = -1;  // Índice do segundo mais votado
		for (int i = 0; i < candidatos.length; i++) {
			if (primeiro == -1 || votosRegistrados[i] > votosRegistrados[primeiro]) {
				segundo = primeiro;
				primeiro = i;
			} else if (segundo == -1 || votosRegistrados[i] > votosRegistrados[segundo]) {
				segundo = i;
			}
		}
		System.out.println("Vão para o 2º turno os candidatos:");
		System.out.println("1º Lugar: Candidato " + candidatos[primeiro] + " com " + votosRegistrados[primeiro] + " votos.");
		System.out.println("2º Lugar: Candidato " + candidatos[segundo] + " com " + votosRegistrados[segundo] + " votos.");
	}


}
