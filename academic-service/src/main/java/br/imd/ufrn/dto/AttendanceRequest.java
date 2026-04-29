package br.imd.ufrn.dto;

import br.imd.ufrn.enums.AttendanceStatus;

public record AttendanceRequest(
        Long enrollmentId,
        AttendanceStatus status
) {

}
