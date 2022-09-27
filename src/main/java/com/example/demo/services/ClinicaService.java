package com.example.demo.services;

import com.example.demo.dto.ClinicaDto;
import com.example.demo.dto.MedicoDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.entities.*;

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
    double promedioPacientesAtendidosPorMedico();
    List<Paciente> pacientesEntreFechas(Date fechaDesde, Date fechaHasta);
    PacienteDto updateTurnoPaciente(CambioTurno cambioTurno, Long id);

    MedicoDto addMedico(MedicoDto medicoDto);
    PacienteDto addPaciente(PacienteDto pacienteDto);
    ClinicaDto addClinica(ClinicaDto clinicaDto);

    PacienteDto savePaciente(PacienteDto pacienteDto);
    PacienteDto actualizarPaciente(PacienteDto pacientedto, Long id);





}
