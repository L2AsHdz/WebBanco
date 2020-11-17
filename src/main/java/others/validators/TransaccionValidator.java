package others.validators;

import datos.CRUD;
import datos.cuenta.CuentaDAOImpl;
import datos.transaccion.TransaccionDAOImpl;
import datos.usuario.UsuarioDAOImpl;
import model.cuenta.Cuenta;
import model.Empleado;
import model.Transaccion;
import model.Usuario;
import others.exceptions.FileInputException;

/**
 * @date 11/11/2020
 * @time 15:17:58
 * @author asael
 */
public class TransaccionValidator extends Validator {

    private static final CRUD<Transaccion> transaccionDAO = TransaccionDAOImpl.getTransaccionDAO();
    private static final CRUD<Cuenta> cuentaDAO = CuentaDAOImpl.getCuentaDAO();
    private static final CRUD<Usuario> usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();

    public static Transaccion validarTransaccion(String codigo, String cuenta, String fecha,
            String hora, String tipo, String monto, String cajero, int i) throws FileInputException {

        Transaccion transaccion = null;
        String etiqueta = "Etiqueta transaccion No. " + (i + 1);
        if (codigo.trim().isEmpty() || cuenta.trim().isEmpty() || fecha.trim().isEmpty()
                || hora.trim().isEmpty() || tipo.trim().isEmpty() || monto.trim().isEmpty()
                || cajero.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isInt(codigo)) {
            throw new FileInputException(etiqueta + ": El codigo debe contener solo numeros");
        } else if (!isFecha(fecha)) {
            throw new FileInputException(etiqueta + ": La fecha no tiene un formato correcto");
        } else if (!isHora(hora)) {
            throw new FileInputException(etiqueta + ": La hora no tiene un formato correcto");
        } else if (!tipo.equalsIgnoreCase("credito") && !tipo.equalsIgnoreCase("debito")) {
            throw new FileInputException(etiqueta + ": El tipo de transaccion ingresado es erroneo");
        } else if (!isFloat(monto) || !isMayorACero(monto)) {
            throw new FileInputException(etiqueta + ": El monto debe ser un numero mayor a cero");
        } else if (!cuentaDAO.exists(cuenta)) {
            throw new FileInputException(etiqueta + ": La cuenta a la que hace referencia no existe");
        } else if (!usuarioDAO.exists(cajero)) {
            throw new FileInputException(etiqueta + ": El cajero al que hace referencia no existe");
        } else if (transaccionDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": La transaccion con codigo " + codigo + " ya existe");
        } else {
            transaccion = new Transaccion(codigo, new Cuenta(cuenta), tipo, fecha, hora, monto, new Empleado(cajero), "0");
        }
        return transaccion;
    }
}
