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
import static web.lecturaArchivo.LecturaCliente.leerCliente;
import static web.lecturaArchivo.LecturaEmpleado.leerEmpleado;
import static web.lecturaArchivo.LecturaTransaccion.leerTransaccion;

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

    //Procesa la solicitud HTML ya sea desde un metodo POST o GET
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("archivoEntrada");
        List<Part> fileParts = (List<Part>)request.getParts();
        
        //Guarda los archivos en el servidor
        fileParts.forEach(part ->{
            String name = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            guardarArchivo(part, name);
        });
        
        //Leer datos del archivo XML
        List<String> errores = leerArchivoXML(filePart.getInputStream());
        
        if (errores.isEmpty()) {
            request.setAttribute("nice", "La lectura del archivo finalizo sin ningun error");
        } else {
            request.setAttribute("errores", errores);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
    
    private List<String> leerArchivoXML(InputStream fileIS) {

        //Errores en la lectura del archivo
        List<String> errores = new ArrayList<>();
        try {

            //DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Crear documento
            Document doc = builder.parse(fileIS);

            //Leer etiqueta gerente
            errores.addAll(leerEmpleado(doc, "GERENTE"));

            //Leer etiqueta cajero
            errores.addAll(leerEmpleado(doc, "CAJERO"));

            //Leer etiqueta cliente con sus cuentas
            errores.addAll(leerCliente(doc));

            //Leer etiqueta transaccion
            errores.addAll(leerTransaccion(doc));

        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            ex.printStackTrace(System.out);
        }
        return errores;
    }
}
