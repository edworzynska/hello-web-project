package com.example.hello_web_project;

import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class HelloWebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWebProjectApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false, defaultValue = "World")String name){
		return String.format("Hello %s!!", name);
	}
	@PostMapping("/submit")
	public String submit(@RequestParam String name, @RequestParam String message){

		return String.format("Thanks %s, you sent this message: %s", name, message);
	}
	@GetMapping("/names")
	public String getNames(@RequestParam (value = "names", required = false, defaultValue = "Julia, Mary, Karim")String names){
		return names;
	}
	@PostMapping("/sort-names")
	public String sortNames(@RequestParam String names){
		List<String> namesList = List.of(names.split(","));

        return namesList.stream().sorted().collect(Collectors.joining(", "));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> missingParameters(MissingServletRequestParameterException e){
		String name = e.getParameterName();
		return new ResponseEntity<>("Error: missing parameter " + name, HttpStatus.BAD_REQUEST);
	}
}
