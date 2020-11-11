package model;

import java.time.LocalDate;

/**
 * @date 10/11/2020
 * @time 21:38:18
 * @author asael
 */
public class Cuenta {

    private String codigo;
    private Cliente cliente;
    private LocalDate fechaCreacion;
    private float saldo;

    public Cuenta() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
