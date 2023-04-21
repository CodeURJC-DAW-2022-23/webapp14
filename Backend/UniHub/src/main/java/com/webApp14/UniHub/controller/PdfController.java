package com.webApp14.UniHub.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RestController
public class PdfController {

    @Autowired
     private PackRepository packRepository;
    // Method to allow a user to download content from the page itself
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable ("id") Long id) throws IOException, DocumentException {

        Pack pack = packRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pack id"));
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        writer.setInitialLeading(12.5f);
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        writer.setViewerPreferences(PdfWriter.HideToolbar);
        writer.setViewerPreferences(PdfWriter.HideMenubar);
        writer.setViewerPreferences(PdfWriter.HideWindowUI);
        writer.setViewerPreferences(PdfWriter.CenterWindow);
        writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
        document.addTitle(pack.getPackTitle());

        document.open();

        document.addLanguage("es-ES");
        document.add(new Paragraph(pack.getPackTitle(), FontFactory.getFont(FontFactory.TIMES_BOLD, 22)));
        document.add(new Paragraph(pack.getpackDescriptionLong(), FontFactory.getFont(FontFactory.TIMES, 16, BaseColor.BLACK)));
        document.close();

        byte[] pdfBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition", "attachment; filename=material.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}