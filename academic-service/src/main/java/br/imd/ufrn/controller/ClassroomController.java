package br.imd.ufrn.controller;


import br.imd.ufrn.dto.ClassroomRequest;
import br.imd.ufrn.dto.ClassroomResponse;
import br.imd.ufrn.dto.ClassroomStatsDTO;
import br.imd.ufrn.dto.StudentResponse;
import br.imd.ufrn.service.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classroom")
@Tag(name = "Classrooms", description = "API para gerenciamento de turmas")
public class ClassroomController {

    private final ClassroomService classroomService;

    ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    @Operation(summary = "Criar turma", description = "Cria uma nova turma no sistema")
    public ResponseEntity<ClassroomResponse> createClassroom(@RequestBody ClassroomRequest data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classroomService.create(data));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter turma por ID", description = "Retorna os detalhes de uma turma específica")
    public ResponseEntity<ClassroomResponse> getClassroom(@PathVariable Long id) {
        return ResponseEntity.ok().body(classroomService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Listar todas as turmas", description = "Retorna uma lista de todas as turmas cadastradas")
    public ResponseEntity<List<ClassroomResponse>> getAllClassrooms() {
        return ResponseEntity.ok().body(classroomService.getAll());
    }

    @PostMapping("/{id}")
    @Operation(summary = "Atualizar turma", description = "Atualiza os dados de uma turma existente")
    public ResponseEntity<ClassroomResponse> updateClassroom(@PathVariable Long id, @RequestBody ClassroomRequest data) {
        return ResponseEntity.ok().body(classroomService.update(id, data));
    }

    @GetMapping("/stats")
    @Operation(summary = "Estatísticas das turmas", description = "Retorna estatísticas gerais sobre as turmas")
    public ResponseEntity<ClassroomStatsDTO>  getClassroomStats() {
        return ResponseEntity.ok().body(classroomService.getClassroomStats());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar turma", description = "Remove uma turma do sistema")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        classroomService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
