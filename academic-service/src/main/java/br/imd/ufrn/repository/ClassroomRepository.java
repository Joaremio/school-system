package br.imd.ufrn.repository;

import br.imd.ufrn.dto.StudentResponse;
import br.imd.ufrn.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
