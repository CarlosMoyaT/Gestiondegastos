package org.example;
import com.opencsv.CSVWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class ExportadorDatos {

    public static void exportarCSV(List<Gasto> gastos, String rutaArchivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivo))) {
            writer.writeNext(new String[]{"Cantidad", "Fecha", "Descripción", "Categoría"});

            for (Gasto gasto : gastos) {
            writer.writeNext(new String[]{
                    String.valueOf(gasto.getCantidad()),
                    gasto.getFecha().toString(),
                    gasto.getDescripcion(),
                    gasto.getCategoria()
            });
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

   public static void exportarPDF(List<Gasto> gastos, String rutaArchivo) {
    try (PDDocument document = new PDDocument()) {
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.COURIER_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(20, 700);
            contentStream.showText("Cantidad\tFecha\tDescripción\tCategoría");
            contentStream.newLineAtOffset(0, -15);

            for (Gasto gasto : gastos) {
                String data = String.format("%s\t%s\t%s\t%s",
                        gasto.getCantidad(),
                        gasto.getFecha().toString(),
                        gasto.getDescripcion(),
                        gasto.getCategoria());
                contentStream.showText(data);
                contentStream.newLineAtOffset(0, -15);
            }

            contentStream.endText();
        }

        document.save(rutaArchivo);
    } catch (IOException e) {
        e.printStackTrace();
    }
   }
}