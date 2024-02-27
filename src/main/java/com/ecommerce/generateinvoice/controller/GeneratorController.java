package com.ecommerce.generateinvoice.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.ecommerce.generateinvoice.dto.TransactionDto;
import com.ecommerce.generateinvoice.service.PdfConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;

@Controller
public class GeneratorController {

	@Autowired
	private SpringTemplateEngine templateEngine;

	@KafkaListener(topics = "invoice-report-result", groupId = "generate-consumer-group")
	public void listen(ConsumerRecord<String, String> record) {
		System.out.println("Received Message: " + record.value());

		ObjectMapper objectMapper = new ObjectMapper();
		TransactionDto transactionDto = new TransactionDto();
		try {
			transactionDto = objectMapper.readValue(record.value(), TransactionDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String processedHtml = processTemplate("invoice", transactionDto);
		generateAndSavePdf(processedHtml, transactionDto);
	}

	private String processTemplate(String templateName, TransactionDto transactionDto) {
	    Map<String, Object> model = new HashMap<>();
	    model.put("transactionDto", transactionDto);
	    
	    Context context = new Context(Locale.getDefault(), model);
	    String processedHtml = templateEngine.process(templateName, context);

	    return processedHtml;
	}
	
	public File generateAndSavePdf(String html, TransactionDto transactionDto) {
		String pdfFileName = "Invoice ID " + transactionDto.getId() + ".pdf";
		String downloadsDirectory = System.getProperty("user.home") + "/Downloads/";
		String pdfFilePath = downloadsDirectory + pdfFileName;

		File downloadsDir = new File(downloadsDirectory);
		if (!downloadsDir.exists()) {
			downloadsDir.mkdirs();
		}

		try {
			PdfConverter.convertHtmlToPdf(html, pdfFilePath);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new File(pdfFilePath);
	}
}
