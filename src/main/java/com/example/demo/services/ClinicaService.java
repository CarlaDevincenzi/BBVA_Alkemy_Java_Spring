package com.example.demo.services;

import com.example.demo.entities.Clinica;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Paciente;

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

    public double promedioPacientesAtendidosPorMedico();

    public List<Paciente> pacientesEntreFechas(Date fechaDesde, Date fechaHasta);
}
