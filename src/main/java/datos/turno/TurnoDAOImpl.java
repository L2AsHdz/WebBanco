package datos.turno;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Turno;

/**
 * @date 13/11/2020
 * @time 09:53:58
 * @author asael
 */
public class TurnoDAOImpl implements TurnoDAO {
    
    private static TurnoDAOImpl turnoDAO = null;
    private final Connection conexion = Conexion.getConexion();
    
    private TurnoDAOImpl() {
    }
    
    public static TurnoDAOImpl getTurnoDAO() {
        if (turnoDAO == null) {
            turnoDAO = new TurnoDAOImpl();
        }
        
        return turnoDAO;
    }

    @Override
    public List<Turno> getList() {
        String sql = "SELECT * FROM turno LIMIT 2";
        List<Turno> turnos = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  
                ResultSet rs = declaracion.executeQuery()) {
            turnos = new ArrayList();

            while (rs.next()) {
                Turno turno = new Turno(
                        rs.getInt("id"), 
                        rs.getString("nombre"), 
                        rs.getString("horaEntrada"), 
                        rs.getString("horaSalida"));
                turnos.add(turno);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return turnos;
    }

    @Override
    public void create(Turno t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Turno getObject(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Turno turno) {
        String sql = "UPDATE turno SET horaEntrada = ?, horaSalida = ? WHERE id = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, turno.getHoraEntrada().toString());
            ps.setString(2, turno.getHoraSalida().toString());
            ps.setInt(3, turno.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
