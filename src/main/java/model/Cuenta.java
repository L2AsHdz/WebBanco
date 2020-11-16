package model;

import model.cliente.Cliente;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @date 10/11/2020
 * @time 21:38:18
 * @author asael
 */
public class Cuenta implements Serializable {

    private int codigo;
    private Cliente cliente;
    private LocalDate fechaCreacion;
    private float saldo;

    public Cuenta() {
    }

    public Cuenta(String codigo) {
        this.codigo = Integer.parseInt(codigo);
    }

    public Cuenta(Cliente cliente, LocalDate fechaCreacion, float saldo) {
        this.cliente = cliente;
        this.fechaCreacion = fechaCreacion;
        this.saldo = saldo;
    }

    public Cuenta(String codigo, Cliente cliente, String fechaCreacion, String saldo) {
        this.codigo = Integer.parseInt(codigo);
        this.cliente = cliente;
        this.saldo = Float.parseFloat(saldo);
        
        try {
            this.fechaCreacion = LocalDate.parse(fechaCreacion);
        } catch (Exception e) {
            try {
                this.fechaCreacion = LocalDate.parse(fechaCreacion, DateTimeFormatter.ofPattern("yyyy-M-d"));
            } catch (Exception ex) {
                try {
                    this.fechaCreacion = LocalDate.parse(fechaCreacion, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
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
