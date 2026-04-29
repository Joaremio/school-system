package br.imd.ufrn.dto;


public record StudentClassroomResponse(
        Long enrollmentId,
        Long classroomId,
        String classroomCode
) {
}
