package com.webApp14.UniHub.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class PdfController {

    // Method to allow a user to download content from the page itself
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPdf() throws IOException, DocumentException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Aquí estaría cargado el material para la asignatura", font);
        document.add(chunk);

        document.close();
        byte[] pdfBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition", "attachment; filename=material.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}