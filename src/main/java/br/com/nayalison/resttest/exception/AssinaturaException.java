package br.com.nayalison.resttest.exception;

public class AssinaturaException  extends Exception {

	private static final long serialVersionUID = 2143905208145772614L;

	public AssinaturaException(String message) {
		super(message);
	}
	
	public AssinaturaException(String message, Throwable cause){
        super(message, cause);
    }
}
