package br.com.nayalison.resttest.model;

public class Resposta {
	
	public String hash;
	
	public String mensagem;

	public Resposta(String hash, String mensagem) {
		super();
		this.hash = hash;
		this.mensagem = mensagem;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	

}
