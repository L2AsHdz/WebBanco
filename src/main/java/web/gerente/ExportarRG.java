package web.gerente;

import datos.CRUD;
import datos.Conexion;
import datos.cambioRealizado.CambioRealizadoDAOImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import model.CambioRealizado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 * @date 17/11/2020
 * @time 03:56:39
 * @author asael
 */
@WebServlet("/exportarRG")
public class ExportarRG extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=HistorialCambios.pdf");

            String imageDir = request.getServletContext().getRealPath("/resources/");

            InputStream inputStream = new FileInputStream(imageDir+"Gerente1.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

            Map<String, Object> params = new HashMap<>();
            params.put("imagesDir", imageDir);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, Conexion.getConexion());
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException | JRException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
