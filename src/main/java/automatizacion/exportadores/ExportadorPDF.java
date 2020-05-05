package automatizacion.exportadores;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExportadorPDF {
    public void exportar(List<String> datos, String rutaCompletaDelArchivo) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            // add page to the PDF document
            document.addPage(page);
            // For writing to a page content stream
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            //Begin the Content stream
            contentStream.beginText();

            //Setting the font to the Content stream
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            //Setting the position for the line
            contentStream.newLineAtOffset(25, 750);

            for(String dato : datos)
            {
                contentStream.showText(dato);
                contentStream.newLineAtOffset(0, -25);
            }

            //Ending the content stream
            contentStream.endText();

            //Closing the content stream
            contentStream.close();

            //Saving the document
            document.save(new File(rutaCompletaDelArchivo));

            //Closing the document
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
