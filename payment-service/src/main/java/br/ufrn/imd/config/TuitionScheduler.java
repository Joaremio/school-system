package br.ufrn.imd.config;

import br.ufrn.imd.dto.StudentResponse;
import br.ufrn.imd.integration.AcademicClient;
import br.ufrn.imd.service.TuitionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TuitionScheduler {

    private final AcademicClient academicClient;
    private final TuitionService tuitionService;

    public TuitionScheduler(AcademicClient academicClient,
                            TuitionService tuitionService) {
        this.academicClient = academicClient;
        this.tuitionService = tuitionService;
    }

    @Scheduled(fixedRate = 60000)
    public void generateMonthlyTuitions() {

        List<StudentResponse> students = academicClient.getStudents();

        for (StudentResponse student : students) {
            tuitionService.createTuitionForStudents(student);
        }
    }
}