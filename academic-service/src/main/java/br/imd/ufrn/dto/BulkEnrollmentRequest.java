package br.imd.ufrn.dto;

import java.util.List;

public record BulkEnrollmentRequest(
        Long classroomId,
        List<Long> studentIds
) {}