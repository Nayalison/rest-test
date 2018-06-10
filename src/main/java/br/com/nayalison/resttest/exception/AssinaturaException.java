package br.com.nayalison.resttest.exception;

/**
 * Exception que representa os erros que podem ocorrer durante o processo de assinatura de um arquivo.
 *  
 * @author nayalison
 *
 */
public class AssinaturaException  extends Exception {

	private static final long serialVersionUID = 2143905208145772614L;

	public AssinaturaException(String message) {
		super(message);
	}
	
	public AssinaturaException(String message, Throwable cause){
        super(message, cause);
    }
}
