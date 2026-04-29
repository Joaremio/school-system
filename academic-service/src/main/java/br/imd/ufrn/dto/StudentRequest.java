package br.imd.ufrn.dto;

import br.imd.ufrn.enums.GradeLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentRequest(
        @NotBlank
        @Size(min = 1, max = 100)
        String name,
        @Size(min = 1, max = 13)
        String cpf,

        @Past
        @NotNull
        LocalDate birthDate,

        @NotBlank
        @Size(min = 1, max = 100)
        String responsibleName,
        @Size(min = 1, max = 13)
        String responsibleCpf,
        String responsiblePhone,

        @NotNull
        GradeLevel gradeLevel,

        AddressRequest address
) {
}
