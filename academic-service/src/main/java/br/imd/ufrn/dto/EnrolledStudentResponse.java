package br.imd.ufrn.dto;

import br.imd.ufrn.enums.EnrollmentStatus;
import br.imd.ufrn.enums.GradeLevel;

import java.time.LocalDate;

public record EnrolledStudentResponse(
        Long enrollmentId,
        Long studentId,
        String studentName,
        String cpf,
        GradeLevel gradeLevel,
        EnrollmentStatus status,
        LocalDate enrollmentDate
) {}
