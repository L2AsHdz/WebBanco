package web.lecturaArchivo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @date 11/11/2020
 * @time 08:20:00
 * @author asael
 */
@WebServlet("/lecturaArchivo")
@MultipartConfig
public class LecturaArchivoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("archivoEntrada");
        String inputFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        List<Part> fileParts = (List<Part>)request.getParts();
        
        fileParts.forEach(part ->{
            String name = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            guardarArchivo(part, name);
        });
        
        guardarArchivo(filePart, inputFileName);
        
        
    }
    
    private void guardarArchivo(Part filePart, String nombreArchivo) {
        File ruta = new File("/tmp");
        File file = new File(ruta, nombreArchivo);

        try ( InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo " + nombreArchivo + " guardado");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    private List<String> leerArchivoXML(String nombreArchivo) {

        //Errores en la lectura del archivo
        List<String> errores = new ArrayList<>();
        try {

            //DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Crear documento
            File xml = new File("/home/asael/uploads/datosEntrada/" + nombreArchivo);
            Document doc = builder.parse(xml);

            /*//Leer etiqueta gerente
            errores.addAll(leerGerente(doc));

            //Leer etiqueta cajero
            errores.addAll(leerCajero(doc));

            //Leer etiqueta cliente
            errores.addAll(leerCliente(doc));

            //Leer etiqueta transaccion
            errores.addAll(leerTransaccion(doc));*/

        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            ex.printStackTrace(System.out);
        }
        return errores;
    }
}
