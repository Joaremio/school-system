package br.ufrn.imd.repository;

import br.ufrn.imd.model.Tuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuitionRepository extends JpaRepository<Tuition,Long> {
    boolean existsByStudentIdAndReferenceMonth(Long studentId, String referenceMonth);
}
