package datos.cambioRealizado;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.CambioRealizado;
import model.Empleado;
import model.Usuario;

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
        String sql = "SELECT u2.nombre gerente, c.*, u.tipoUsuario, u.nombre usuario FROM "
                + "cambioRealizado c INNER JOIN usuario u ON c.codUsuarioModificado=u.codigo "
                + "inner join usuario u2 on c.codGerente=u2.codigo";
        List<CambioRealizado> cambios = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  
                ResultSet rs = declaracion.executeQuery()) {
            cambios = new ArrayList();

            while (rs.next()) {
                Empleado gerente = new Empleado();
                gerente.setCodigo(rs.getInt("codGerente"));
                gerente.setNombre(rs.getString("gerente"));
                gerente.setTipoUsuario(1);
                
                Usuario usuario = new Usuario();
                usuario.setCodigo(rs.getInt("codUsuarioModificado"));
                usuario.setNombre(rs.getString("usuario"));
                usuario.setTipoUsuario(rs.getInt("tipoUsuario"));
                
                CambioRealizado cambio = new CambioRealizado(
                        gerente,
                        usuario,
                        LocalDate.parse(rs.getString("fecha")),
                        LocalTime.parse(rs.getString("hora")));
                cambios.add(cambio);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return cambios;
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
