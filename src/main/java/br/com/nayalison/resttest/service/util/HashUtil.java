package br.com.nayalison.resttest.service.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Classe utilitária responsável pela geração de hash de textos.
 * 
 * @author nayalison
 *
 */
public class HashUtil {
	
	/**
	 * Método responsável por gerar o hash de uma {@link String} utilizando o algoritmo MD5
	 * 
	 * @param input Texto do qual o hash será gerado
	 * @return hash MD5
	 * @throws NoSuchAlgorithmException
	 */
	public static String gerarHashMD5(String input) throws NoSuchAlgorithmException {
        return gerarHash(input, "MD5", 32);
    }
	
	/**
	 * Método responsável por gerar o hash de uma {@link String} utilizando o algoritmo SHA
	 * 
	 * @param input Texto do qual o hash será gerado
	 * @return hash MD5
	 * @throws NoSuchAlgorithmException
	 */
	public static String gerarHashSHA1(String input) throws NoSuchAlgorithmException {
        return gerarHash(input, "SHA-1", 40);
    }

	private static String gerarHash(String texto, String algoritmo, int tamanhoMinimo) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algoritmo);
        byte[] bytes = messageDigest.digest(texto.getBytes());
        BigInteger integer = new BigInteger(1, bytes);
        String result = integer.toString(16);
        while (result.length() < tamanhoMinimo) {
            result = "0" + result;
        }
        return result;
    }
}
