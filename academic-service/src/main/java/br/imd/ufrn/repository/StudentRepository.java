package br.imd.ufrn.repository;


import br.imd.ufrn.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("""
        SELECT s FROM Student s
        WHERE s.id NOT IN (
            SELECT e.student.id FROM Enrollment e
            WHERE e.classroom.id = :classroomId
        )
    """)
    List<Student> findStudentsNotEnrolledInClassroom(@Param("classroomId") Long classroomId);

    List<Student> findByEnrollmentsClassroomId(Long classroomId);
}