package com.resumeanalyser.resumeanalyser.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class ResumeService {

    private final GPTService gptService;

    public ResumeService(GPTService gptService) {
        this.gptService = gptService;
    }

    public String processResume(MultipartFile file) throws IOException {
        String text = extractTextFromFile(file);
        return gptService.getResumeFeedback(text);
    }

    private String extractTextFromFile(MultipartFile file) throws IOException {
        if ("application/pdf".equals(file.getContentType())) {
            try (PDDocument document = PDDocument.load(file.getInputStream())) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                return pdfStripper.getText(document);
            }
        } else {
            return new String(file.getBytes(), StandardCharsets.UTF_8);
        }
    }
}