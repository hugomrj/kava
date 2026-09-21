package com.erp.sistema.seguridad.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "Codigo")
    private Integer codigo;

    @Column(name = "CodigoNiveles", insertable = false, updatable = false)
    private Integer codigoNiveles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CodigoNiveles")
    private NivelUsuario nivel;

    @Column(name = "Estado")
    private Integer estado;

    @Column(name = "CI")
    private String ci;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "Celular")
    private String celular;

    @Column(name = "Usuario")
    private String usuario;

    // ✅ CORRECCIÓN: Mapeamos al campo "Clave" de tu base de datos
    @Column(name = "Clave")
    private String clave;

    // --- Getters y Setters ---
    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }

    public Integer getCodigoNiveles() { return codigoNiveles; }
    public void setCodigoNiveles(Integer codigoNiveles) { this.codigoNiveles = codigoNiveles; }

    public NivelUsuario getNivel() { return nivel; }
    public void setNivel(NivelUsuario nivel) { this.nivel = nivel; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}