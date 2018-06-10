package br.com.nayalison.resttest.assinador;

import java.io.File;

import br.com.nayalison.resttest.exception.AssinaturaException;

/**
 * Abstração de classes capazes de assinar uma arquivo com um hash.
 * 
 * @author nayalison
 *
 */
public interface AssinadorHash {
	
	/**
	 * Métod responsável por assinar um documento com o hash em seu rodapé.
	 * 
	 * @param hash Hash para a assinatura
	 * @param arquivo arquivo que será assinado
	 * @throws AssinaturaException
	 */
	public void assinar(String hash, File arquivo) throws AssinaturaException;

}
