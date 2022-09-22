package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Clinica;
import com.example.demo.entities.DiaSemanaEnum;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Paciente;
import com.example.demo.services.ClinicaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clinica")
public class ClinicaController {
	
	@Autowired
	private ClinicaService service;

	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@ApiOperation(value = "Endpoint de prueba para saber que funciona las API", response = String.class, tags = "Endpoint prueba API")
	@GetMapping("/hello")
	public String hello() {
		return "Funciona el controlador";
	}
	
	@ApiOperation(value = "Endpoint para poder agregar un paciente a la lista de pacientes", response = Paciente.class, tags = "Agregar paciente")
	@PostMapping("/add/paciente")
    public Paciente cargarPaciente(@RequestBody Paciente paciente) {

		return service.guardarPaciente(paciente);
    }
	
	@ApiOperation(value = "Endpoint para poder agregar un medico a la lista de medicos", response = Paciente.class, tags = "Agregar medico")
	@PostMapping("/add/medico")
    public Medico cargarMedico(@RequestBody Medico medico) {

		return service.guardarMedico(medico);
    }
	
	@ApiOperation(value = "Endpoint para poder agregar una clinica a la lista de clinicas", response = Paciente.class, tags = "Agregar clinica")
	@PostMapping("/add/clinica")
    public Clinica cargarClinica(@RequestBody Clinica clinica) {

		return service.guardarClinica(clinica);
    }

	@ApiOperation(value = "Endpoint para poder una lista de pacientes filtrando por medico y por fecha de turno con el medico", response = Paciente.class, tags = "Pacientes por medico y fecha")
	@GetMapping("/get/pacientesPorMedicoYFecha/{medicoId}/{fecha}")
	public List<Paciente> pacientesPorMedicoYFecha(@PathVariable("medicoId") Long medicoId, @PathVariable("fecha") Date fecha) {
		return service.obtenerPacienteFechaDelMedico(medicoId, fecha);
	}
	
	@ApiOperation(value = "Endpoint para poder obtener una lista de pacientes que tuvo un medico", response = Paciente.class, tags = "Historial de pacientes de un medico")
	@GetMapping("/get/historialPacientesMedico/{medicoId}")
	public ResponseEntity<List<Paciente>> historialPacientesMedico(@PathVariable("medicoId") Long medicoId) {
		return ResponseEntity.ok().body(service.obtenerPacientesMedico(medicoId));
	}
	
	@ApiOperation(value = "Endpoint para poder obtener una lista de medicos con los que se atendio un paciente", response = Paciente.class, tags = "Historial de medicos de un paciente")
	@GetMapping("/get/historialAtencionesPaciente/{pacienteId}")
	public ResponseEntity<List<Medico>> historialAtencionesPaciente(@PathVariable("pacienteId") Long pacienteId) {
		return ResponseEntity.ok().body(service.obtenerMedicosPaciente(pacienteId));
	}
	
	@ApiOperation(value = "Endpoint para poder obtener los medicos que trabajan en un dia de la semana", response = Paciente.class, tags = "Medicos que trabajan un dia especifico de la semana")
	@GetMapping("/get/medicosQueTrabajanDia/{diaSemana}")
	public ResponseEntity<List<Medico>> medicosQueTrabajanDia(@PathVariable("diaSemana") DiaSemanaEnum diaSemana) {

		List<Medico> medicosDia = service.obtenerMedicosQueTrabajanDiaSemana(diaSemana);
		return ResponseEntity.status(HttpStatus.OK).body(medicosDia);
	}
	
	@ApiOperation(value = "Endpoint para poder obtener la cantidad de pacientes que tiene una clinica para una fecha en particular", response = Paciente.class, tags = "Cantidad de pacientes de una clinica por fecha")
	@GetMapping("/get/cantidadPacientesClinicaFecha/{clinicaId}/{fecha}")
	public int cantidadPacientesClinicaFecha(@PathVariable("clinicaId") long clinicaId, @PathVariable("fecha") Date fecha) {

		return service.obtenerCantidadPacientesClinicaFecha(clinicaId, fecha);
	}

