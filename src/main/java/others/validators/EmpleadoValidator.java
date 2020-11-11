package others.validators;

import datos.CRUD;
import datos.gerente.EmpleadoDAOImpl;
import model.Empleado;
import others.exceptions.FileInputException;

/**
 * @date 11/11/2020
 * @time 14:38:56
 * @author asael
 */
public class EmpleadoValidator extends Validator {

    private static final CRUD<Empleado> empleadoDAO = EmpleadoDAOImpl.getEmpleadoDAO();

    public static void validarEmpleado(String turno, String tipoUser, int i) throws FileInputException {
        
        String etiqueta = "Etiqueta " + tipoUser + " No. " + (i + 1);
        if (turno.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!turno.equalsIgnoreCase("MATUTINO") && !turno.equalsIgnoreCase("VESPERTINO")) {
            throw new FileInputException(etiqueta + ": El turno ingresado no existe");
        }
    }
}
