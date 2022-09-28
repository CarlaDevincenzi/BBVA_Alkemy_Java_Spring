package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.ClinicaDto;
import com.example.demo.dto.MedicoDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.entities.*;
import com.example.demo.model.PacienteConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
	
	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	PacienteConverter pacienteConverter;



	/**
	 * Usado para crear un nuevo paciente en la tabla de pacientes
	 * @param nuevoPaciente
	 * @return
	 */

	public Paciente guardarPaciente(Paciente nuevoPaciente) {
		return pacienteRepository.save(nuevoPaciente);
	}

	/*Prueba con BeanUtils*/
	public PacienteDto addPaciente(PacienteDto pacienteDto){
		Paciente pacienteEntity = new Paciente();
		BeanUtils.copyProperties(pacienteDto, pacienteEntity); // dto recibe todos los campos del entity
		pacienteEntity = pacienteRepository.save(pacienteEntity);
		BeanUtils.copyProperties(pacienteEntity, pacienteDto); // convierte entidad en el dto
		return  pacienteDto;
	}

	/*Prueba con Model Mapper*/
	public PacienteDto  savePaciente(PacienteDto pacienteDto) {
		Paciente paciente = pacienteConverter.convertDtoToEntity(pacienteDto);
		paciente = pacienteRepository.save(paciente);
		return pacienteConverter.converteEntityToDto(paciente);

	}



	/**
	 * Usado para crear un nuevo medico en la tabla de medicos
	 * @param nuevoMedico
	 * @return
	 */
	public Medico guardarMedico(Medico nuevoMedico) {
		return medicoRepository.save(nuevoMedico);

	}

	public MedicoDto addMedico(MedicoDto medicoDto){
		Medico medico = new Medico();
		BeanUtils.copyProperties(medicoDto, medico);
		medico = medicoRepository.save(medico);
		BeanUtils.copyProperties(medico, medicoDto);
		return medicoDto;
	}

	/**
	 * Usado para crear una nueva clinica en la tabla de clinicas
	 * @param nuevaClinica
	 * @return
	 */
	public Clinica guardarClinica(Clinica nuevaClinica) {
		return clinicaRepository.save(nuevaClinica);
	}

	@Override
	public ClinicaDto addClinica(ClinicaDto clinicadto) {
		Clinica clinica = new Clinica();
		BeanUtils.copyProperties(clinicadto, clinica);
		clinica = clinicaRepository.save(clinica);
		BeanUtils.copyProperties(clinica,clinicadto);

		return clinicadto;
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

	@Override
	public List<Paciente> obtenerPacienteFechaDelMedico(Long id, Date fecha) {
		List<Medico> medicos = obtenerMedicos();
		Medico med = null;
		for (Medico medico : medicos){
			if(medico.getMedicoId() == id){
				med = medico;
				break;
			}
		}
		List<Paciente> pacientesFecha = new ArrayList<>();
		for (Paciente pacte : med.getPacientes()){
			if(pacte.getFechaTurnoConMedico().equals(fecha)){
				pacientesFecha.add(pacte);
			}
		}

		return pacientesFecha;
	}

	@Override
	public List<Medico> obtenerMedicosQueTrabajanDiaSemana(DiaSemanaEnum diaSemana) {
		List<Medico> medicosDia = obtenerMedicos().stream()
				.filter(medico -> medico.getDiaSemanaDisponible().equals(diaSemana))
				.collect(Collectors.toList());

		return medicosDia;
	}

	public Clinica getClinicaById(long clinicaId){
		Optional<Clinica> clinica = obtenerClinicas().stream()
				.filter(clinica1 -> clinica1.getClinicaId() == clinicaId)
				.findFirst();

		return clinica.isPresent() ? clinica.get() : null;
	}

	@Override
	public int obtenerCantidadPacientesClinicaFecha(long clinicaId, Date fecha) {
		int cantPacientes = 0;
		Clinica clinica = getClinicaById(clinicaId);

		if (clinica != null) {
			List<Medico> medicosClinica = clinica.getMedicos();

			for (Medico med : medicosClinica) {
				for (Paciente paciente : med.getPacientes()) {
					if (paciente.getFechaTurnoConMedico().equals(fecha)) {
						cantPacientes++;
					}
				}
			}
		}
		return cantPacientes;
	}

	public List<Paciente> obtenerPacientesMedico (Long idMedico) {
		Medico medico = medicoRepository.findById(idMedico).orElse(null);
		return medico.getPacientes();

	}

	public List<Medico> obtenerMedicosPaciente (Long idPaciente) {
		Paciente paciente = pacienteRepository.findById(idPaciente).orElse(null);
		return paciente.getMedicos();
	}


	@Override
	public List<Medico> medicosQueTrabajanDiasNoLaborables() {
		List<Medico> medicos = medicoRepository.findAll();
		return  medicos.stream()
				.filter(Medico::isTrabajaFinesSemanasYFeriados)
				.collect(Collectors.toList());
	}

	@Override
	public Clinica getCLinicaById(Long clinicaId) {
		return clinicaRepository.findById(clinicaId).orElse(null);

	}


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

	/*Actualizar con lamda*/
	public PacienteDto actualizarPaciente(PacienteDto pacientedto, Long id){
		pacienteRepository.findById(id).map(paciente -> {
			paciente.setNombre(pacientedto.getNombre());
			paciente.setApellido(pacientedto.getApellido());
			paciente.setDni(pacientedto.getDni());
			paciente.setEdad(pacientedto.getEdad());
			paciente.setFechaNacimiento(pacientedto.getFechaNacimiento());
			paciente.setFechaTurnoConMedico(pacientedto.getFechaTurnoConMedico());
			paciente.setTelefono(pacientedto.getTelefono());
			paciente.setEmail(pacientedto.getEmail());
			paciente.setMedicos(pacientedto.getMedicos());
			return pacienteRepository.save(paciente);
		}).orElseThrow(NullPointerException::new);
		return pacientedto;
	}


	public void eliminarClinica (Long idClinica) {
		if (clinicaRepository.existsById(idClinica)) {
			clinicaRepository.deleteById(idClinica);
		}
		else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public double promedioPacientesAtendidosPorMedico() {
		double cantidadPacientesAtendidos = pacienteRepository.findAll().size();
		double promedioPacientes = 0;
		int cantMedicos = medicoRepository.findAll().size();

		promedioPacientes = cantidadPacientesAtendidos / cantMedicos;


		return promedioPacientes;
	}
	@Override
	public List<Paciente> pacientesEntreFechas(Date fechaDesde, Date fechaHasta) {
		List<Paciente> pacientes = pacienteRepository.findAll();

		List<Paciente> pacientesFecha = new ArrayList<>();
		for (Paciente paciente : pacientes) {
			if(paciente.getFechaTurnoConMedico().after(fechaDesde) && (paciente.getFechaTurnoConMedico().before(fechaHasta))){
				pacientesFecha.add(paciente);
			}
		}

		return pacientesFecha;
	}

	public Paciente updateTurnoPaciente(CambioTurno cambioTurno, Long id){
		Optional<Paciente> p = pacienteRepository.findById(id);
		Paciente paciente = null;
		if(p.isPresent()){
			paciente = p.get();
			paciente.setFechaTurnoConMedico(cambioTurno.getFechaTurno());
			pacienteRepository.save(paciente);
		}
		return paciente;
	}


}
