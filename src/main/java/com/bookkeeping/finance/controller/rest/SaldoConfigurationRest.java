package com.bookkeeping.finance.controller.rest;

import com.bookkeeping.finance.model.SaldoConfiguration;
import com.bookkeeping.finance.repo.SaldoConfigurationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/SaldoConfiguration")
public class SaldoConfigurationRest {

	@Autowired
	private SaldoConfigurationRepo service;

	@GetMapping("/")
	public Iterable<SaldoConfiguration> read() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SaldoConfiguration> read(@PathVariable("id") Long id) {
		Optional<SaldoConfiguration> foundAnswer = service.findById(id);
		SaldoConfiguration answer = foundAnswer.isPresent() ? foundAnswer.get() : new SaldoConfiguration();

		if (answer == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(answer);
		}
	}

	@PostMapping("/")
	public ResponseEntity<SaldoConfiguration> create(@RequestBody SaldoConfiguration student) throws URISyntaxException {
		SaldoConfiguration createdStudent = service.save(student);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdStudent.getId())
				.toUri();

		return ResponseEntity.created(uri)
				.body(createdStudent);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SaldoConfiguration> update(@RequestBody SaldoConfiguration student, @PathVariable Long id) {
		SaldoConfiguration data = service.findById(id).get();
		if (data == null) {
			return ResponseEntity.notFound().build();
		} else {
			data.setInvestmentsAmount(student.getInvestmentsAmount());
			data.setInvestmentsName(student.getInvestmentsName()); ;
			data.setStartedDateMonth(student.getStartedDateMonth());
			data.setStartedDateYear(student.getStartedDateYear());
			data.setCurrentCurrency(student.getCurrentCurrency());

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
