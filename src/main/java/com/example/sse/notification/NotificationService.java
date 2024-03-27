package com.example.sse.notification;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class NotificationService {

	private static final Map<Long, Set<SseEmitter>> emitterMap = new ConcurrentHashMap<>();

	public SseEmitter subscribe(Long userId) {
		SseEmitter emitter = new SseEmitter(60000L);
		Set<SseEmitter> sseEmitterSet = emitterMap.getOrDefault(userId, new HashSet<>());

		try {
			emitter.send(SseEmitter.event()
				.name("connect")
				.data("success"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		sseEmitterSet.add(emitter);
		emitter.onCompletion(() -> emitterMap.remove(emitter));
		emitterMap.put(userId, sseEmitterSet);
		emitter.onTimeout(emitter::complete);
		emitter.onCompletion(() -> {
			sseEmitterSet.remove(emitter);
		});
		return emitter;
	}

	public void sendEvent(Long userId, int size) {
		Set<SseEmitter> sseEmitterSet = emitterMap.get(userId);
		for (SseEmitter emitter : sseEmitterSet) {
			try {
				emitter.send(SseEmitter.event()
					.name("comment")
					.comment("게시글에 댓글이 달렸습니다.")
					.data(size));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
