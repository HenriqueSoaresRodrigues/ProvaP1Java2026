package tse;

public class Voto implements Eleicao {
	private String titulo;
	private int voto;
	public String getTitulo() {
		return titulo;
	}
	public Voto(String titulo, int voto) {
		this.setTitulo(titulo);
		this.setVoto(voto);
	}
	public void setTitulo(String titulo) {
		if(validaTitulo(titulo)) {
			this.titulo = ofuscaTitulo(titulo);
		}
	}
	public int getVoto() {
		return voto;
	}
	public void setVoto(int voto) {
		if(voto >= 1 && voto <= 3) {
			this.voto = voto;
		}
		else{
			this.voto = -1;

		}
	}
	public void exibe() {
		System.out.println("Título de Eleitor: "+ titulo);
		System.out.println("Número do Candidato: "+ voto);

	}
	public static boolean validaTitulo(String titulo) {
		//Verifica extensão do título.
		if(titulo.length()!=12) {
			return false;
		}
		//Verifica se há letras no título
		for(int i = 0; i<= titulo.length()-1; i++) {
			if(titulo.charAt(i)<'0'||titulo.charAt(i)>'9') {
				return false;
			}
		}
		//Verifica código da UF
		int codigoUF = Integer.parseInt(titulo.substring(8, 10));
		if(codigoUF < 1 || codigoUF > 28) {
			return false;
		}
		//Verifica dígitos de verificação
		int codigoVerificacao = Integer.parseInt(titulo.substring(10, 12));
		int somaDigitos = 0;
		for(int j = 0; j < 10; j++) {
			somaDigitos += titulo.charAt(j)-'0'; //Para pegar o valor real em vez do valor da tabela ASCII
		}
		if((somaDigitos%11)!=codigoVerificacao) {
			return false;
		}
		return true;
	}
	public static String ofuscaTitulo(String titulo) {
		return "********"+titulo.substring(8, 12);
	}

}
