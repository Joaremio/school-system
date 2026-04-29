package br.imd.ufrn.controller;


import br.imd.ufrn.dto.AttendanceRequest;
import br.imd.ufrn.dto.AttendanceResponse;
import br.imd.ufrn.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@Tag(name = "Attendance", description = "API para gerenciamento de presença")
public class AttendanceController {
    
    private final AttendanceService attendanceService;
    
    AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    
    @PostMapping("/{turmaId}")
    @Operation(summary = "Registrar presença", description = "Registra a presença dos estudantes em uma turma")
    public ResponseEntity<List<AttendanceResponse>> makeAttendance(@PathVariable Long turmaId, @RequestBody List<AttendanceRequest> data) {
        return ResponseEntity.ok().body(attendanceService.makeAttendanceList(turmaId, data));
    }

    @GetMapping("/{turmaId}")
    @Operation(summary = "Obter presença por data", description = "Retorna a lista de presença para uma turma em uma data específica")
    public ResponseEntity<List<AttendanceResponse>> getAttendance(
            @PathVariable Long turmaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
    ) {
        List<AttendanceResponse> response = attendanceService.getAttendanceListForDate(date, turmaId);
        return ResponseEntity.ok(response);
    }


}
