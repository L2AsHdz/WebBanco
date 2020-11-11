package others.validators;

import model.Empleado;
import model.Turno;
import others.exceptions.FileInputException;

/**
 * @date 11/11/2020
 * @time 14:38:56
 * @author asael
 */
public class EmpleadoValidator extends Validator {

    /**
     * Valida los datos del empleado obtenidos del archivo de entrada, si no hay
     * errores devuelve un objeto Empleado
     * @param codigo
     * @param turno
     * @param tipoUser
     * @param i
     * @return Empleado
     * @throws FileInputException
     */
    public static Empleado validarEmpleado(String codigo, String turno, String tipoUser, int i) throws FileInputException {
        
        Empleado empleado = null;
        String etiqueta = "Etiqueta " + tipoUser + " No. " + (i + 1);
        if (turno.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!turno.equalsIgnoreCase("MATUTINO") && !turno.equalsIgnoreCase("VESPERTINO")) {
            throw new FileInputException(etiqueta + ": El turno ingresado no existe");
        } else {
            empleado = new Empleado(new Turno(turno), codigo);
        }
        return empleado;
    }
}
