package com.example.demo.services.imple;

<<<<<<< Updated upstream:src/main/java/com/example/demo/services/ClinicaService.java
import com.example.demo.entities.*;
import org.springframework.web.bind.annotation.PathVariable;
=======
import com.example.demo.dto.PacienteDto;
import com.example.demo.entities.CambioTurno;
import com.example.demo.entities.Paciente;
>>>>>>> Stashed changes:src/main/java/com/example/demo/services/imple/PacienteService.java

import java.util.Date;
import java.util.List;

public interface PacienteService {
    Paciente guardarPaciente(Paciente nuevoPaciente);
    List<Paciente> obtenerPacientes();
    List<Paciente> obtenerPacienteFechaDelMedico(Long id, Date fecha);
    int obtenerCantidadPacientesClinicaFecha(long clinicaId, Date fecha);
    List<Paciente> obtenerPacientesMedico (Long idMedico);
<<<<<<< Updated upstream:src/main/java/com/example/demo/services/ClinicaService.java
    List<Medico> obtenerMedicosPaciente (Long idPaciente);

    List<Medico> medicosQueTrabajanDiasNoLaborables();
    List<Medico> medicosQueTrabajanDiasNoLaborablesClinica(Long clinicaId);
    Clinica getCLinicaById(Long clinicaId);
    Paciente updatePaciente(Paciente paciente, Long pacienteId);

    void eliminarClinica (Long idClinica);

    double promedioPacientesAtendidosPorMedico();
    List<Paciente> pacientesEntreFechas(Date fechaDesde, Date fechaHasta);
    Paciente updateTurnoPaciente(CambioTurno cambioTurno, Long id);





=======
    Paciente updatePaciente(Paciente paciente, Long pacienteId);

    double promedioPacientesAtendidosPorMedico();
    List<Paciente> pacientesEntreFechas(Date fechaDesde, Date fechaHasta);
    Paciente updateTurnoPaciente(CambioTurno cambioTurno, Long id);
    PacienteDto addPaciente(PacienteDto pacienteDto);


    PacienteDto savePaciente(PacienteDto pacienteDto);
    PacienteDto actualizarPaciente(PacienteDto pacientedto, Long id);
>>>>>>> Stashed changes:src/main/java/com/example/demo/services/imple/PacienteService.java
}
