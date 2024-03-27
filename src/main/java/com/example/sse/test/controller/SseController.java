package com.example.sse.test.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.sse.test.service.SseService;

@RestController
public class SseController {

	private final SseService service;

	public SseController(SseService service) {
		this.service = service;
	}

	@GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> connect() {
		SseEmitter emitter = service.connect();
		return ResponseEntity.ok().body(emitter);
	}

	@GetMapping("/connection")
	public ResponseEntity<List<SseEmitter>> allConnect() {
		List<SseEmitter> allEmitter = service.getAllEmitter();
		return ResponseEntity.ok().body(allEmitter);
	}

	@PostMapping("/count")
	public ResponseEntity<Void> count() {
		service.count();
		return ResponseEntity.ok().build();
	}
}
