package br.ufrn.imd.service;


import br.ufrn.imd.dto.StudentResponse;
import br.ufrn.imd.dto.TuitionResponse;
import br.ufrn.imd.enums.TuitionStatus;
import br.ufrn.imd.model.Tuition;
import br.ufrn.imd.repository.TuitionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class TuitionService {

    private TuitionRepository tuitionRepository;

    public TuitionService(TuitionRepository tuitionRepository) {
        this.tuitionRepository = tuitionRepository;
    }

    public void createTuitionForStudents(StudentResponse student){

        String referenceMonth = YearMonth.now().toString();

        boolean exists = tuitionRepository
                .existsByStudentIdAndReferenceMonth(student.id(), referenceMonth);

        if(exists){return;}

        Tuition tuition = Tuition.builder()
                .studentId(student.id())
                .studentName(student.name())
                .totalAmount(new BigDecimal("50"))
                .paidAmount(BigDecimal.ZERO)
                .status(TuitionStatus.PENDENTE)
                .dueDate(LocalDate.now().withDayOfMonth(10))
                .referenceMonth(referenceMonth)
                .build();

        tuitionRepository.save(tuition);
    }

    public List<TuitionResponse> getAll() {
        return tuitionRepository.findAll()
                .stream()
                .map(t -> new TuitionResponse(
                        t.getId(),
                        t.getStudentId(),
                        t.getStudentName(),
                        t.getTotalAmount(),
                        t.getPaidAmount(),
                        t.getTotalAmount().subtract(t.getPaidAmount()),
                        t.getStatus(),
                        t.getDueDate()
                ))
                .toList();
    }
}
