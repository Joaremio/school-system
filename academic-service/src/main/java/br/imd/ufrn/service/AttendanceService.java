package br.imd.ufrn.service;


import br.imd.ufrn.dto.AttendanceRequest;
import br.imd.ufrn.dto.AttendanceResponse;
import br.imd.ufrn.enums.AttendanceStatus;
import br.imd.ufrn.model.Attendance;
import br.imd.ufrn.model.Classroom;
import br.imd.ufrn.model.Enrollment;
import br.imd.ufrn.repository.AttendanceRepository;
import br.imd.ufrn.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    ClassroomRepository classroomRepository;
    AttendanceRepository attendanceRepository;

    public AttendanceService(ClassroomRepository classroomRepository,  AttendanceRepository attendanceRepository) {
        this.classroomRepository = classroomRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<AttendanceResponse> makeAttendanceList(Long turmaId, List<AttendanceRequest> data) {
        Classroom classroom = classroomRepository.findById(turmaId).orElseThrow(()-> new RuntimeException("Classroom with id: " + turmaId + " not found"));

        Map<Long, AttendanceStatus> attendanceMap = data.stream()
                .collect(Collectors.toMap(AttendanceRequest::enrollmentId, AttendanceRequest::status));

        List<Enrollment> enrollments = classroom.getEnrollments();

        List<Attendance> attendancesToSave = new ArrayList<>();

        for(Enrollment enrollment : enrollments) {

            AttendanceStatus status = attendanceMap.get(enrollment.getId());

            if (status != null) {
                Attendance attendance = new Attendance();
                attendance.setEnrollment(enrollment);
                attendance.setStatus(status);
                attendancesToSave.add(attendance);
            }
        }
        List<Attendance> saved = attendanceRepository.saveAll(attendancesToSave);

        return saved.stream()
                .map(a -> new AttendanceResponse(
                        a.getId(),
                        a.getEnrollment().getId(),
                        a.getEnrollment().getStudent().getName(),
                        a.getStatus()))
                .toList();
    }

    public List<AttendanceResponse> getAttendanceListForDate(LocalDateTime date, Long turmaId) {

        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = date.toLocalDate().atTime(23, 59, 59);

        List<Attendance> attendances =
                attendanceRepository.findByEnrollment_Classroom_IdAndCreatedAtBetween(
                        turmaId,
                        startOfDay,
                        endOfDay
                );

        return attendances.stream()
                .map(a -> new AttendanceResponse(
                        a.getId(),
                        a.getEnrollment().getId(),
                        a.getEnrollment().getStudent().getName(),
                        a.getStatus()
                ))
                .toList();
    }
}
