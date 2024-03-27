package com.example.sse.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SseService {

	private List<SseEmitter> emitters = new ArrayList<>();
	private static int count = 0;

	public SseEmitter connect() {
		SseEmitter emitter = new SseEmitter(300000L);
		this.emitters.add(emitter);
		emitter.onCompletion(() -> {
			this.emitters.remove(emitter);
		});
		emitter.onTimeout(emitter::complete);
		try {
			emitter.send(SseEmitter.event()
				.name("connect")
				.data("connect test"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return emitter;
	}

	public List<SseEmitter> getAllEmitter() {
		return this.emitters;
	}

	public void count() {
		long countNum = count++;
		emitters.forEach(emitter -> {
			try {
				emitter.send(SseEmitter.event()
					.name("count")
					.data(countNum));
			} catch (Exception e) {
				throw new RuntimeException();
			}
		});
	}
}
