package com.example.demo.services;

import com.example.demo.entities.Clinica;
import com.example.demo.entities.DiaSemanaEnum;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Paciente;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface ClinicaService {
    Paciente guardarPaciente(Paciente nuevoPaciente);
    Medico guardarMedico(Medico nuevoMedico);
    Clinica guardarClinica(Clinica nuevaClinica);
    List<Paciente> obtenerPacientes();
    List<Medico> obtenerMedicos();
    List<Clinica> obtenerClinicas();
    List<Paciente> obtenerPacienteFechaDelMedico(Long id, Date fecha);
    List<Medico> obtenerMedicosQueTrabajanDiaSemana(DiaSemanaEnum diaSemana);
    int obtenerCantidadPacientesClinicaFecha(long clinicaId, Date fecha);
    List<Paciente> obtenerPacientesMedico (Long idMedico);
    List<Medico> obtenerMedicosPaciente (Long idPaciente);

    List<Medico> medicosQueTrabajanDiasNoLaborables();
    List<Medico> medicosQueTrabajanDiasNoLaborablesClinica(Long clinicaId);
    Clinica getCLinicaById(Long clinicaId);
    Paciente updatePaciente(Paciente paciente, Long pacienteId);
    void eliminarClinica (Long idClinica);



}
