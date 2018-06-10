package br.com.nayalison.resttest.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe utilitária para manipulação de datas.
 * 
 * @author nayalison
 *
 */
public class DateUtil {
	
	/**
	 * Método responsável por formatar uma data no padrão ddMMyyyyHHmmss
	 * @param data data que será formatada
	 * @return data formatada
	 */
	public static String formatarDataHoraMinutosSegundos(Date data) {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		String dataformatada =  format.format(data);
		return dataformatada;
	}

}
