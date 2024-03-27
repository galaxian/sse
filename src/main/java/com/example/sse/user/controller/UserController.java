package com.example.sse.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sse.user.dto.UserRequestDto;
import com.example.sse.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody UserRequestDto requestDto) {
		userService.signup(requestDto);
		return ResponseEntity.ok().build();
	}
}
