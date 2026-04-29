package br.imd.ufrn.dto;

import br.imd.ufrn.enums.EnrollmentStatus;
import br.imd.ufrn.model.Classroom;
import br.imd.ufrn.model.Student;

import java.time.LocalDate;

public record EnrollmentResponse(
        Long id,
        Long studentId,
        Long classroomId,
        LocalDate enrollmentDate,
        EnrollmentStatus status
) {
}
