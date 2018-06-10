package br.com.nayalison.resttest.service;

import java.io.File;
import java.security.NoSuchAlgorithmException;
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
import br.com.nayalison.resttest.model.Resposta;
import br.com.nayalison.resttest.service.util.DateUtil;
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
	public Resposta assinar(@Param("cpf") String cpf, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String mensagem = null;
		String hash = null;
		String ip = null;
		
		try {
			String dataFormatada =  DateUtil.formatarDataHoraMinutosSegundos(new Date());
			ip = getEnderecoIp(request);
			hash = criarHash(cpf, dataFormatada, ip);
			File arquivoTemp = FileUtil.copiarArquivoParaDiretorioTemporario(file, dataFormatada);
			assinador.assinar(hash, arquivoTemp);
			mensagem =  "Arquivo assinado com sucesso!";
		} catch (NoSuchAlgorithmException e) {
			mensagem = "Fala ao gerar o hash do arquivo";
		} catch (AssinaturaException e) {
			mensagem = e.getMessage();
		}
		
		return new Resposta(hash, mensagem);
	}

	private String criarHash(String cpf, String dataFormatada, String ip) throws NoSuchAlgorithmException {
		return HashUtil.gerarHashMD5(cpf + dataFormatada + ip);
	}

	private String getEnderecoIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
			ipAddress = request.getRemoteAddr();  
		}
		return ipAddress;
	}

}
