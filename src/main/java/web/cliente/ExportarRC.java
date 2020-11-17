package web.cliente;

import datos.Conexion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @date 17/11/2020
 * @time 10:00:12
 * @author asael
 */
@WebServlet("/exportarRC")
public class ExportarRC extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reporte = request.getParameter("reporte");

        response.setContentType("application/pdf");

        String imageDir = request.getServletContext().getRealPath("/resources/");
        Map<String, Object> params = new HashMap<>();
        params.put("imagesDir", imageDir);

        switch (reporte) {
            case "r3" -> {
                int codCuenta = Integer.parseInt(request.getParameter("codCuenta"));
                String fechaInicial = request.getParameter("fecha");
                params.put("codCuenta", codCuenta);
                params.put("fechaInicial", fechaInicial);
                exportar(request, response, "Cliente3", params, imageDir, "Reporte3-Cliente");
            }
        }
    }
    
    private void exportar(HttpServletRequest request, HttpServletResponse response,
            String nameReporte, Map<String, Object> params, String imageDir, String namePDF) throws ServletException, IOException {
        
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+namePDF+".pdf");
        
        try {

            InputStream inputStream = new FileInputStream(imageDir + nameReporte + ".jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, Conexion.getConexion());
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException | JRException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
