package com.bookkeeping.finance.controller.rest;

import com.bookkeeping.finance.model.SaveContainer;
import com.bookkeeping.finance.repo.SaveContainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/SaveContainer")
public class SaveContainerRest {

	@Autowired
	private SaveContainerRepo service;

	@GetMapping("/")
	public Iterable<SaveContainer> read() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SaveContainer> read(@PathVariable("id") Long id) {
		Optional<SaveContainer> foundAnswer = service.findById(id);
		SaveContainer answer = foundAnswer.isPresent() ? foundAnswer.get() : new SaveContainer();

		if (answer == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(answer);
		}
	}

	@PostMapping("/")
	public ResponseEntity<SaveContainer> create(@RequestBody SaveContainer student) throws URISyntaxException {
		SaveContainer createdStudent = service.save(student);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdStudent.getId())
				.toUri();

		return ResponseEntity.created(uri)
				.body(createdStudent);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SaveContainer> update(@RequestBody SaveContainer student, @PathVariable Long id) {
		SaveContainer data = service.findById(id).get();
		if (data == null) {
			return ResponseEntity.notFound().build();
		} else {
			data.setConfig(student.getConfig());
			data.setData(student.getData()) ;

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
