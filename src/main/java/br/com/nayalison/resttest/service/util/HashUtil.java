package br.com.nayalison.resttest.service.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
	
	public static String gerarHashMD5(String input) throws NoSuchAlgorithmException {
        return gerarHash(input, "MD5", 32);
    }
	
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
