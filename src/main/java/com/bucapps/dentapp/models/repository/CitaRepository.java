package com.bucapps.dentapp.models.repository;

import com.bucapps.dentapp.models.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> getAllByDoctorIdAndFecha(Long doctorId, Date fecha);

    @Query("select c from Cita c where c.doctor.id = ?1 and c.usuario.token = ?2 and c.apartada = ?3 and (c.pagada is false or c.pagada is null)")
    Cita getAllByDoctorIdAndUsuarioTokenAndApartada(Long doctorId, String usuarioId, Boolean apartada);

    int countAllByDoctorIdAndUsuarioTokenAndFechaAndHora(Long doctorId, String usuarioId, Date fecha, Time hora);

    @Transactional
    @Modifying
    @Query(value = "delete\n" +
            "from cita\n" +
            "where apartada is true\n" +
            "  and doctor_id = ?1\n" +
            "  and usuario_id = (select id from usuario where token = ?2)\n" +
            "  and (pagada is false or pagada is null);", nativeQuery = true)
    int borrarCitaApartadaPrevia(Long doctorId, String usuarioId);

    @Query(value = "select *\n" +
            "from cita\n" +
            "where apartada is false\n" +
            "  and usuario_id = (select id from usuario where token = ?1)\n" +
            "order by fecha asc ,hora asc ", nativeQuery = true)
    List<Cita> getAllUsuarioToken(String usuarioToken);

    @Query(value = "select *\n" +
            "from cita\n" +
            "where doctor_id = ?1\n" +
            "  and estado_confirmacion_id in (1, 2, 5);", nativeQuery = true)
    List<Cita> obtenerTodasLasCitasPagadasPorConfirmarYFinalizadas(Long drId);

    List<Cita> getAllByDoctorIdAndFechaAndEstadoConfirmacionEstadoOrderByHora(Long drId, Date fecha, Long estadoConfirmacion);

    List<Cita> getAllByDoctorIdAndAndEstadoConfirmacionEstadoOrderByCreatedDate(Long drId, Long estadoConfirmacion);
}
