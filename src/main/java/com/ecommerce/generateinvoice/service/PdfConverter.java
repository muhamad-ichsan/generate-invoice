package com.ecommerce.generateinvoice.service;

import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfConverter {

	public static void convertHtmlToPdf(String html, String pdfFilePath) throws IOException, DocumentException {
		try (FileOutputStream fos = new FileOutputStream(pdfFilePath)) {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(html);
			renderer.layout();
			renderer.createPDF(fos);
		}
	}
}