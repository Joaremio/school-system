package br.ufrn.imd.controller;


import br.ufrn.imd.dto.TuitionResponse;
import br.ufrn.imd.service.TuitionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tuitions")
public class TuitionController {
    private TuitionService tuitionService;

    public  TuitionController(TuitionService tuitionService) {
        this.tuitionService = tuitionService;
    }

    @GetMapping
    public List<TuitionResponse> getAll() {
        return tuitionService.getAll();
    }
}
