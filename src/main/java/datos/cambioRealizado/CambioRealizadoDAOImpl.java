package datos.cambioRealizado;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.CambioRealizado;

/**
 * @date 13/11/2020
 * @time 14:19:50
 * @author asael
 */
public class CambioRealizadoDAOImpl implements CambioRealizadoDAO {
    
    private static CambioRealizadoDAOImpl cambioDAO = null;
    private final Connection conexion = Conexion.getConexion();
    
    private CambioRealizadoDAOImpl() {
    }
    
    public static CambioRealizadoDAOImpl getCambioDAO() {
        if (cambioDAO == null) {
            cambioDAO = new CambioRealizadoDAOImpl();
        }
        
        return cambioDAO;
    }

    @Override
    public List<CambioRealizado> getList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(CambioRealizado cambio) {
        String sql = "INSERT INTO cambioRealizado(codGerente, codUsuarioModificado, "
                + "fecha, hora) VALUES (?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cambio.getGerente().getCodigo());
            ps.setInt(2, cambio.getUsuarioModificado().getCodigo());
            ps.setString(3, cambio.getFecha().toString());
            ps.setString(4, cambio.getHora().toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public CambioRealizado getObject(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(CambioRealizado t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean exists(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
