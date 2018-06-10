package br.com.nayalison.resttest.assinador.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import br.com.nayalison.resttest.assinador.AssinadorHash;
import br.com.nayalison.resttest.exception.AssinaturaException;
import br.com.nayalison.resttest.service.util.FileUtil;

@Component
public class AssinadorDePdf  implements AssinadorHash {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	private static final String MENSAGEM_ERRO = "Falha ao assinar o PDF com o hash";

	@Override
	public void assinar(String hash, File arquivoOriginal) throws AssinaturaException {
		String caminhoArquivoFinal = getDiretorioDestino() + "/" + arquivoOriginal.getName(); 
		 File arquivoFinal = new File(caminhoArquivoFinal);
	     arquivoFinal.getParentFile().mkdirs();
	     try {
			createPdf(arquivoOriginal, arquivoFinal, hash);
		} catch (IOException | DocumentException e) {
			logger.error(MENSAGEM_ERRO, e);
			throw new AssinaturaException(MENSAGEM_ERRO);
		}
	}

	private String getDiretorioDestino() {
		return "/home/nayalison/teste";
	}
	
	public void createPdf(File arquivoOriginal, File arquivoFinal, String hash) throws IOException, DocumentException {
		Document document = new Document(); 

		PdfCopy copy = new PdfCopy(document, new FileOutputStream(arquivoFinal));
		document.open();

		PdfReader reader1 = new PdfReader(arquivoOriginal.getAbsolutePath());
		int n1 = reader1.getNumberOfPages();

		PdfImportedPage page;
		PdfCopy.PageStamp stamp;
		Font ffont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC, BaseColor.BLACK);

		for (int i = 0; i < n1; ) {
		    page = copy.getImportedPage(reader1, ++i);
		    stamp = copy.createPageStamp(page);
		    ColumnText.showTextAligned(stamp.getUnderContent(), Element.ALIGN_CENTER,new Phrase(hash, ffont),297.5f, 28, 0);
		    stamp.alterContents();
		    copy.addPage(page);
		}

		document.close();
		reader1.close();
    }
}
