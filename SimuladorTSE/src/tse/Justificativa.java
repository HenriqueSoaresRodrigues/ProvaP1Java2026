package tse;

public class Justificativa implements Eleicao{
	private String titulo;
	private String justificativa;
	public Justificativa(String titulo, String justificativa) {
		this.titulo = Voto.ofuscaTitulo(titulo);
		this.setJustificativa(justificativa);
	
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		if(Voto.validaTitulo(titulo)) {
			this.titulo = Voto.ofuscaTitulo(titulo);
		}
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public void exibe() {
		System.out.println("Título de Eleitor: "+ titulo);
		System.out.println("Justificativa: "+ justificativa);
	}
	
}
