package com.example.sse.notification;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.sse.common.interceptor.Authenticated;
import com.example.sse.common.resolver.UserId;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class NotificationController {

	private final NotificationService notificationService;

	@Authenticated
	@GetMapping(value = "/notice", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> subscribe(@UserId String userId) {
		SseEmitter subscribe = notificationService.subscribe(Long.valueOf(userId));
		return ResponseEntity.ok().body(subscribe);
	}
}
