package br.ufrn.imd.integration;


import br.ufrn.imd.dto.StudentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="academic-service", url = "http://localhost:8081")
public interface AcademicClient {

    @GetMapping("/api/students")
    List<StudentResponse> getStudents();
}