	@ApiOperation(value = "Endpoint para poder obtener el promedio de pacientes atendidos por todos los medicos", response = Paciente.class, tags = "Promedio de pacientes de los medico")
	@GetMapping("/get/promedioPacientesAtendidosPorMedico/{medicoId}")
	public double promedioPacientesAtendidosPorMedico(@PathVariable("medicoId") int medicoId) {
		return 0;
	}

	@ApiOperation(value = "Endpoint para poder obtener una lista de pacientes en un rango de fechas", response = Paciente.class, tags = "Pacientes por rango de fechas")
	@GetMapping("/get/pacientesEntreFechas/desde/{fechaDesde}/hasta/{fechaHasta}")
	public List<Paciente> pacientesEntreFechas(@PathVariable("fechaDesde") Date fechaDesde, @PathVariable("fechaHasta") Date fechaHasta) {
		return service.pacientesEntreFechas(fechaDesde, fechaHasta);
	}


	@ApiOperation(value = "Endpoint para poder obtener los medicos que trabajan los fines de semana y o feriados", response = Paciente.class, tags = "Medicos que trabajan dias no laborables")
	@GetMapping("/get/medicosQueTrabajanDiasNoLaborables")
	public ResponseEntity<List<Medico>> medicosQueTrabajanDiasNoLaborables() {
		ResponseEntity responseEntity;
		try {
			List<Medico> medicos = service.medicosQueTrabajanDiasNoLaborables();
			if (medicos.size() == 0) throw new Exception();
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(medicos);
		} catch (Exception e) {
			String noFound = "Hubo un error, no se obtuvo resultados";
			responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(noFound);
		}
		return responseEntity;
	}


	@ApiOperation(value = "Endpoint para poder obtener los medicos que trabajan en una clinica en particular", response = Paciente.class, tags = "Medicos que trabajan en una clinica")
	@GetMapping("/get/medicosPorClinica/{clinicaId}")
	public ResponseEntity<List<Medico>> medicosQueTrabajanDiasNoLaborables(@PathVariable("clinicaId") Long clinicaId) {
		ResponseEntity response;
		try {
			List<Medico> medicos = service.medicosQueTrabajanDiasNoLaborablesClinica(clinicaId);
			if (medicos.size() == 0) throw new Exception();
			response = ResponseEntity.status(HttpStatus.OK).body(medicos);
		} catch (Exception exception){
			String noFound = "Hubo un error, no se obtuvo resultados, introduce una clinica valida";
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(noFound);
		}
		return response;
	}

	@ApiOperation(value = "Endpoint para poder obtener todos los pacientes", response = Paciente.class, tags = "Todos los pacientes")
	@GetMapping("/get/pacientes")
	public ResponseEntity<List<Paciente>> obtenerPacientes () {
		return ResponseEntity.ok().body(service.obtenerPacientes());
	}

	@ApiOperation(value = "Endpoint para poder obtener todos los médicos", response = Medico.class, tags = "Todos los médicos")
	@GetMapping("/get/medicos")
	public ResponseEntity<List<Medico>> obtenerMedicos () {
		return ResponseEntity.ok().body(service.obtenerMedicos());
	}

	@ApiOperation(value = "Endpoint para poder obtener todas los clínicas", response = Clinica.class, tags = "Todas los clínicas")
	@GetMapping("/get/clinicas")
	public ResponseEntity<List<Clinica>> obtenerClinicas () {
		return ResponseEntity.ok().body(service.obtenerClinicas());
	}

	@ApiOperation(value = "Endpoint para poder actualizar un paciente", response = Paciente.class, tags = "Actualizacion de Paciente")
	@PutMapping("/update/paciente/{id}")
	public ResponseEntity<?> actualizarPaciente(@RequestBody Paciente paciente, @PathVariable Long id){
		try {
			service.updatePaciente(paciente, id);
			String fineId = "EL paciente se actualizo en la db";
			return ResponseEntity.status(HttpStatus.OK).body(fineId);
		}catch (Exception e){
			String badId = "No se pudo actualizar el paciente, verifica el id";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badId);
		}
	}

	@ApiOperation(value = "Endpoint para poder obtener el promedio de pacientes atendidos por todos los medicos", response = Paciente.class, tags = "Promedio de pacientes de los medico")
	@GetMapping("/get/promedioPacientesAtendidosPorMedico")
	public double promedioPacientesAtendidosPorMedico() {

		return service.promedioPacientesAtendidosPorMedico();
	}

}
