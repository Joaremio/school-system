package br.imd.ufrn.repository;

import br.imd.ufrn.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEnrollment_Classroom_IdAndCreatedAtBetween(
            Long classroomId,
            LocalDateTime start,
            LocalDateTime end
    );
}
