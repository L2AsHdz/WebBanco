package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @date 10/11/2020
 * @time 21:41:55
 * @author asael
 */
public class Transaccion {

    private int codigo;
    private Cuenta cuenta;
    private String tipo;
    private LocalDate fecha;
    private LocalTime hora;
    private float monto;
    private Empleado cajero;
    private float saldoCuenta;

    public Transaccion() {
    }

    public Transaccion(Cuenta cuenta, String tipo, LocalDate fecha, LocalTime hora, float monto, Empleado cajero, float saldoCuenta) {
        this.cuenta = cuenta;
        this.tipo = tipo;
        this.fecha = fecha;
        this.hora = hora;
        this.monto = monto;
        this.cajero = cajero;
        this.saldoCuenta = saldoCuenta;
    }

    public Transaccion(String codigo, Cuenta cuenta, String tipo, String fecha, String hora, String monto, Empleado cajero, String saldoCuenta) {
        this.codigo = Integer.parseInt(codigo);
        this.cuenta = cuenta;
        this.tipo = tipo;
        this.monto = Float.parseFloat(monto);
        this.cajero = cajero;
        this.saldoCuenta = Float.parseFloat(saldoCuenta);
        this.hora = LocalTime.parse(hora);

        try {
            this.fecha = LocalDate.parse(fecha);
        } catch (Exception e) {
            try {
                this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-M-d"));
            } catch (Exception ex) {
                try {
                    this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                } catch (Exception exx) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Empleado getCajero() {
        return cajero;
    }

    public void setCajero(Empleado cajero) {
        this.cajero = cajero;
    }

    public float getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(float saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }
}
