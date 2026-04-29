package br.imd.ufrn.dto;

import br.imd.ufrn.enums.GradeLevel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentResponse(Long id,
                              String name,
                              String cpf,
                              LocalDate birthDate,
                              String responsibleName,
                              String responsibleCpf,
                              String responsiblePhone,
                              GradeLevel gradeLevel,
                              AddressResponse address,
                              LocalDateTime createdAt) {
}
