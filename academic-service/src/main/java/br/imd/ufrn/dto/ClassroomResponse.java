package br.imd.ufrn.dto;

import br.imd.ufrn.enums.Shifts;
import br.imd.ufrn.model.Enrollment;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record ClassroomResponse (
        Long id,
        String code,
        Shifts shift,
        LocalTime startTime,
        LocalTime endTime,
        LocalDateTime createdAt,
        int students
){
}
