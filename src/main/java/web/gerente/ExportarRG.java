package web.gerente;

import datos.Conexion;
import datos.limite.LimiteDAO;
import datos.limite.LimiteDAOImpl;
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
import model.CajeroDTO;
import model.Empleado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @date 17/11/2020
 * @time 03:56:39
 * @author asael
 */
@WebServlet("/exportarRG")
public class ExportarRG extends HttpServlet {

    private final LimiteDAO limiteDAO = LimiteDAOImpl.getLimiteDAO();
    
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
            case "r1" -> {
                exportar(request, response, "Gerente1", params, imageDir, "HistorialCambios");
            }
            case "r2" -> {
                Float limite = limiteDAO.getObject("1").getValor();
                params.put("limite", limite.toString());
                exportar(request, response, "Gerente2", params, imageDir, "Reporte2-Gerente");
            }
            case "r3" -> {
                Float limite = limiteDAO.getObject("2").getValor();
                params.put("limite", limite.toString());
                exportar(request, response, "Gerente3", params, imageDir, "Reporte3-Gerente");
            }
            case "r4" -> {
                exportar(request, response, "Gerente4", params, imageDir, "Reporte4-Gerente");
            }
            case "r7" -> {
                CajeroDTO cajero = (CajeroDTO) request.getSession().getAttribute("cajeroR");
                params.put("codigo", cajero.getCodigo());
                params.put("nombre", cajero.getNombre());
                params.put("direccion", cajero.getDireccion());
                params.put("identificacion", cajero.getNoIdentificacion());
                params.put("turno", cajero.getTurno().getNombre());
                params.put("total", cajero.getTotalTransacciones());
                exportar(request, response, "Gerente7", params, imageDir, "Reporte7-Gerente");
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
