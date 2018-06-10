package br.com.nayalison.resttest.service;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.nayalison.resttest.assinador.AssinadorHash;
import br.com.nayalison.resttest.exception.AssinaturaException;
import br.com.nayalison.resttest.service.util.FileUtil;
import br.com.nayalison.resttest.service.util.HashUtil;

@RestController
@RequestMapping(value="/assinador")
public class AssinadorDePdfService {
	
	@Autowired
	private AssinadorHash assinador;
	
	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "Service started";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String assinar(@Param("cpf") String cpf, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String mensagem = null;
		String ip = null;
		
		try {
			ip = getEnderecoIp(request);
			String hash = criarHash(cpf, new Date(), ip);
			File arquivoTemp = FileUtil.copiarArquivoParaDiretorioTemporario(file);
			assinador.assinar(hash, arquivoTemp);
			mensagem =  hash;
		} catch (NoSuchAlgorithmException e) {
			mensagem = "Fala ao gerar o hash do arquivo";
		} catch (AssinaturaException e) {
			mensagem = e.getMessage();
		}
		
		return mensagem;
	}

	private String criarHash(String cpf, Date data, String ip) throws NoSuchAlgorithmException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		String dataformatada =  format.format(data);
		return HashUtil.gerarHashMD5(cpf+dataformatada+ip);
	}

	private String getEnderecoIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
			ipAddress = request.getRemoteAddr();  
		}
		return ipAddress;
	}

}
