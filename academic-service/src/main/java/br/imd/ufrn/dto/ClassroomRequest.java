package br.imd.ufrn.dto;

import br.imd.ufrn.enums.Shifts;


import java.time.LocalTime;

public record ClassroomRequest(Shifts shift, LocalTime startTime, LocalTime endTime) {
}
