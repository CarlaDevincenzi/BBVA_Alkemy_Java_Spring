package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Clinica;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Paciente;
import com.example.demo.repositories.ClinicaRepository;
import com.example.demo.repositories.MedicoRepository;
import com.example.demo.repositories.PacienteRepository;

@Service
public class ClinicaServiceImpl implements ClinicaService {
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;

	/**
	 * Usado para crear un nuevo paciente en la tabla de pacientes
	 * @param nuevoPaciente
	 * @return
	 */

	public Paciente guardarPaciente(Paciente nuevoPaciente) {
		return pacienteRepository.save(nuevoPaciente);

	}

	/**
	 * Usado para crear un nuevo medico en la tabla de medicos
	 * @param nuevoMedico
	 * @return
	 */
	public Medico guardarMedico(Medico nuevoMedico) {
		return medicoRepository.save(nuevoMedico);

	}

	/**
	 * Usado para crear una nueva clinica en la tabla de clinicas
	 * @param nuevaClinica
	 * @return
	 */
	public Clinica guardarClinica(Clinica nuevaClinica) {
		return clinicaRepository.save(nuevaClinica);

	}

	/**
	 * Usado para obtener una lista de pacientes de la tabla de pacientes
	 * @return Lista de pacientes
	 */
	public List<Paciente> obtenerPacientes() {
		return pacienteRepository.findAll();
	}

	/**
	 * Usado para obtener una lista de medicos de la tabla de medicos
	 * @return Lista de medicos
	 */
	public List<Medico> obtenerMedicos() {
		return medicoRepository.findAll();
	}

	/**
	 * Usado para obtener una lista de clinicas de la tabla de clinicas
	 * @return Lista de clinicas
	 */
	public List<Clinica> obtenerClinicas() {
		return clinicaRepository.findAll();
	}

}
