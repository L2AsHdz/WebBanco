package others.validators;

import datos.CRUD;
import datos.cuenta.CuentaDAOImpl;
import model.Cliente;
import model.Cuenta;
import others.exceptions.FileInputException;

/**
 * @date 11/11/2020
 * @time 15:02:58
 * @author asael
 */
public class CuentaValidator extends Validator {

    private static final CRUD<Cuenta> cuentaDAO = CuentaDAOImpl.getCuentaDAO();
    
    public static Cuenta validarCuenta(String codigo, String codCliente, String fechaC, 
            String saldo, int i, int j) throws FileInputException {
        
        Cuenta cuenta = null;
        String etiqueta = "La cuenta " + (j + 1) + " de la etiqueta cliente No. " + (i+1);
        if (codigo.trim().isEmpty() || fechaC.trim().isEmpty() || saldo.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isInt(codigo)) {
            throw new FileInputException(etiqueta + ": El codigo debe contener solo numeros");
        } else if (!isFecha(fechaC)) {
            throw new FileInputException(etiqueta + ": La fecha no tiene un formato correcto");
        } else if (!isMayorACero(saldo) || !isFloat(saldo)) {
            throw new FileInputException(etiqueta + ": El saldo debe ser un dato numerico mayor a cero");
        } else if (cuentaDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": La cuenta ya esta registrada");
        } else {
            cuenta = new Cuenta(codigo, new Cliente(codCliente), fechaC, saldo);
        }
        
        return cuenta;
    }
}
