package br.com.nayalison.resttest.service.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import br.com.nayalison.resttest.exception.AssinaturaException;

/**
 * Classe utilitária para manipulação de arquivos.
 * 
 * @author nayalison
 *
 */
public class FileUtil {
	
	private static final String ARQUIVO_NAO_ENCONTRADO = "Não foi possível encontrar o arquivo";
	private static final String FALHA_AO_MANIPULAR_O_ARQUIVO = "Falha ao manipular o arquivo";
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * Método responsável por obter o diretório temporário.
	 * 
	 * @return diretório temporário
	 */
	public static String getTempDir(){
        String property = "java.io.tmpdir";
        String tempDir = System.getProperty(property);
        return tempDir;
    }
	
	/**
	 * Método responsável por copiar o conteúdo de um {@link MultipartFile} para o diretório temporário.
	 * 
	 * @param file conteúdo que será copiado
	 * @param dataFormatada data formatada
	 * @return arquivo copiado para o diretório temporário
	 * @throws AssinaturaException
	 */
	public static final File copiarArquivoParaDiretorioTemporario(MultipartFile file, String dataFormatada) throws AssinaturaException {
		File saveFile = new File(getTempDir(), dataFormatada + "_" + file.getOriginalFilename());
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(saveFile);
			IOUtils.copyLarge(file.getInputStream(), output);
		} catch (FileNotFoundException e) {
			logger.error(ARQUIVO_NAO_ENCONTRADO, e);
			throw new AssinaturaException(ARQUIVO_NAO_ENCONTRADO);
		} catch (IOException e) {
			logger.error(FALHA_AO_MANIPULAR_O_ARQUIVO, e);
			throw new AssinaturaException(FALHA_AO_MANIPULAR_O_ARQUIVO);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					logger.error("Falha ao fechar o recurso.");
				}
			}
		}
		return saveFile;
	}

}
