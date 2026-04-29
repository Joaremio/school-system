package br.imd.ufrn.dto;


import br.imd.ufrn.enums.AttendanceStatus;

public record AttendanceResponse(
        Long id,
        Long enrollmentId,
        String studentName,
        AttendanceStatus status

) {
}
