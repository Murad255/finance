package com.bookkeeping.finance.controller.rest;

import com.bookkeeping.finance.model.SaldoCell;
import com.bookkeeping.finance.repo.SaldoCellRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/SaldoCell")
public class SaldoCellRest {

    @Autowired
    private SaldoCellRepo service;

    @GetMapping("/")
    public Iterable<SaldoCell> read() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaldoCell> read(@PathVariable("id") Long id) {
        Optional<SaldoCell> foundAnswer = service.findById(id);
        SaldoCell answer = foundAnswer.isPresent() ? foundAnswer.get() : new SaldoCell();

        if (answer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(answer);
        }
    }

    @PostMapping("/")
    public ResponseEntity<SaldoCell> create(@RequestBody SaldoCell student) throws URISyntaxException {
        SaldoCell createdStudent = service.save(student);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdStudent.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaldoCell> update(@RequestBody SaldoCell student, @PathVariable Long id) {
		SaldoCell data = service.findById(id).get();
        if (data == null) {
            return ResponseEntity.notFound().build();
        } else {
			data.setAmount(student.getAmount());
			data.setName(student.getName()) ;
			data.setIsConst(student.getIsConst());

            service.save(data);
            return ResponseEntity.ok(data);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
