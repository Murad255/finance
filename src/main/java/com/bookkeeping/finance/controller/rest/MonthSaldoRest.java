package com.bookkeeping.finance.controller.rest;

import com.bookkeeping.finance.model.MonthSaldo;
import com.bookkeeping.finance.repo.MonthSaldoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/MonthSaldo")
public class MonthSaldoRest {

	@Autowired
	private MonthSaldoRepo service;

	@GetMapping("/")
	public Iterable<MonthSaldo> read() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<MonthSaldo> read(@PathVariable("id") Long id) {
		Optional<MonthSaldo> foundAnswer = service.findById(id);
		MonthSaldo answer = foundAnswer.isPresent() ? foundAnswer.get() : new MonthSaldo();

		if (answer == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(answer);
		}
	}

	@PostMapping("/")
	public ResponseEntity<MonthSaldo> create(@RequestBody MonthSaldo student) throws URISyntaxException {
		MonthSaldo createdStudent = service.save(student);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdStudent.getId())
				.toUri();

		return ResponseEntity.created(uri)
				.body(createdStudent);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MonthSaldo> update(@RequestBody MonthSaldo student, @PathVariable Long id) {
		MonthSaldo data = service.findById(id).get();
		if (data == null) {
			return ResponseEntity.notFound().build();
		} else {
			data.setIncomes(student.getIncomes());
		//	data.setExpenses(student.getExpenses()); ;

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
