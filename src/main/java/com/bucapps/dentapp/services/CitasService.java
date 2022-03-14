package com.bucapps.dentapp.services;


import com.bucapps.dentapp.models.dto.ApartaCitaDto;
import com.bucapps.dentapp.models.entity.Cita;
import com.bucapps.dentapp.models.entity.Doctor;
import com.bucapps.dentapp.models.repository.CitaRepository;
import com.bucapps.dentapp.models.repository.EstadoConfirmacionRepository;
import com.bucapps.dentapp.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CitasService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoConfirmacionRepository estadoConfirmacionRepository;

    @Autowired
    private DoctoresService doctoresService;


    public List<Date> obtenerDiasDisponiblesPorDr(Long doctorId) {

        Doctor doctor = doctoresService.obtenerDoctorPorId(doctorId);

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.setTime(dt);

        List<Date> fechas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            c.add(Calendar.DATE, 1);

            switch (c.getTime().getDay()) {
                case 0:
                    if (doctor.getDisponibilidadSemanal().getDomingo()) {
                        fechas.add(c.getTime());
                    }
                    break;
                case 1:
                    if (doctor.getDisponibilidadSemanal().getLunes()) {
                        fechas.add(c.getTime());
                    }
                    break;
                case 2:
                    if (doctor.getDisponibilidadSemanal().getMartes()) {
                        fechas.add(c.getTime());
                    }
                    break;
                case 3:
                    if (doctor.getDisponibilidadSemanal().getMiercoles()) {
                        fechas.add(c.getTime());
                    }
                    break;
                case 4:
                    if (doctor.getDisponibilidadSemanal().getJueves()) {
                        fechas.add(c.getTime());
                    }
                    break;
                case 5:
                    if (doctor.getDisponibilidadSemanal().getViernes()) {
                        fechas.add(c.getTime());
                    }
                    break;
                case 6:
                    if (doctor.getDisponibilidadSemanal().getSabado()) {
                        fechas.add(c.getTime());
                    }
                    break;
            }


        }
        return fechas;
    }

    public List<Time> obtenerHorarioDisponiblePorDoctorYDia(Long doctorId, String diaSeleccionado, Boolean huboCambio, String idUsuario) throws ParseException {

        if (huboCambio) {
            citaRepository.borrarCitaApartadaPrevia(doctorId, idUsuario);
        }

        Date dia = new SimpleDateFormat("dd-MM-yyyy").parse(diaSeleccionado);
        Doctor doctor = doctoresService.obtenerDoctorPorId(doctorId);

        Time apertura = new Time(System.currentTimeMillis());
        Time cierre = new Time(System.currentTimeMillis());

        switch (dia.getDay()) {
            case 0:
                apertura = doctor.getDisponibilidadSemanal().getDomingoApertura();
                cierre = doctor.getDisponibilidadSemanal().getDomingoCierre();
                break;
            case 1:
                apertura = doctor.getDisponibilidadSemanal().getLunesApertura();
                cierre = doctor.getDisponibilidadSemanal().getLunesCierre();
                break;
            case 2:
                apertura = doctor.getDisponibilidadSemanal().getMartesApertura();
                cierre = doctor.getDisponibilidadSemanal().getMartesCierre();
                break;
            case 3:
                apertura = doctor.getDisponibilidadSemanal().getMiercolesApertura();
                cierre = doctor.getDisponibilidadSemanal().getMiercolesCierre();
                break;
            case 4:
                apertura = doctor.getDisponibilidadSemanal().getJuevesApertura();
                cierre = doctor.getDisponibilidadSemanal().getJuevesCierre();
                break;
            case 5:
                apertura = doctor.getDisponibilidadSemanal().getViernesApertura();
                cierre = doctor.getDisponibilidadSemanal().getViernesCierre();
                break;
            case 6:
                apertura = doctor.getDisponibilidadSemanal().getSabadoApertura();
                cierre = doctor.getDisponibilidadSemanal().getSabadoCierre();
                break;
        }

        List<Cita> citasCreadas = citaRepository.getAllByDoctorIdAndFecha(doctorId, new java.sql.Date(dia.getTime()));

        Integer aperturaInt = Integer.parseInt(apertura.toString().split(":")[0]);
        Integer cierreInt = Integer.parseInt(cierre.toString().split(":")[0]);

        List<Time> horariosDisponibles = new ArrayList<>();

        for (int i = aperturaInt; i <= cierreInt; i++) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format

            java.util.Date d1;

            if (i < 10) {
                d1 = format.parse("0" + (i) + ":00");
            } else {
                d1 = format.parse((i) + ":00");
            }

            java.sql.Time sqlTime = new java.sql.Time(d1.getTime());

            if (citasCreadas.stream().noneMatch(c -> c.getHora().equals(sqlTime))) {
                horariosDisponibles.add(sqlTime);
            }

        }


        return horariosDisponibles;
    }


    public Cita apartarCita(ApartaCitaDto dto) {
        Cita cita = new Cita();
        if (citaRepository.countAllByDoctorIdAndUsuarioTokenAndFechaAndHora(dto.getDoctorId(),
                dto.getUsuario(), dto.getFecha(), Time.valueOf(dto.getHora())) == 0) {
            cita = citaRepository.getAllByDoctorIdAndUsuarioTokenAndApartada(
                    dto.getDoctorId(),
                    dto.getUsuario(),
                    true);


            if (cita == null) {
                cita = new Cita();
            }

            cita.setFecha(dto.getFecha());
            cita.setHora(Time.valueOf(dto.getHora()));
            cita.setDoctor(doctoresService.obtenerDoctorPorId(dto.getDoctorId()));
            cita.setApartada(true);
            cita.setNombreOpcional(dto.getNombreOpcional());
            cita.setTelOpcional(dto.getTelOpcional());
            cita.setUsuario(usuarioRepository.getAllByToken(dto.getUsuario()));

            citaRepository.save(cita);

        } else {
            cita.setId(0L);
        }


        return cita;
    }


    public ApartaCitaDto confirmarCitaUsuario(ApartaCitaDto dto) {
        Cita cita = citaRepository.getById(dto.getId());
        cita.setEstadoConfirmacion(estadoConfirmacionRepository.getById(1L));
        cita.setTokenCardConekta(dto.getTokenCardConekta());
        cita.setNombreOpcional(dto.getNombreOpcional());
        cita.setApartada(false);

        citaRepository.save(cita);


        return obtenerCita(dto.getId());
    }

    public ApartaCitaDto obtenerCita(Long citaId) {
        Cita cita = citaRepository.getById(citaId);

        ApartaCitaDto dto = new ApartaCitaDto();
        dto.setId(cita.getId());
        dto.setDoctorId(cita.getDoctor().getId());
        dto.setFecha(cita.getFecha());
        dto.setHora(cita.getHora().toString());
        dto.setNombreOpcional(cita.getNombreOpcional());
        dto.setUsuario(cita.getUsuario().getNombre());
        dto.setNombreDoctor(cita.getDoctor().getNombre());
        dto.setNombreClinica(cita.getDoctor().getClinica().getNombre());
        dto.setFotoDoctor(cita.getDoctor().getPhoto());
        dto.setTelDoctor(cita.getDoctor().getTel());
        dto.setEstadoCita(cita.getEstadoConfirmacion().getEstado());
        dto.setDescripcionEstadoCita(cita.getEstadoConfirmacion().getNombre());

        if (cita.getDoctor().getClinica().getDireccion().getInterior() != null) {
            dto.setDireccionClinica(cita.getDoctor().getClinica().getDireccion().getCalle()
                    + " "
                    + cita.getDoctor().getClinica().getDireccion().getNumero() +
                    " "
                    + cita.getDoctor().getClinica().getDireccion().getInterior());
        }else {
            dto.setDireccionClinica(cita.getDoctor().getClinica().getDireccion().getCalle()
                    + " "
                    + cita.getDoctor().getClinica().getDireccion().getNumero());
        }
        return dto;
    }

    public List<ApartaCitaDto> misCitasUsuario(String idUsuario) {
        List<ApartaCitaDto> listadoCitas = new ArrayList<>();

        List<Cita> citasCreadas = citaRepository.getAllUsuarioToken(idUsuario);

        for (Cita cita : citasCreadas) {
            ApartaCitaDto dto = new ApartaCitaDto();
            dto.setId(cita.getId());
            dto.setDoctorId(cita.getDoctor().getId());
            dto.setFecha(cita.getFecha());
            dto.setHora(cita.getHora().toString());
            dto.setNombreOpcional(cita.getNombreOpcional());
            dto.setUsuario(cita.getUsuario().getNombre());
            dto.setNombreDoctor(cita.getDoctor().getNombre());
            dto.setNombreClinica(cita.getDoctor().getClinica().getNombre());
            dto.setFotoDoctor(cita.getDoctor().getPhoto());
            dto.setTelDoctor(cita.getDoctor().getTel());
            dto.setEstadoCita(cita.getEstadoConfirmacion().getEstado());
            dto.setDescripcionEstadoCita(cita.getEstadoConfirmacion().getNombre());


            listadoCitas.add(dto);
        }
        return listadoCitas;
    }
}
