package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @date 10/11/2020
 * @time 21:35:05
 * @author asael
 */
public class Turno implements Serializable {

    private int id;
    private String nombre;
    private LocalDate horaEntrada;
    private LocalDate horaSalida;

    public Turno() {
    }

    public Turno(String nombre) {
        this.id = nombre.equalsIgnoreCase("MATUTINO") ? 1 : 2;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDate horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDate getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDate horaSalida) {
        this.horaSalida = horaSalida;
    }
}
