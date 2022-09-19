package com.example.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clinicas")
public class Clinica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long clinicaId;
	private String nombre;
	private String direccion;
	private String telefono;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicaDondeTrabaja")
	private List<Medico> medicos;
	
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

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
}
