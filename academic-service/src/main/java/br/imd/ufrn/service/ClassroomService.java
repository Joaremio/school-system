package br.imd.ufrn.service;


import br.imd.ufrn.dto.ClassroomRequest;
import br.imd.ufrn.dto.ClassroomResponse;
import br.imd.ufrn.dto.ClassroomStatsDTO;
import br.imd.ufrn.dto.StudentResponse;
import br.imd.ufrn.enums.Shifts;
import br.imd.ufrn.model.Classroom;
import br.imd.ufrn.repository.ClassroomRepository;
import br.imd.ufrn.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    ClassroomService(ClassroomRepository classroomRepository, StudentRepository studentRepository) {
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
    }

    public ClassroomResponse transform(Classroom  classroom) {
        return new ClassroomResponse(
                classroom.getId(),
                classroom.getCode(),
                classroom.getShift(),
                classroom.getStartTime(),
                classroom.getEndTime(),
                classroom.getCreatedAt(),
                classroom.getEnrollments().size()
        );
    }

    public ClassroomResponse create(ClassroomRequest data) {
        Classroom classroom = new Classroom();
        classroom.setShift(data.shift());
        classroom.setStartTime(data.startTime());
        classroom.setEndTime(data.endTime());
        classroom.setCode(createClassroomCode(data));

        Classroom savedClassroom = classroomRepository.save(classroom);

        return transform(savedClassroom);
    }

    public ClassroomResponse findById(Long id   ) {
        Classroom classroom = classroomRepository.findById(id).orElseThrow(()-> new RuntimeException("Classroom not found"));

        return transform(classroom);
    }

    public List<ClassroomResponse> getAll(){
        List<Classroom> classrooms = classroomRepository.findAll();

        return classrooms.stream().map(this::transform).toList();
    }

    public ClassroomResponse update(Long id, ClassroomRequest data) {
        Classroom classroom = classroomRepository.findById(id).orElseThrow(()-> new RuntimeException("Classroom not found"));

        classroom.setShift(data.shift());

        Classroom savedClassroom = classroomRepository.save(classroom);

        return transform(savedClassroom);
    }


    public ClassroomStatsDTO getClassroomStats(){
        long students = studentRepository.count();
        long classes = classroomRepository.count();

        return new ClassroomStatsDTO(students, classes);
    }

    public void delete(Long id) {
        Classroom classroom = classroomRepository.findById(id).orElseThrow(()-> new RuntimeException("Classroom not found"));
        classroomRepository.delete(classroom);
    }

    public String createClassroomCode(ClassroomRequest data) {
        Shifts shift = data.shift();

        int randomNumber = ThreadLocalRandom.current().nextInt(1000, 10000);

        return shift.getCode() + randomNumber;
    }



}
