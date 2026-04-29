package br.imd.ufrn.controller;


import br.imd.ufrn.dto.StudentRequest;
import br.imd.ufrn.dto.StudentResponse;
import br.imd.ufrn.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Students", description = "API para gerenciamento de estudantes")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(summary = "Criar estudante", description = "Cria um novo estudante no sistema")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody  StudentRequest data){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(data));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter estudante por ID", description = "Retorna os detalhes de um estudante específico")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }


    @GetMapping
    @Operation(summary = "Listar todos os estudantes", description = "Retorna uma lista de todos os estudantes cadastrados")
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar estudante", description = "Atualiza os dados de um estudante existente")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequest data){

        return ResponseEntity.ok(studentService.updateStudent(id, data));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar estudante", description = "Remove um estudante do sistema")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/not-enrolled/{classroomId}")
    @Operation(summary = "Estudantes não matriculados na turma", description = "Retorna estudantes que não estão matriculados na turma especificada")
    public ResponseEntity<List<StudentResponse>> getStudentsThatDontInThisClassroom(@PathVariable Long classroomId){
        return ResponseEntity.ok(studentService.getStudentsThatDontInThisClassroom(classroomId));
    }

    @GetMapping("/enrolled/{classroomId}")
    @Operation(summary = "Estudantes matriculados na turma", description = "Retorna estudantes que estão matriculados na turma especificada")
    public ResponseEntity<List<StudentResponse>> getStudentsThatInThisClassroom(@PathVariable Long classroomId){
        return  ResponseEntity.ok(studentService.getStudentsThatInThisClassroom(classroomId));
    }



}
