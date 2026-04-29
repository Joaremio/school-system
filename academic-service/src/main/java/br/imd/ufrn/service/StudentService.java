package br.imd.ufrn.service;


import br.imd.ufrn.dto.AddressResponse;
import br.imd.ufrn.dto.ClassroomResponse;
import br.imd.ufrn.dto.StudentRequest;
import br.imd.ufrn.dto.StudentResponse;
import br.imd.ufrn.model.Address;
import br.imd.ufrn.model.Classroom;
import br.imd.ufrn.model.Student;
import br.imd.ufrn.repository.ClassroomRepository;
import br.imd.ufrn.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private ClassroomRepository classroomRepository;

    StudentService(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }
    public StudentResponse transform(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getCpf(),
                student.getBirthDate(),
                student.getResponsibleName(),
                student.getResponsibleCpf(),
                student.getResponsiblePhone(),
                student.getGradeLevel(),
                transformAddress(student.getAddress()),
                student.getCreatedAt()

        );
    }
    private AddressResponse transformAddress(Address address) {
        if (address == null) return null;

        return new AddressResponse(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getCity()
        );
    }

    public StudentResponse create(StudentRequest data) {
        Student student = new Student();
        student.setName(data.name());
        student.setCpf(data.cpf());
        student.setBirthDate(data.birthDate());
        student.setResponsibleName(data.responsibleName());
        student.setResponsibleCpf(data.responsibleCpf());
        student.setResponsiblePhone(data.responsiblePhone());
        student.setGradeLevel(data.gradeLevel());

        Address address = new Address();
        address.setStreet(data.address().street());
        address.setNumber(data.address().number());
        address.setCity(data.address().city());

        student.setAddress(address);

        Student savedStudent = studentRepository.save(student);

        return transform(savedStudent);
    }

    public StudentResponse getStudentById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        return transform(student);
    }

    public List<StudentResponse> getAllStudents(){
        List<Student> students = studentRepository.findAll();

        return students.stream().map(this::transform).toList();
    }

    public StudentResponse updateStudent(Long id, StudentRequest data) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(data.name());
        student.setCpf(data.cpf());
        student.setBirthDate(data.birthDate());
        student.setResponsibleName(data.responsibleName());
        student.setResponsibleCpf(data.responsibleCpf());
        student.setResponsiblePhone(data.responsiblePhone());
        student.setGradeLevel(data.gradeLevel());

        Student updatedStudent = studentRepository.save(student);

        return transform(updatedStudent);
    }

    public void deleteStudent(Long id){

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.delete(student);
    }

    public List<StudentResponse> getStudentsThatDontInThisClassroom(Long classroomId) {
        classroomRepository.findById(classroomId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        return studentRepository.findStudentsNotEnrolledInClassroom(classroomId)
                .stream()
                .map(this::transform)
                .toList();
    }

    public List<StudentResponse> getStudentsThatInThisClassroom(Long classroomId) {
        classroomRepository.findById(classroomId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        return studentRepository.findByEnrollmentsClassroomId(classroomId)
                .stream()
                .map(this::transform)
                .toList();
    }
}
