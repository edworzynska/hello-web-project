package com.example.hello_web_project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
class HelloWebProjectApplicationTests {
	private static HelloWebProjectApplication helloWebProjectApplication;

	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
    public static void setUp() {
		helloWebProjectApplication = new HelloWebProjectApplication();
	}
	@Test
	void returnsHelloMessageResponse() throws Exception{
		mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello World!!"));
	}

	@Test
	void returnsHelloNameResponse() throws Exception {
		mockMvc.perform(get("/hello").param("name","Gucci"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello Gucci!!"));
	}

	@Test
	void returnsHelloMessage() {
		var result = helloWebProjectApplication.hello("ddd");
		assertEquals("Hello ddd!!", result);
	}

	@Test
	void submitsMessageWithParams() throws Exception{
		mockMvc.perform(post("/submit").param("name", "Gucci").param("message", "meow"))
				.andExpect(status().isOk())
				.andExpect(content().string("Thanks Gucci, you sent this message: meow"));
	}

	@Test
	void submitsMessageWithoutParams () throws Exception{
		mockMvc.perform(post("/submit"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void submitsAndReturnsCustomMessage(){
		var result = helloWebProjectApplication.submit("dd", "a nice day");
		assertEquals("Thanks dd, you sent this message: a nice day", result);
	}

	@Test
	void returnsDefaultNames() throws Exception{
		mockMvc.perform(get("/names"))
				.andExpect(status().isOk())
				.andExpect(content().string("Julia, Mary, Karim"));
	}

	@Test
	void returnsCustomNames() throws Exception {
		mockMvc.perform(get("/names").param("names","Gucci, Gucci, Gucci"))
				.andExpect(status().isOk())
				.andExpect(content().string("Gucci, Gucci, Gucci"));
	}

	@Test
	void sortsStringOfNames() {
		var result = helloWebProjectApplication.sortNames("Dee,Bee,Tee");
		assertEquals("Bee, Dee, Tee", result);
	}

	@Test
	void sortsStringOfNamesResponse() throws Exception{
		mockMvc.perform(post("/sort-names").param("names", "Asdf,Qwer,Cbnm,Bdee"))
				.andExpect(status().isOk())
				.andExpect(content().string("Asdf, Bdee, Cbnm, Qwer"));
	}

	@Test
	void sortsNamesNoParams() throws Exception{
		mockMvc.perform(post("/sort-names"))
				.andExpect(status().is4xxClientError())
				.andExpect(content().string("Error: missing parameter names"));
	}
}
