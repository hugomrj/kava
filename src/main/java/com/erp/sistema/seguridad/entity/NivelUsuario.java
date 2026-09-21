package com.erp.sistema.seguridad.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "nivelesusuarios")
public class NivelUsuario {

    @Id
    @Column(name = "Codigo")
    private Integer codigo;

    @Column(name = "Descripcion")
    private String descripcion;

    public NivelUsuario() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}