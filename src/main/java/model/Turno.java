package model;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * @date 10/11/2020
 * @time 21:35:05
 * @author asael
 */
public class Turno implements Serializable {

    private int id;
    private String nombre;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    public Turno() {
    }

    public Turno(String nombre) {
        this.id = nombre.equalsIgnoreCase("MATUTINO") ? 1 : 2;
        this.nombre = nombre;
    }

    public Turno(String id, String horaEntrada, String horaSalida) {
        this.id = Integer.parseInt(id);
        this.horaEntrada = LocalTime.parse(horaEntrada);
        this.horaSalida = LocalTime.parse(horaSalida);
    }

    public Turno(int id, String nombre, String horaEntrada, String horaSalida) {
        this.id = id;
        this.nombre = nombre;
        this.horaEntrada = LocalTime.parse(horaEntrada);
        this.horaSalida = LocalTime.parse(horaSalida);
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

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }
}
