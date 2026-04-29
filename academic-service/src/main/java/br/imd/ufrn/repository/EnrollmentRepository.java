package br.imd.ufrn.repository;


import br.imd.ufrn.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentIdAndClassroomId(Long studentId, Long classroomId);

    void deleteByStudentIdAndClassroomId(Long studentId, Long classroomId);

    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByClassroomId(Long classroomId);

}
