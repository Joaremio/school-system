package br.imd.ufrn.service;


import br.imd.ufrn.dto.*;
import br.imd.ufrn.model.Classroom;
import br.imd.ufrn.model.Enrollment;
import br.imd.ufrn.model.Student;
import br.imd.ufrn.repository.ClassroomRepository;
import br.imd.ufrn.repository.EnrollmentRepository;
import br.imd.ufrn.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             ClassroomRepository classroomRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }

    public EnrollmentResponse registration(EnrollmentRequest data) {
        Student student = studentRepository.findById(data.studentId())
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));

        Classroom classroom = classroomRepository.findById(data.classroomId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setClassroom(classroom);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return new EnrollmentResponse(
                savedEnrollment.getId(),
                savedEnrollment.getStudent().getId(),
                savedEnrollment.getClassroom().getId(),
                savedEnrollment.getEnrollmentDate(),
                savedEnrollment.getStatus()
        );
    }

    @Transactional
    public void unregister(EnrollmentRequest data) {
        if (!enrollmentRepository.existsByStudentIdAndClassroomId(data.studentId(), data.classroomId())) {
            throw new RuntimeException("Matrícula não encontrada para este aluno nesta turma.");
        }

        enrollmentRepository.deleteByStudentIdAndClassroomId(data.studentId(), data.classroomId());
    }


    public List<StudentClassroomResponse> getClassroomsByStudentId(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        return enrollments.stream()
                .map( en -> new StudentClassroomResponse(
                        en.getId(),
                        en.getClassroom().getId(),
                        en.getClassroom().getCode()
                )).toList();
    }

    @Transactional
    public List<EnrollmentResponse> bulkRegistration(BulkEnrollmentRequest data) {
        Classroom classroom = classroomRepository.findById(data.classroomId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        return data.studentIds().stream().map(studentId -> {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Estudante não encontrado: " + studentId));

            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setClassroom(classroom);

            Enrollment saved = enrollmentRepository.save(enrollment);

            return new EnrollmentResponse(
                    saved.getId(),
                    saved.getStudent().getId(),
                    saved.getClassroom().getId(),
                    saved.getEnrollmentDate(),
                    saved.getStatus()
            );
        }).toList();
    }

    public List<EnrolledStudentResponse> getStudentsByClassroom(Long classroomId) {
        classroomRepository.findById(classroomId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        return enrollmentRepository.findByClassroomId(classroomId)
                .stream()
                .map(e -> new EnrolledStudentResponse(
                        e.getId(),
                        e.getStudent().getId(),
                        e.getStudent().getName(),
                        e.getStudent().getCpf(),
                        e.getStudent().getGradeLevel(),
                        e.getStatus(),
                        e.getEnrollmentDate()
                ))
                .toList();
    }
}
