package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.example.demo.entities.*;
<<<<<<< Updated upstream
=======
import com.example.demo.model.PacienteConverter;
import com.example.demo.services.imple.ClinicaService;
import org.springframework.beans.BeanUtils;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.ClinicaRepository;
import com.example.demo.repositories.MedicoRepository;
import com.example.demo.repositories.PacienteRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class ClinicaServiceImpl implements ClinicaService {
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;

<<<<<<< Updated upstream
=======
	@Autowired
	private PacienteRepository pacienteRepository;
	



>>>>>>> Stashed changes
	/**
	 * Usado para crear un nuevo paciente en la tabla de pacientes
	 * @param nuevoPaciente
	 * @return
	 */

<<<<<<< Updated upstream
	public Paciente guardarPaciente(Paciente nuevoPaciente) {
		return pacienteRepository.save(nuevoPaciente);

	}

=======





>>>>>>> Stashed changes
	/**
	 * Usado para crear un nuevo medico en la tabla de medicos
	 * @param nuevoMedico
	 * @return
	 */

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
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


	/**
	 * Usado para obtener una lista de medicos de la tabla de medicos
	 * @return Lista de medicos
	 */


	/**
	 * Usado para obtener una lista de clinicas de la tabla de clinicas
	 * @return Lista de clinicas
	 */
	public List<Clinica> obtenerClinicas() {
		return clinicaRepository.findAll();
	}


	public Clinica getClinicaById(long clinicaId){
		Optional<Clinica> clinica = obtenerClinicas().stream()
				.filter(clinica1 -> clinica1.getClinicaId() == clinicaId)
				.findFirst();

		return clinica.isPresent() ? clinica.get() : null;
	}



	@Override
	public Clinica getCLinicaById(Long clinicaId) {
		return clinicaRepository.findById(clinicaId).orElse(null);

	}


<<<<<<< Updated upstream
	@Override
	public List<Medico> medicosQueTrabajanDiasNoLaborablesClinica(Long clinicaId) {
		Clinica clinica = getCLinicaById(clinicaId);
		List<Medico> medicos = medicoRepository.findAll();
		List<Medico> medicoList = new ArrayList<>();

		medicos.stream()
				.filter(medico -> medico.getClinicaDondeTrabaja().equals(clinica) &&
						medico.isTrabajaFinesSemanasYFeriados())
				.forEach(medicoList::add);
		return medicoList;
	}

	@Override
	public Paciente updatePaciente(Paciente paciente, Long id){
		Optional<Paciente> optPaciente = pacienteRepository.findById(id);
		Paciente p;
		if (optPaciente.isPresent()){
			p = optPaciente.get();
			p.setNombre(paciente.getNombre());
			p.setApellido(paciente.getApellido());
			p.setEmail(paciente.getEmail());
			p.setEdad(paciente.getEdad());
			p.setTelefono(paciente.getTelefono());
			p.setFechaNacimiento(paciente.getFechaNacimiento());
			p.setDni(paciente.getDni());
			p.setFechaTurnoConMedico(paciente.getFechaTurnoConMedico());
			p = pacienteRepository.save(p);

			return p;
		}
		return null;

	}


=======
>>>>>>> Stashed changes
	public void eliminarClinica (Long idClinica) {
		if (clinicaRepository.existsById(idClinica)) {
			clinicaRepository.deleteById(idClinica);
		}
		else {
			throw new EntityNotFoundException();
		}
	}



}
