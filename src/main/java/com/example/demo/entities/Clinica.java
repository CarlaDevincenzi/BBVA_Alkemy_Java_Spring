package com.example.demo.entities;

public class Clinica {
	
	private long clinicaId;
	private String nombre;
	private String direccion;
	private String telefono;
	
	public long getClinicaId() {
		return clinicaId;
	}
	public void setClinicaId(long clinicaId) {
		this.clinicaId = clinicaId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
