package br.imd.ufrn.controller;


import br.imd.ufrn.dto.*;
import br.imd.ufrn.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
@Tag(name = "Enrollments", description = "API para gerenciamento de matrículas")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    @Operation(summary = "Registrar matrícula", description = "Registra uma nova matrícula de estudante em uma turma")
    public ResponseEntity<EnrollmentResponse> registration(@RequestBody EnrollmentRequest data) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.registration(data));
    }

    @DeleteMapping
    @Operation(summary = "Cancelar matrícula", description = "Cancela a matrícula de um estudante em uma turma")
    public ResponseEntity<Void> delete(@RequestBody EnrollmentRequest data) {
        enrollmentService.unregister(data);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/{studentId}")
    @Operation(summary = "Matrículas do estudante", description = "Retorna as turmas em que o estudante está matriculado")
    public ResponseEntity<List<StudentClassroomResponse>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<StudentClassroomResponse> response = enrollmentService.getClassroomsByStudentId(studentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Matrícula em lote", description = "Registra múltiplas matrículas de estudantes em turmas")
    public ResponseEntity<List<EnrollmentResponse>> bulkRegistration(@RequestBody BulkEnrollmentRequest data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.bulkRegistration(data));
    }

    @GetMapping("/classroom/{classroomId}")
    @Operation(summary = "Estudantes matriculados na turma", description = "Retorna a lista de estudantes matriculados em uma turma específica")
    public ResponseEntity<List<EnrolledStudentResponse>> getStudentsByClassroom(@PathVariable Long classroomId) {
        return ResponseEntity.ok(enrollmentService.getStudentsByClassroom(classroomId));
    }
}
