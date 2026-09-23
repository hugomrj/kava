package com.erp.sistema.seguridad.dto;

public class UsuarioDTO {
    private final String nombreCompleto;
    private final String rol;

    public UsuarioDTO(String nombre, String apellido, String rol) {
        this.nombreCompleto = (nombre + " " + apellido).trim();
        this.rol = rol;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public String getRol() { return rol; }
}