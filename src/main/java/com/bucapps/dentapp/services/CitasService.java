package com.bucapps.dentapp.services;

import com.bucapps.dentapp.models.dto.ApartaCitaDto;
import com.bucapps.dentapp.models.entity.Cita;
import com.bucapps.dentapp.models.entity.Doctor;
import com.bucapps.dentapp.models.repository.CitaRepository;
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

    public List<Time> obtenerHorarioDisponiblePorDoctorYDia(Long doctorId, String diaSeleccionado) throws ParseException {

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
        Cita cita = citaRepository.getAllByDoctorIdAndUsuarioTokenAndApartada(
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
        cita.setUsuario(usuarioRepository.getAllByToken(dto.getUsuario()));

        citaRepository.save(cita);

        return cita;
    }
}
