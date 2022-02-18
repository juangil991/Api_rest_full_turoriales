package com.books.integrate.spring.react.controller;

import java.util.*;

import com.books.integrate.spring.react.model.Tutorial;
import com.books.integrate.spring.react.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.books.integrate.spring.react.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
    TutorialService tutorialService;

	@GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>>allTutorials(@RequestParam(required = false) String title){
        return tutorialService.getAllTutorials(title);
    }
	@GetMapping("/tutorials/query")
	public ResponseEntity<List<Tutorial>>allTutorialsTitle(@RequestParam("title") String title){
		return tutorialService.getAllTutorials(title);
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorial(@PathVariable("id") Long id){
		return tutorialService.getTutorialById(id);
	}


	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> addTutorial(@RequestBody Tutorial tutorial) {
		return tutorialService.createTutorial(tutorial);
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorials(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		return tutorialService.updateTutorial(id,tutorial);
	}

//HttpStatus
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<String> deleteTurorialById(@PathVariable("id") long id) {
		return tutorialService.deleteTutorial(id);
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteTutorials() {
		return tutorialService.deleteAllTutorials();
	}
	@DeleteMapping("/tutorials/query")
	public ResponseEntity<HttpStatus> deleteTitle(@RequestParam("title")String title) {
		return tutorialService.deleteTutorialsByTitle(title);
	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findPublisheds() {
		return tutorialService.findByPublished();
	}
}
